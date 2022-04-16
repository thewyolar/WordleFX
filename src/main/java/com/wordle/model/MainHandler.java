package com.wordle.model;

import com.wordle.MainApplication;
import com.wordle.ResultWindow;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class MainHandler {

    private final String[] firstRowLetters = {"Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х", "Ъ"};
    private final String[] secondRowLetters = {"Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"};
    private final String[] thirdRowLetters = {"↵", "Я", "С", "М", "И", "Т", "Ь", "Б", "Ю", "←"};

    private int CURRENT_ROW = 0;
    private int CURRENT_COLUMN = 0;
    private final int MAX_COLUMN = 4;
    private final int MAX_ROW = 5;
    private String winningWord;

    private void setLabelText(GridPane gridPane, int searchRow, int searchColumn, String input) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null)
            label.setText(input.toUpperCase());
    }

    private Label getLabel(GridPane gridPane, int searchRow, int searchColumn) {
        for (Node child : gridPane.getChildren()) {
            Integer r = GridPane.getRowIndex(child);
            Integer c = GridPane.getColumnIndex(child);
            int row = r == null ? 0 : r;
            int column = c == null ? 0 : c;
            if (row == searchRow && column == searchColumn && (child instanceof Label))
                return (Label) child;
        }
        return null;
    }

    private Label getLabel(GridPane gridPane, String letter) {
        Label label;
        for (Node child : gridPane.getChildren()) {
            if (child instanceof Label) {
                label = (Label) child;
                if (letter.equalsIgnoreCase(label.getText()))
                    return label;
            }
        }
        return null;
    }

    private String getLabelText(GridPane gridPane, int searchRow, int searchColumn) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null)
            return label.getText().toLowerCase();
        return null;
    }

    private void setLabelStyleClass(GridPane gridPane, int searchRow, int searchColumn, String styleclass) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null) {
            label.getStyleClass().add(styleclass);
        }
    }

    private void clearLabelStyleClass(GridPane gridPane, int searchRow, int searchColumn) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null) {
            label.getStyleClass().clear();
        }
    }

    private void updateRowColors(GridPane gridPane, int searchRow) {
        for (int i = 0; i <= MAX_COLUMN; i++) {
            Label label = getLabel(gridPane, searchRow, i);
            String styleClass;
            if (label != null) {
                String currentCharacter = String.valueOf(label.getText().charAt(0)).toLowerCase();
                if (String.valueOf(winningWord.charAt(i)).toLowerCase().equals(currentCharacter)) {
                    styleClass = "correct-letter";
                } else if (winningWord.contains(currentCharacter)) {
                    styleClass = "present-letter";
                } else {
                    styleClass = "wrong-letter";
                }

                FadeTransition firstFadeTransition = new FadeTransition(Duration.millis(300), label);
                firstFadeTransition.setFromValue(1);
                firstFadeTransition.setToValue(0.2);
                firstFadeTransition.setOnFinished(e -> {
                    label.getStyleClass().clear();
                    label.getStyleClass().setAll(styleClass);
                });

                FadeTransition secondFadeTransition = new FadeTransition(Duration.millis(300), label);
                secondFadeTransition.setFromValue(0.2);
                secondFadeTransition.setToValue(1);

                new SequentialTransition(firstFadeTransition, secondFadeTransition).play();
            }
        }
    }

    private void updateKeyboardColors(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) {
        String currentWord = getWordFromCurrentRow(gridPane).toLowerCase();
        for (int i = 0; i <= MAX_COLUMN; i++) {
            Label keyboardLabel = new Label();
            String styleClass;
            String currentCharacter = String.valueOf(currentWord.charAt(i));
            String winningCharacter = String.valueOf(winningWord.charAt(i));

            if (contains(firstRowLetters, currentCharacter))
                keyboardLabel = getLabel(keyboardRow1, currentCharacter);
            else if (contains(secondRowLetters, currentCharacter))
                keyboardLabel = getLabel(keyboardRow2, currentCharacter);
            else if (contains(thirdRowLetters, currentCharacter))
                keyboardLabel = getLabel(keyboardRow3, currentCharacter);

            if (currentCharacter.equals(winningCharacter))
                styleClass = "keyboardCorrectColor";
            else if (winningWord.contains("" + currentCharacter))
                styleClass = "keyboardPresentColor";
            else
                styleClass = "keyboardWrongColor";
            if (keyboardLabel != null) {
                keyboardLabel.getStyleClass().clear();
                keyboardLabel.getStyleClass().add(styleClass);
            }
        }
    }

    private String getWordFromCurrentRow(GridPane gridPane) {
        StringBuilder input = new StringBuilder();
        for (int j = 0; j <= MAX_COLUMN; j++)
            input.append(getLabelText(gridPane, CURRENT_ROW, j));
        return input.toString();
    }

    public void onKeyPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3, KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            onBackspacePressed(gridPane);
        } else if (keyEvent.getCode().isLetterKey() || keyEvent.getCode().getName().equals("Undefined")) {
            onLetterPressed(gridPane, keyEvent);
        }
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onEnterPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
        }
    }

    private void onBackspacePressed(GridPane gridPane) {
        if ((CURRENT_COLUMN == MAX_COLUMN || CURRENT_COLUMN == 0) && !Objects.equals(getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN), "")) {
            setLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN, "");
            clearLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN);
            setLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN, "default-tile");
        } else if ((CURRENT_COLUMN > 0 && CURRENT_COLUMN < MAX_COLUMN) || (CURRENT_COLUMN == MAX_COLUMN && Objects.equals(getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN), ""))) {
            CURRENT_COLUMN--;
            setLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN, "");
            clearLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN);
            setLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN, "default-tile");
        }
    }

    private void onLetterPressed(GridPane gridPane, KeyEvent keyEvent) {
        if (Objects.equals(getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN), "")) {
            setLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN, keyEvent.getText());
            Label label = getLabel(gridPane, CURRENT_ROW, CURRENT_COLUMN);
            ScaleTransition firstScaleTransition = new ScaleTransition(Duration.millis(100), label);
            firstScaleTransition.fromXProperty().setValue(1);
            firstScaleTransition.toXProperty().setValue(1.1);
            firstScaleTransition.fromYProperty().setValue(1);
            firstScaleTransition.toYProperty().setValue(1.1);
            ScaleTransition secondScaleTransition = new ScaleTransition(Duration.millis(100), label);
            secondScaleTransition.fromXProperty().setValue(1.1);
            secondScaleTransition.toXProperty().setValue(1);
            secondScaleTransition.fromYProperty().setValue(1.1);
            secondScaleTransition.toYProperty().setValue(1);
            new SequentialTransition(firstScaleTransition, secondScaleTransition).play();

            setLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN, "tile-with-letter");
            if (CURRENT_COLUMN < MAX_COLUMN)
                CURRENT_COLUMN++;
        }
    }

    private void onEnterPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) throws IOException {
        if (CURRENT_ROW <= MAX_ROW && CURRENT_COLUMN == MAX_COLUMN) {
            String guess = getWordFromCurrentRow(gridPane).toLowerCase();
            if (guess.equals(winningWord)) {
                updateRowColors(gridPane, CURRENT_ROW);
                updateKeyboardColors(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                ResultWindow.display(true, winningWord);
            } else if (isValidGuess(guess)) {
                updateRowColors(gridPane, CURRENT_ROW);
                updateKeyboardColors(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                if (CURRENT_ROW == MAX_ROW) {
                    ResultWindow.display(false, winningWord);
                    if (ResultWindow.getResetGame())
                        resetGame(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                }
                CURRENT_ROW++;
                CURRENT_COLUMN = 0;
            } else {
                MainApplication.showAlert();
            }
            if (ResultWindow.getResetGame()) {
                resetGame(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                ResultWindow.setResetGame(false);
            }
            if (ResultWindow.getQuitApplication())
                MainApplication.quit();
        }
    }

    public void getRandomWord() {
        winningWord = MainApplication.getDictionaryWords().get(new Random().nextInt(MainApplication.getDictionaryWords().size()));
    }

    private boolean isValidGuess(String guess) {
        return binarySearch(MainApplication.getDictionaryWords(), guess);
    }

    public void resetGame(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) {
        getRandomWord();
        Label label;
        for (Node child : gridPane.getChildren())
            if (child instanceof Label) {
                label = (Label) child;
                label.getStyleClass().clear();
                label.setText("");
                label.getStyleClass().add("default-tile");
            }

        for (Node child : keyboardRow1.getChildren())
            if (child instanceof Label) {
                label = (Label) child;
                label.getStyleClass().clear();
                label.getStyleClass().add("keyboardTile");
            }

        for (Node child : keyboardRow2.getChildren())
            if (child instanceof Label) {
                label = (Label) child;
                label.getStyleClass().clear();
                label.getStyleClass().add("keyboardTile");
            }

        for (Node child : keyboardRow3.getChildren())
            if (child instanceof Label) {
                label = (Label) child;
                label.getStyleClass().clear();
                label.getStyleClass().add("keyboardTile");
            }

        CURRENT_COLUMN = 0;
        CURRENT_ROW = 0;
    }

    private boolean binarySearch(ArrayList<String> list, String string) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = string.compareTo(list.get(mid));
            if (comparison == 0) return true;
            if (comparison > 0) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    private boolean contains(String[] array, String letter) {
        for (String string : array)
            if (string.equalsIgnoreCase(letter))
                return true;
        return false;
    }
}
