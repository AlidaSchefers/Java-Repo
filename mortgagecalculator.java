package com.hello;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal ($1K - $1M): ", 1000, 1_000_000);
        float yearlyInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte periodYears = (byte)readNumber("Period (Years): ", 1,30);

        double mortgage = calculateMortgage(principal, yearlyInterest, periodYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE"+ "\n"+ "--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
        System.out.println(
                "\n"+
                "PAYMENT SCHEDULE"+
                "\n"+
                "----------------");

        short numPaymentsPaid = 0;
        short totalPayments = (short)calcPeriodMonths(periodYears);
        while(numPaymentsPaid <= totalPayments) {
            System.out.println(NumberFormat.getCurrencyInstance().format(calcRemainingBalance(principal, yearlyInterest, periodYears, numPaymentsPaid)));
            numPaymentsPaid++; //one more than the totalNumOfPayments on the last iteration. Does not matter for the purpose of printing out the remaining balance, though.
        }
    }

    public static double readNumber(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
        return value;
    };

    public static double calculateMortgage(int principal, double yearlyInterest, byte periodYears) {
        double monthlyInterestPercent = calcMonthlyInterest(yearlyInterest);
        short periodMonths = (short)calcPeriodMonths(periodYears);

        double mortgage = principal * (
                (monthlyInterestPercent * Math.pow(1 + monthlyInterestPercent, periodMonths))
                        / (Math.pow(1 + monthlyInterestPercent, periodMonths) - 1)
        );
        return mortgage;
    };

    public static double calcMonthlyInterest(double yearlyInterest) {
        return (yearlyInterest / PERCENT) / MONTHS_IN_YEAR;
    };

    public static int calcPeriodMonths(byte periodYears) {
        return periodYears * MONTHS_IN_YEAR;
    }

    public static double calcRemainingBalance(int principal, double yearlyInterest, byte periodYears, int numPaymentsPaid) {
        short totalNumOfPayments = (short)calcPeriodMonths(periodYears);
        double monthlyInterestPercent = calcMonthlyInterest(yearlyInterest);
        double remainingBalance =
                (principal * (
                        Math.pow(1+monthlyInterestPercent, totalNumOfPayments)
                                - Math.pow(1+monthlyInterestPercent, numPaymentsPaid)
                )) / (Math.pow(1+monthlyInterestPercent, totalNumOfPayments) - 1);
        return remainingBalance;
    }
}