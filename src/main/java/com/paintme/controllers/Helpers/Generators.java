package com.paintme.controllers.Helpers;

import java.util.Random;

public class Generators {

    public static String generateRandomHex() {
        return String.format("#%06x", new Random().nextInt(256 * 256 * 256));
    }
}
