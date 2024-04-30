package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    private Dotenv env;

    public Env() {
        tryToLoad("./src/main/resources");
        tryToLoad("./classes/");
    }

    private void tryToLoad(String path) {
        try {
            env = Dotenv.configure().directory(path).load();
        } catch (Exception e) {
            System.out.println("Failed to load env from " + path);
        }
    }

    public Dotenv load() {
        return env;
    }
}
