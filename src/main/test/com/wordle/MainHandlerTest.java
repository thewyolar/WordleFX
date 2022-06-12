package com.wordle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainHandlerTest {
    MainHandler testMainHandler = new MainHandler();

    @Test
    void binarySearchTest() {
        ArrayList<String> testDictionary = new ArrayList<>(Arrays.asList("абзац", "пирог", "сапер", "север"));
        assertEquals(true, testMainHandler.binarySearch(testDictionary, "север"));
    }

    @Test
    void containsTest() {
        String[] testArray = new String[] { "носок", "сапог", "обвал" };
        assertEquals(true, testMainHandler.contains(testArray, "обвал"));
    }
}