package com.wordle.statistics;

import java.io.*;

/** Класс, который считывает и записывает в файл статистику игры */
public abstract class Statistics {

    /** Файл, хранящий статистику игры */
    private static final File statisticsFile = new File("src/main/resources/com/wordle/statistics.bin");

    /**
     * statisticsList - cписок значений, считанных из файла
     * statisticsList[0] - playedGames
     * statisticsList[1] - totalWins
     * statisticsList[2] - winsInRowNow
     * statisticsList[3] - winsInRowMax
     */
    private static int[] statisticsList = new int[] {0, 0, 0, 0};

    /**
     * Возвращает значение поля {@link Statistics#statisticsList}
     * @return возвращает cписок значений, считанных из файла
     */
    public static int[] getStatistics() {
        readStatistics();
        return statisticsList;
    }

    /** Считывает статистику из файла {@link Statistics#statisticsFile} */
    public static void readStatistics() {
        try {
            if (!statisticsFile.exists()) {
                statisticsFile.createNewFile();
                writeFile(0, 0, 0, 0);
            }
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statisticsList = (int[]) objectInputStream.readObject();
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Записывает статистику
     * @param playedGames - кол-во сыгранных игр
     * @param totalWins - кол-во победных игр
     * @param winsInRowNow - кол-во подряд сыгранных победных игр в последней попытке
     * @param winsInRowMax - максимальное кол-во подряд сыгранных победных игр
     */
    public static void writeStatistics(int playedGames, int totalWins, int winsInRowNow, int winsInRowMax) {
        try {
            if (!statisticsFile.exists())
                statisticsFile.createNewFile();
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statisticsList = (int[]) objectInputStream.readObject();
                objectInputStream.close();
            }
            writeFile(playedGames, totalWins, winsInRowNow, winsInRowMax);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Записывает статистику в файл {@link Statistics#statisticsFile}
     * @param playedGames - кол-во сыгранных игр
     * @param totalWins - кол-во победных игр
     * @param winsInRowNow - кол-во подряд сыгранных победных игр в последней попытке
     * @param winsInRowMax - максимальное кол-во подряд сыгранных победных игр
     * @exception IOException - исключение ввода-вывода
     */
    private static void writeFile(int playedGames, int totalWins, int winsInRowNow, int winsInRowMax) throws IOException {
        statisticsList[0] += playedGames;
        statisticsList[1] += totalWins;

        if (winsInRowNow == 0)
            statisticsList[2] = winsInRowNow;
        else
            statisticsList[2] += winsInRowNow;

        if (statisticsList[2] > statisticsList[3])
            statisticsList[3] = statisticsList[2];

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(statisticsFile));
        objectOutputStream.writeObject(statisticsList);
        objectOutputStream.close();
    }
}
