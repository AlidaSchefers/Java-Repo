package com.hello;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //PopCrackle
            //if number is divisible by 3, print Pop
            //if div by 5, print Crackle
            //if div by both 3 and 5, print PopCrackle
            //if not div by 3 or 5, print number.
        System.out.print("Number: ");
        int num = new Scanner(System.in).nextInt();
        // Scanner scanner = new Scanner(System.in);
        // int num = scanner.nextInt();
        if (num % 3 == 0)
            System.out.print("Pop"); //use print so PopCrackle is not divided and separated by a line
        if (num % 5 == 0)
            System.out.print("Crackle");
        if ( !(num % 3 == 0) && !(num % 5 == 0) )
            System.out.print(num);
    }
}