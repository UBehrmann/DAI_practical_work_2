package ch.heigvd.dai.Types;

import java.time.LocalDate;

public class Date {
    private int day;
    private int month;
    private int year;

    // Constructeur par défaut
    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }

    // Constructeur avec paramètres
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date: " + day + "/" + month + "/" + year);
        }
    }

    // Méthode pour mettre à jour la date à partir de l'heure système
    public void updateToCurrentSystemDate() {
        LocalDate currentDate = LocalDate.now(); // Récupère la date actuelle
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getFullDate() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    // Setters
    public void setDay(int day) {
        if (isValidDate(day, this.month, this.year)) {
            this.day = day;
        } else {
            throw new IllegalArgumentException("Invalid day: " + day + " for month " + month + " and year " + year);
        }
    }

    public void setMonth(int month) {
        if (isValidDate(this.day, month, this.year)) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("Invalid month: " + month + " for day " + day + " and year " + year);
        }
    }

    public void setYear(int year) {
        if (isValidDate(this.day, this.month, year)) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid year: " + year + " for day " + day + " and month " + month);
        }
    }

    public void setFullDate(String dateString) {
        // Vérifie le format de la chaîne (DD/MM/YYYY)
        if (!dateString.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            throw new IllegalArgumentException("Invalid date format. Expected DD/MM/YYYY.");
        }

        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date: " + dateString);
        }
    }

    // Méthode pour valider une date
    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > daysInMonth(month, year)) {
            return false;
        }

        return year >= 0;
    }

    // Méthode pour obtenir le nombre de jours dans un mois donné
    private int daysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeapYear(year)) ? 29 : 28;
            default:
                return 31;
        }
    }

    // Méthode pour vérifier si une année est bissextile
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
