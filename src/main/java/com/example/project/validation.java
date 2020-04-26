package com.example.project;


public class validation {


    public boolean validTime(String s) {

        String string = s;
        String[] parts = string.split(":");
        String hour = parts[0]; // 004
        String minute = parts[1];

        int hours = Integer.valueOf(hour);
        int minutes = Integer.valueOf(minute);
        if (hours < 0 || hours >= 23 || minutes >= 60)
            return true;
        else
            return false;

    }

    public boolean validDate(String s) {
        String string = s;
        String[] parts = string.split("[-./]");
        String day = parts[0]; // 004
        String month = parts[1];
        String year = parts[2];

        int days = Integer.valueOf(day);
        int months = Integer.valueOf(month);
        int years = Integer.valueOf(year);

        if (days > 31 || months > 12 || years < 2020)
            return true;
        else
            return false;

    }
}
