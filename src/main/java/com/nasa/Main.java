package com.nasa;

public class Main {
    public static void main(String[] args) {
        RoverNavigator navigator = new RoverNavigator();
        navigator.execute(args[0]);
    }
}