package com.example.project;

import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        try {
            FileWriter myWriter = new FileWriter("filename.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
