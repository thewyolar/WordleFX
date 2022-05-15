package com.wordle.statistics;

import java.io.*;
import java.util.ArrayList;

public abstract class Statistics {

    private static final File statisticsFile = new File("src/main/java/com/wordle/statistics/stats.bin");

    private static ArrayList<Integer> statisticsList = new ArrayList<>();

    public static int[] getStatistics() {
        readStatistics();
        int[] result = new int[4];
        result[0] = statisticsList.size();
        result[1] = getTotalWins();
        result[2] = getWinsInRowNow();
        result[3] = getWinsInRowMax();

        System.out.println(statisticsList.toString());

        return result;
    }

    public static int getTotalWins() {
        int totalWins = 0;

        for (int i = 0; i < statisticsList.size(); i++) {
            if (statisticsList.get(i) == 1)
                totalWins += statisticsList.get(i);
        }

        return totalWins;
    }

    public static int getWinsInRowNow() {
        int count = 0;
        int lastZeroIndex = 0;

        for (int i = 0; i < statisticsList.size(); i++) {
            if (statisticsList.get(i) == 0)
                lastZeroIndex = i;
        }

        for (int j = lastZeroIndex + 1; j < statisticsList.size(); j++) {
            if (statisticsList.get(j) == 1)
                count++;
        }
        return count;
    }

    public static int getWinsInRowMax() {
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < statisticsList.size(); i++) {
            if (statisticsList.get(i) == statisticsList.get(i - 1) && statisticsList.get(i) == 1) {
                count++;
                if (count > maxCount)
                    maxCount = count;
            }
            else
                count = 1;
        }

        return maxCount;
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
            e.printStackTrace();
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
