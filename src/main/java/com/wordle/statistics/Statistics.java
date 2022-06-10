package com.wordle.statistics;

import java.io.*;

public abstract class Statistics {

    private static final File statisticsFile = new File("src/main/resources/com/wordle/stats.bin");

    private static int[] statisticsList = new int[] {0, 0, 0, 0};

    public static int[] getStatistics() {
        readStatistics();
        return statisticsList;
    }

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
