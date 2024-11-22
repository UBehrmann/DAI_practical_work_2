package ch.heigvd.dai.Types;

import java.time.LocalTime;

public class Time {
    private int hour;
    private int minute;
    private int second;

    // Constructeur par défaut
    public Time() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    // Constructeur avec paramètres
    public Time(int hour, int minute, int second) {
        if (isValidTime(hour, minute, second)) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else {
            throw new IllegalArgumentException("Invalid time: " + hour + ":" + minute + ":" + second);
        }
    }

    // Méthode pour mettre à jour l'heure selon l'heure système
    public void updateToCurrentSystemTime() {
        LocalTime currentTime = LocalTime.now();
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();
    }

    // Getters
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String getFullTime() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    // Setters
    public void setHour(int hour) {
        if (isValidTime(hour, this.minute, this.second)) {
            this.hour = hour;
        } else {
            throw new IllegalArgumentException("Invalid hour: " + hour);
        }
    }

    public void setMinute(int minute) {
        if (isValidTime(this.hour, minute, this.second)) {
            this.minute = minute;
        } else {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
    }

    public void setSecond(int second) {
        if (isValidTime(this.hour, this.minute, second)) {
            this.second = second;
        } else {
            throw new IllegalArgumentException("Invalid second: " + second);
        }
    }

    public void setFullTime(String timeString) {
        // Vérifie le format de la chaîne (HH:MM:SS)
        if (!timeString.matches("^\\d{2}:\\d{2}:\\d{2}$")) {
            throw new IllegalArgumentException("Invalid time format. Expected HH:MM:SS.");
        }

        String[] parts = timeString.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);

        if (isValidTime(hour, minute, second)) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else {
            throw new IllegalArgumentException("Invalid time: " + timeString);
        }
    }

    // Méthode pour valider une heure
    private boolean isValidTime(int hour, int minute, int second) {
        return hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >= 0 && second < 60;
    }
}
