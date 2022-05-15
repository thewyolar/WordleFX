package com.wordle.statistics;

import java.io.*;
import java.util.ArrayList;

public abstract class Statistics {

    private static final File statisticsFile = new File("src/main/java/com/wordle/statistics/statistics.bin");

    private static ArrayList<Integer> statisticsList = new ArrayList<>();

    public static ArrayList<Integer> getStatistics() {
        readStatistics();
        return statisticsList;
    }

    public static void readStatistics() {
        try {
            if (!statisticsFile.exists()) {
                statisticsFile.createNewFile();
                writeFile(new ArrayList<Integer>());
            }
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statisticsList = (ArrayList<Integer>) objectInputStream.readObject();
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeStatistics(ArrayList<Integer> statistics) {
        try {
            if (!statisticsFile.exists())
                statisticsFile.createNewFile();
            else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statisticsFile));
                statisticsList = (ArrayList<Integer>) objectInputStream.readObject();
                objectInputStream.close();
            }
            writeFile(statistics);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(ArrayList<Integer> statistics) throws IOException {
        ArrayList<Integer> resultStatistics = new ArrayList<Integer>(statistics);
        resultStatistics.addAll(statisticsList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(statisticsFile));
        objectOutputStream.writeObject(resultStatistics);
        objectOutputStream.close();
    }
}
