package bullscows;

/* stage 1: print results
System.out.println("The secret code is prepared: ****.");
        System.out.println("");
        System.out.println("Turn 1. Answer:");
        System.out.println("1234");
        System.out.println("Grade: 1 cow.");
        System.out.println("");
        System.out.println("Turn 2. Answer:");
        System.out.println("9876");
        System.out.println("Grade: 4 bulls.");
        System.out.println("Congrats! The secret code is 9876.");
 */



import java.util.*;

public class Main {
    public static void main(String[] args) {

        /* stage 3: generating random code using nanoTime
        System.out.println("Please, enter the secret code's length:");

        Scanner scan = new Scanner(System.in);
        String target = "";

        int length = scan.nextInt();
        while (length > 10) {
            System.out.println("Error.");
            System.out.println("Enter the number of digits of the code:");
            length = scan.nextInt();
        }

        if (length <= 10) {
            System.out.println("Okay, let's start a game!");
            long pseudoRandomNumber = System.nanoTime();
            String randomNumberString = Long.toString(pseudoRandomNumber);

            // iterate in reverse, add unique number
            for (int i = length - 1; i >= 0; i--) {
                if (!target.contains(randomNumberString.valueOf(i))) {
                    target += randomNumberString.valueOf(i);
                }
            }
            findBullsCows(target, scan);
        }


        // stage 4: using Random to generate random code

        // scan length
        System.out.println("Please, enter the secret code's length:");
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int length = scan.nextInt();
        String target = "";


        // error code when length > 10
        while (length > 10) {
            System.out.println("Error.");
            System.out.println("Enter the number of digits of the code:");
            length = scan.nextInt();
        }

        if (length <= 10) {
            System.out.println("Okay, let's start a game!");


            // first integer
            target += rand.nextInt(10);

            // the rest of the integer
            if (length > 1) {
                for (int i = 1; i < length - 1; i++) {
                    String temp = Integer.toString(rand.nextInt(10) + 1);
                    while (target.contains(temp)) {
                        temp = Integer.toString(rand.nextInt(10) + 1);
                    }
                    target += temp;
                }
            }

            findBullsCows(target, scan, length);
        }

        */

        // stage 5: expanding range to a-z, also asking for available range of chars

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String len = "";
        int length;
        String charLen;
        int charLength;

        System.out.println("Input the length of the secret code:");
        try {
            len = scan.next();
            length = Integer.parseInt(len);

            System.out.println("Input the number of possible symbols in the code:");
            charLen = scan.next();
            charLength = Integer.parseInt(charLen) - 10;

            // stage 6: error handling
            if (length > (charLength + 10)) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            } else if (charLength > 26) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else if (length == 0 || charLength < 0) {
                System.out.println("Error: length of code / number of possible symbols cannot be 0!");
            } else {
                String a = (charLength == 0) ? " (0-9)." : " (0-9, a-" + (char) ('a' + charLength - 1) + ").";

                // print ready line
                System.out.println("The secret is prepared: "
                        + "*".repeat(Math.max(0, length))
                        + a);
                System.out.println("Okay, let's start a game!");


                StringBuilder target = new StringBuilder();
                // generate random string
                if (charLength == 0) {
                    for (int i = 1; i <= length; i++) {
                        String temp = Integer.toString(rand.nextInt(10));
                        while (target.toString().contains(temp)) {
                            temp = Integer.toString(rand.nextInt(10));
                        }
                        target.append(temp);
                    }
                }
                if (charLength > 0) {
                    String temp;
                    for (int i = 1; i <= length; i++) {
                        // random 0 and 1 --> number or character
                        int num = rand.nextInt(2);

                        switch (num) {
                            case 0: { // integer
                                temp = Integer.toString(rand.nextInt(10));
                                while (target.toString().contains(temp)) {
                                    temp = Integer.toString(rand.nextInt(10));
                                }
                                target.append(temp);
                                break;
                            }
                            case 1: { // char
                                int range = rand.nextInt(charLength - 1);
                                char character = (char) ('a' + range);
                                temp = Character.toString(character);
                                while (target.toString().contains(temp)) {
                                    range = rand.nextInt(charLength);
                                    character = (char) ('a' + range);
                                    temp = Character.toString(character);
                                }
                                target.append(temp);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }


                findBullsCows(target.toString(), scan, length);
            }
            // stage 6: exception handling
        } catch (Exception e) {
            System.out.println("Error: " + len + " isn't a valid number.");
        }

    }

    private static void findBullsCows(String target, Scanner scan, int length) {
        System.out.println("Turn 1:");
        int turnNumber = 1;

        int bulls = 0;
        int cows = 0;

        while (bulls != target.length()) {
            String str = scan.next();
            // count number of bulls and cows
            for (int i = 0; i < length; i++) {
                if (str.charAt(i) == target.charAt(i)) {
                    bulls++;
                } else if (str.contains(target.valueOf(i))) {
                    cows++;
                }
            }

            // results
            if (bulls == target.length()) {
                System.out.println("Grade: " + bulls + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
            }

            if (bulls == 0) {
                if (cows != 0) {
                    System.out.println("Grade: " + cows + " cow(s).");
                    turnNumber++;
                    bulls = 0;
                    cows = 0;
                } else {
                    System.out.println("Grade: None. Try again.");
                    turnNumber++;
                    bulls = 0;
                    cows = 0;
                }
            } else {
                if (cows == 0 && bulls != target.length()) {
                    System.out.println("Grade: " + bulls + " bull(s).");
                    turnNumber++;
                    bulls = 0;
                    cows = 0;
                } else if (bulls != target.length()) {
                    System.out.println("Grade: " + bulls + " bulls(s) and " + cows + " cow(s).");
                    turnNumber++;
                    bulls = 0;
                    cows = 0;
                }
            }
            System.out.println("Turn " + turnNumber + ":");
        }



    }
}

