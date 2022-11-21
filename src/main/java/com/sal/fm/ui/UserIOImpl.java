package com.sal.fm.ui;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

@Component
public class UserIOImpl implements UserIO {
    final private Scanner sc = new Scanner(System.in);

    /* The print method takes in a message to display in the console and
    * then waits for an integer answer from the user to return
    */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /*
    * This method takes in a prompt to display in the console
    * and waits for an answer from the user to return
     */
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    /*
    * This method takes in a message to display in the console,
    * set a try...catch block to handle exception, and wrap it inside the while loop
    * the loop will run repeatedly as long as it is true else I will end the program
     */
    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                num = Integer.parseInt(readString(prompt));
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Invalid input. Please try again.");
            }
        }
        return num;
    }

    /*
    * This method takes in a message to display in the console,
    * and is going to re-prompt continuously until the user enter an integer within the range of mix and max number
    * to return
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        int num = 0;
        do {
            System.out.println(prompt);
            num = Integer.parseInt(sc.nextLine());
        }
        while (num < min || num > max);
        return num;
    }

    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt));
            } catch (NumberFormatException e) {
                this.print("You entered an invalid input. Please try again.");
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        }
        while (result < min || result > max);
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(prompt);
            } catch (NumberFormatException e) {
                this.print("You entered an invalid input. Please try again.");
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = readFloat(prompt);
        }
        while (result < min || result > max);
        return result;
    }

    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readString(prompt));
            } catch (NumberFormatException e) {
                this.print("You entered an invalid input. Please try again.");
            }
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean invalidInput = true;
        BigDecimal num = new BigDecimal("0.0");
        while (invalidInput) {
            try {
                num = new BigDecimal(readString(prompt));
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("You entered an invalid input. Please try again.");
            }
        }
        return num;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num;
        do {
            System.out.println(prompt);
            num = new BigDecimal(sc.nextLine());
        } while (num.doubleValue() < min.doubleValue() || num.doubleValue() > max.doubleValue() || num == null);
        return num;
    }

    @Override
    public LocalDate readDate(String prompt) {
        boolean invalidDate = true;
        LocalDate dateEntered = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        while(invalidDate) {
            try {
                String dateAsString = this.readString(prompt);
                dateEntered = LocalDate.parse(dateAsString, formatter);
                invalidDate = false;
            } catch (DateTimeParseException e) {
                this.print("Invalid input. Please enter a date in the format of MMddyyyy.");
            }
        }
        return dateEntered;
    }
}


