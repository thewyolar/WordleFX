package com.wordlefx.util;

import com.wordlefx.MainApplication;
import com.wordlefx.statistics.Statistics;
import com.wordlefx.window.ResultWindow;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Класс, реализующий основную логику приложения
 * используется для сохранения "чистоты" в контроллере {@link com.wordlefx.controllers.MainController}
 */
public class MainHandler {

    /** firstRowLetters - список всех букв первой строки экранной клавиатуры */
    private final String[] firstRowLetters = {"Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х", "Ъ"};

    /** firstRowLetters - список всех букв средней строки экранной клавиатуры */
    private final String[] secondRowLetters = {"Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"};

    /** firstRowLetters - список всех букв последней строки экранной клавиатуры */
    private final String[] thirdRowLetters = {"Я", "Ч", "С", "М", "И", "Т", "Ь", "Б", "Ю"};

    /** CURRENT_ROW - содержит номер текущей строки */
    private int CURRENT_ROW = 0;

    /** CURRENT_COLUMN - содержит номер текущего столбца */
    private int CURRENT_COLUMN = 0;

    /** MAX_COLUMN - содержит номер последней строки */
    private final int MAX_ROW = 5;

    /** MAX_ROW - содержит номер последнего столбца */
    private final int MAX_COLUMN = 4;

    /** winningWord - загаданное слово */
    private String winningWord;

    /** playedGames - кол-во сыгранных игр */
    private int playedGames;

    /** totalWins - кол-во победных игр */
    private int totalWins;

    /** winsInRowNow - кол-во подряд сыгранных победных игр в последней попытке */
    private int winsInRowNow;

    /** winsInRowMax - максимальное кол-во подряд сыгранных победных игр */
    private int winsInRowMax;

    /**
     * Устанавливает текст метки, т.е. побуквенно вводит слово в таблицу
     * @param gridPane - таблица для ввода слов
     * @param searchRow - номер строки введенной буквы
     * @param searchColumn - номер столбца введенной буквы
     * @param input - введенная буква
     */
    private void setLabelText(GridPane gridPane, int searchRow, int searchColumn, String input) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null)
            label.setText(input.toUpperCase());
    }

    /**
     * Возвращает необходимый объект класса метки из таблицы
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     * @param searchColumn - номер столбца с буквой
     * @return возвращает необходимую метку из таблицы
     */
    private static Label getLabel(GridPane gridPane, int searchRow, int searchColumn) {
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

    /**
     * Возвращает из таблицы все объекты класса кнопки, буква которой содержится в нужном слове
     * @param gridPane - таблица, содержащая метки с буквами
     * @param letter - слово, буквы которого нужно получить
     * @return возвращает кнопки, буквы которых содеражатся в нужном слове из таблицы
     */
    private Button getButton(GridPane gridPane, String letter) {
        Button button;
        for (Node child : gridPane.getChildren()) {
            if (child instanceof Button) {
                button = (Button) child;
                if (letter.equalsIgnoreCase(button.getText()))
                    return button;
            }
        }
        return null;
    }

    /**
     * Возвращает текст метки
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     * @param searchColumn - номер столбца с буквой
     * @return возвращает букву слова по номеру строки и столбца
     */
    private static String getLabelText(GridPane gridPane, int searchRow, int searchColumn) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null)
            return label.getText().toLowerCase();
        return null;
    }

    /**
     * Устанавливает класс со стилями для метки
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     * @param searchColumn - номер столбца с буквой
     * @param styleClass - название класса со стилями
     */
    private void setLabelStyleClass(GridPane gridPane, int searchRow, int searchColumn, String styleClass) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null) {
            label.getStyleClass().add(styleClass);
        }
    }

    /**
     * Возвращает класс со стилями для метки по номеру строки и столбца
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     * @param searchColumn - номер столбца с буквой
     */
    private static String getLabelStyleClass(GridPane gridPane, int searchRow, int searchColumn) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null)
            return String.valueOf(label.getStyleClass());
        return null;
    }

    /**
     * Удаляет класс со стилями для метки по номеру строки и столбца
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     * @param searchColumn - номер столбца с буквой
     */
    private void clearLabelStyleClass(GridPane gridPane, int searchRow, int searchColumn) {
        Label label = getLabel(gridPane, searchRow, searchColumn);
        if (label != null) {
            label.getStyleClass().clear();
        }
    }

    /**
     * Обновляет цвет буквы из таблицы по номеру строки
     * @param gridPane - таблица, содержащая метки с буквами
     * @param searchRow - номер строки с буквой
     */
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

                /*FadeTransition firstFadeTransition = new FadeTransition(Duration.millis(300), label);
                firstFadeTransition.setFromValue(1);
                firstFadeTransition.setToValue(0.2);
                firstFadeTransition.setOnFinished(e -> {
                    label.getStyleClass().clear();
                    label.getStyleClass().setAll(styleClass);
                });

                FadeTransition secondFadeTransition = new FadeTransition(Duration.millis(300), label);
                secondFadeTransition.setFromValue(0.2);
                secondFadeTransition.setToValue(1);

                SequentialTransition transition = new SequentialTransition(firstFadeTransition, secondFadeTransition);
                transition.play();*/
                label.getStyleClass().clear();
                label.getStyleClass().setAll(styleClass);
            }
        }
    }

    /**
     * Обновляет цвета всех клавиш на экранной клавиатуре в соответствии с введенным словом
     * @param gridPane - таблица, содержащая метки с буквами
     * @param keyboardRow1 - первая строка букв экранной клавиатуры
     * @param keyboardRow2 - средняя строка букв экранной клавиатуры
     * @param keyboardRow3 - последняя строка букв экранной клавиатуры
     */
    private void updateKeyboardColors(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) {
        String currentWord = getWordFromCurrentRow(gridPane).toLowerCase();
        for (int i = 0; i <= MAX_COLUMN; i++) {
            Button keyboardButton = new Button();
            String styleClass;
            String currentCharacter = String.valueOf(currentWord.charAt(i));
            String winningCharacter = String.valueOf(winningWord.charAt(i));

            if (contains(firstRowLetters, currentCharacter))
                keyboardButton = getButton(keyboardRow1, currentCharacter);
            else if (contains(secondRowLetters, currentCharacter))
                keyboardButton = getButton(keyboardRow2, currentCharacter);
            else if (contains(thirdRowLetters, currentCharacter))
                keyboardButton = getButton(keyboardRow3, currentCharacter);

            if (currentCharacter.equals(winningCharacter))
                styleClass = "keyboardCorrectColor";
            else if (winningWord.contains(currentCharacter))
                styleClass = "keyboardPresentColor";
            else
                styleClass = "keyboardWrongColor";

            if (keyboardButton != null) {
                keyboardButton.getStyleClass().clear();
                keyboardButton.getStyleClass().add(styleClass);
            }
        }
    }

    /**
     * Возвращает слово из таблицы
     * @param gridPane - таблица, содержащая слова
     * @return возвращает слово из текущей строки
     */
    private String getWordFromCurrentRow(GridPane gridPane) {
        StringBuilder input = new StringBuilder();
        for (int j = 0; j <= MAX_COLUMN; j++)
            input.append(getLabelText(gridPane, CURRENT_ROW, j));
        return input.toString();
    }

    /**
     * Заполняет окно с результатами {@link ResultWindow}
     * @param mainGrid - таблица, содержащая введенные слова
     * @param resultGrid - таблица для вывода результатов
     */
    public static void fillResultGrid(GridPane mainGrid, GridPane resultGrid) {
        for (int i = 0; i < mainGrid.getRowCount(); i++) {
            for (int j = 0; j < mainGrid.getColumnCount(); j++) {
                if (!getLabelText(mainGrid, i, j).isEmpty()) {
                    String labelStyleClass = getLabelStyleClass(mainGrid, i, j);
                    if (labelStyleClass.contains("correct-letter")) {
                        Label label = new Label();
                        label.getStyleClass().add("correct-result-tile");
                        resultGrid.add(label, j, i);
                    }
                    else if (labelStyleClass.contains("present-letter")) {
                        Label label = new Label();
                        label.getStyleClass().add("present-result-tile");
                        resultGrid.add(label, j, i);
                    }
                    else {
                        Label label = new Label();
                        label.getStyleClass().add("default-result-tile");
                        resultGrid.add(label, j, i);
                    }
                }
            }
        }
    }

    /**
     * Считывает результаты {@link ResultWindow}
     * @param resultGrid - таблица для вывода результатов
     * @return возвращает отфторматированное строковое представление таблицы результатов
     */
    public static String readResultGrid(GridPane resultGrid) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < resultGrid.getRowCount(); i++) {
            for (int j = 0; j < resultGrid.getColumnCount(); j++) {
                String labelStyleClass = getLabelStyleClass(resultGrid, i, j);
                if (labelStyleClass.contains("correct-result-tile")) {
                    stringBuilder.append("\uD83D\uDFE9");
                }
                else if (labelStyleClass.contains("present-result-tile")) {
                    stringBuilder.append("\uD83D\uDFE8");
                }
                else {
                    stringBuilder.append("⬜");
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Обрабатывает событие нажатия клавиши на клавиатуре
     * @param gridPane - таблица, содержащая введенные слова
     * @param keyboardRow1 - первая строка букв экранной клавиатуры
     * @param keyboardRow2 - средняя строка букв экранной клавиатуры
     * @param keyboardRow3 - последняя строка букв экранной клавиатуры
     * @param keyEvent - событие, указывающее на то, что произошло нажатие клавиши
     * @exception IOException - исключение ввода-вывода
     */
    public void onKeyPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3, KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            onBackspacePressed(gridPane);
        } else if (keyEvent.getCode().isLetterKey() || keyEvent.getCode().getName().equals("Undefined")) {
            onLetterPressed(gridPane, keyEvent);
        }
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onEnterPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
        }
//        PrintStream out = new PrintStream(System.out, true, "utf-8");
//        out.println(winningWord);
    }

    /**
     * Обрабатывает событие нажатия клавиши backspace
     * @param gridPane - таблица, содержащая введенные слова
     */
    private void onBackspacePressed(GridPane gridPane) {
        if ((CURRENT_COLUMN == MAX_COLUMN || CURRENT_COLUMN == 0) && !getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN).isEmpty()) {
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

    /**
     * Обрабатывает событие нажатия клавиши с буквой
     * @param gridPane - таблица, содержащая введенные слова
     * @param keyEvent - событие, указывающее на то, что произошло нажатие клавиши
     */
    private void onLetterPressed(GridPane gridPane, KeyEvent keyEvent) {
        if (getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN).isEmpty()) {
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

            SequentialTransition transition = new SequentialTransition(firstScaleTransition, secondScaleTransition);
            transition.play();

            setLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN, "tile-with-letter");
            if (CURRENT_COLUMN < MAX_COLUMN)
                CURRENT_COLUMN++;
        }
    }

    public void onVirtualKeyPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3, Button btn) throws IOException {
        if (btn.getText().equals("⌫")) {
            onBackspacePressed(gridPane);
        } else if (btn.getText().equals("↵")) {
            onEnterPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
        } else {
            onLetterPressed(gridPane, btn);
        }
//        PrintStream out = new PrintStream(System.out, true, "utf-8");
//        out.println(winningWord);
    }

    private void onLetterPressed(GridPane gridPane, Button btn) {
        if (getLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN).isEmpty()) {
            setLabelText(gridPane, CURRENT_ROW, CURRENT_COLUMN, btn.getText());
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

            SequentialTransition transition = new SequentialTransition(firstScaleTransition, secondScaleTransition);
            transition.play();

            setLabelStyleClass(gridPane, CURRENT_ROW, CURRENT_COLUMN, "tile-with-letter");
            if (CURRENT_COLUMN < MAX_COLUMN)
                CURRENT_COLUMN++;
        }
    }

    /**
     * Обрабатывает событие нажатия клавиши enter
     * @param gridPane - таблица, содержащая введенные слова
     * @param keyboardRow1 - первая строка букв экранной клавиатуры
     * @param keyboardRow2 - средняя строка букв экранной клавиатуры
     * @param keyboardRow3 - последняя строка букв экранной клавиатуры
     * @exception IOException - исключение ввода-вывода
     */
    private void onEnterPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) throws IOException {
        if (CURRENT_ROW <= MAX_ROW && CURRENT_COLUMN <= MAX_COLUMN) {
            String guess = getWordFromCurrentRow(gridPane).toLowerCase();
            if (guess.equals(winningWord)) {
                playedGames += 1;
                totalWins += 1;
                winsInRowNow += 1;

                if (winsInRowNow > winsInRowMax)
                    winsInRowMax = winsInRowNow;

                updateRowColors(gridPane, CURRENT_ROW);
                updateKeyboardColors(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                Statistics.writeStatistics(playedGames, totalWins, winsInRowNow, winsInRowMax);
                ResultWindow.display(true, winningWord);
            } else if (isValidGuess(guess)) {
                updateRowColors(gridPane, CURRENT_ROW);
                updateKeyboardColors(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                if (CURRENT_ROW == MAX_ROW) {
                    playedGames += 1;
                    winsInRowNow = 0;
                    Statistics.writeStatistics(playedGames, totalWins, winsInRowNow, winsInRowMax);
                    ResultWindow.display(false, winningWord);
                    if (ResultWindow.getResetGame()) {
                        resetGame(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                    }
                }
                CURRENT_ROW++;
                CURRENT_COLUMN = 0;
            } else {
                if (guess.length() < 5) {
                    MainApplication.showAlert("В слове не хватает букв!");
                } else {
                    MainApplication.showAlert("В словаре игры нет такого слова, \nпопробуйте другое!");
                }
            }
            if (ResultWindow.getResetGame()) {
                resetGame(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                ResultWindow.setResetGame(false);
            }
            if (ResultWindow.getQuitApplication())
                MainApplication.quit();
        }
    }

    /**
     * Выбирает случайное слово из словаря
     */
    public void getRandomWord() {
        winningWord = MainApplication.getDictionaryWords().get(new Random().nextInt(MainApplication.getDictionaryWords().size()));
    }

    /**
     * Проверяет введенное слово на наличие в словаре
     * для более быстрого поиска используется алгоритм бинарного поиска (словарь предварительно отсортирован по алфавиту)
     * @param guess - проверяемое слово
     * @return возвращает true, если слово присутствует в словаре, иначе - false
     */
    public boolean isValidGuess(String guess) {
        return binarySearch(MainApplication.getDictionaryWords(), guess);
    }

    /**
     * Перезапускает игру
     * @param gridPane - таблица, содержащая введенные слова
     * @param keyboardRow1 - первая строка букв экранной клавиатуры
     * @param keyboardRow2 - средняя строка букв экранной клавиатуры
     * @param keyboardRow3 - последняя строка букв экранной клавиатуры
     */
    public void resetGame(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) {
        getRandomWord();
        playedGames = 0;
        totalWins = 0;
        winsInRowNow = 0;
        winsInRowMax = 0;
        Label label;
        Button button;
        for (Node child : gridPane.getChildren())
            if (child instanceof Label) {
                label = (Label) child;
                label.getStyleClass().clear();
                label.setText("");
                label.getStyleClass().add("default-tile");
            }

        for (Node child : keyboardRow1.getChildren())
            if (child instanceof Button) {
                button = (Button) child;
                button.getStyleClass().clear();
                button.getStyleClass().add("keyboardTile");
            }

        for (Node child : keyboardRow2.getChildren())
            if (child instanceof Button) {
                button = (Button) child;
                button.getStyleClass().clear();
                button.getStyleClass().add("keyboardTile");
            }

        for (Node child : keyboardRow3.getChildren())
            if (child instanceof Button) {
                button = (Button) child;
                button.getStyleClass().clear();
                button.getStyleClass().add("keyboardTile");
            }

        CURRENT_COLUMN = 0;
        CURRENT_ROW = 0;
    }

    /**
     * Реализует алгоритм двоичного поиска
     * @param list - список, по которому происходит поиск
     * @param string - слово, которое нужно найти
     * @return возвращает true, если слово найдено, иначе - false
     */
    public boolean binarySearch(ArrayList<String> list, String string) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = string.compareTo(list.get(mid));
            if (comparison == 0)
                return true;

            if (comparison > 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    /**
     * Проверяет содержит ли список слово или нет
     * @param array - список для поиска слова
     * @param letter - слово, которое нужно найти
     * @return возвращает true, если слово присутствует, иначе - false
     */
    public boolean contains(String[] array, String letter) {
        for (String string : array)
            if (string.equalsIgnoreCase(letter))
                return true;
        return false;
    }
}
