package com.wordle.statistics;

import java.io.*;
import java.util.ArrayList;

public abstract class Statistics {

    private static final File statisticsFile = new File("src/main/java/com/wordle/statistics/statistics.bin");

    private static ArrayList<Integer> statisticsList = new ArrayList<>();

    public static int[] getStatistics() {
        readStatistics();
        int[] result = new int[4];
        result[0] = statisticsList.size();

        for (int i = 0; i < statisticsList.size(); i++) {
            if (statisticsList.get(i) == 1)
                result[1] += statisticsList.get(i);
        }

        if (statisticsList.size() == 1) {
            result[3] = statisticsList.get(statisticsList.size() - 1);
        } else {
            for (int j = 0; j < statisticsList.size() - 1; j++) {
                if (statisticsList.get(j) == statisticsList.get(j + 1)) {
                    result[3] += statisticsList.get(j) + statisticsList.get(j + 1);
                }
            }
        }

        return result;
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

    private static ArrayList<Integer> joinLists(ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        if ((firstList == null) || (firstList.isEmpty() && (secondList != null)))
            return secondList;
        if ((secondList == null) || secondList.isEmpty())
            return firstList;

        ArrayList<Integer> result = new ArrayList(firstList.size() + secondList.size());
        result.addAll(firstList);
        result.addAll(secondList);

        return result;
    }

    private static void writeFile(ArrayList<Integer> statistics) throws IOException {
        ArrayList<Integer> resultStatistics = joinLists(statisticsList, statistics);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(statisticsFile));
        objectOutputStream.writeObject(resultStatistics);
        objectOutputStream.close();
    }
}
