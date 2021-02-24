package com.hello;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal ($1K - $1M): ", 1000, 1_000_000);
        float yearlyInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte periodYears = (byte)readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, yearlyInterest, periodYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.print(mortgageFormatted);
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min || value <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
        return value;
    };

    public static double calculateMortgage(int principal, double yearlyInterest, byte periodYears) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        double monthlyInterestPercent = (yearlyInterest / PERCENT) / MONTHS_IN_YEAR;
        short periodMonths = (short)(periodYears * MONTHS_IN_YEAR);

        double mortgage = principal * (
                (monthlyInterestPercent * Math.pow(1 + monthlyInterestPercent, periodMonths))
                        / (Math.pow(1 + monthlyInterestPercent, periodMonths) - 1)
        );
        return mortgage;
    };
}