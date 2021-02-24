package com.hello;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        //user input: Principal, Annual Interest Rate (e.g. 3.92), Period (Years)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Principal: ");
        int principal = scanner.nextInt();
        System.out.print("Annual Interest Rate: ");
        double annualInterestRate = scanner.nextDouble();
        System.out.print("Period (Years): ");
        int periodYears = scanner.nextInt();

        double monthlyInterestRatePercent = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        int periodMonths = periodYears * MONTHS_IN_YEAR;

        //there is implicit casting: periodMonths and principal become doubles in the math
        double mortgage = principal * (
                (monthlyInterestRatePercent * Math.pow(1+monthlyInterestRatePercent, periodMonths))
                / ( Math.pow(1+monthlyInterestRatePercent, periodMonths) - 1 )
                );

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.print(mortgageFormatted);
    }
}