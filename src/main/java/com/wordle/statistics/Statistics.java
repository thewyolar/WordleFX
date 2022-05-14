package com.wordle.statistics;

import java.io.*;

public abstract class Statistics {

    private static final File statisticsFile = new File("src/main/java/com/wordle/statistics/statistics.bin");

    private static int[] statistics = new int[] { 0, 0, 0, 0 };

    public static int[] getStatistics() {
        readStatistics();
        return statistics;
    }

    public static void readStatistics() {
        try {
            if (!statisticsFile.exists()) {
                statisticsFile.createNewFile();
                writeFile(0, 0, 0, 0);
            }
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statistics = (int[]) objectInputStream.readObject();
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeStatistics(int playedGame, int totalWins, int winsInRowNow, int winsInRowMax) {
        try {
            if (!statisticsFile.exists())
                statisticsFile.createNewFile();
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statistics = (int[]) objectInputStream.readObject();
                objectInputStream.close();
            }
            writeFile(playedGame, totalWins, winsInRowNow, winsInRowMax);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(int playedGame, int totalWins, int winsInRowNow, int winsInRowMax) throws IOException {
        statistics[0] += playedGame;
        statistics[1] += totalWins;
        statistics[2] += winsInRowNow;
        statistics[3] += winsInRowMax;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(statisticsFile));
        objectOutputStream.writeObject(statistics);
        objectOutputStream.close();
    }
}
