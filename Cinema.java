package cinema;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
public class Cinema {
    /* stage 1: basic schematics
    public static void main(String[] args) {
        System.out.println("Cinema:\n" +
                "  1 2 3 4 5 6 7 8\n" +
                "1 S S S S S S S S\n" +
                "2 S S S S S S S S\n" +
                "3 S S S S S S S S\n" +
                "4 S S S S S S S S\n" +
                "5 S S S S S S S S\n" +
                "6 S S S S S S S S\n" +
                "7 S S S S S S S S");
    }

     */

    /* stage 3: seat occupation and ticket price retrieval
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();

        // initial board
        char[][] sAndB = new char[rows + 1][seats * 2 + 1];
        sAndB[0][0] = ' ';
        for (int i = 1; i < rows + 1; i++) {
            sAndB[i][0] = (char)(i + '0');
            for (int j = 1; j < seats * 2 + 1; j++) {
                sAndB[i][j] = 'S';
                sAndB[0][j] = (char)(j / 2 + '0');
                if (j % 2 != 0) {
                    sAndB[0][j] = ' ';
                    sAndB[i][j] = ' ';
                }
            }
        }

        // output initial board
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats * 2 + 1; j++) {
                System.out.print(sAndB[i][j]);
            }
            System.out.println();
        }

        // ticket price
        System.out.println("Enter a row number:");
        int rowNo = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNo = scan.nextInt();

     */

        /* for stage 2
        int income;
        if (rowNo * seatNo <= 60) {
            income = rowNo * seatNo * 10;
        } else {
            income = (rowNo / 2 * seatNo * 10) + ((rowNo - rowNo / 2) * seatNo * 8);
        }
        System.out.println("Total income: \n" +
                "$" + income);

         */
/*
        // ticket price
        int ticketPrice;
        if (rows * seats < 60) {
            ticketPrice = 10;
        } else {
            if (rowNo <= rows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.println("Ticket price: $" + ticketPrice);

        // final board with B
        sAndB[rowNo][seatNo * 2] = 'B';
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats * 2 + 1; j++) {
                System.out.print(sAndB[i][j]);
            }
            System.out.println();
            */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seats = scan.nextInt();
        System.out.println();
        int rowNo = 0;
        int seatNo = 0;

        // income
        int income = 0;

        // menu
        menu();

        int option = scan.nextInt();
        char[][] sAndB = seatArrangement(rows, seats);
        while(option != 0) {
            if (option == 1) {
                // output plan
                System.out.println("Cinema:");
                for (int i = 0; i < rows + 1; i++) {
                    for (int j = 0; j < seats * 2 + 1; j++) {
                        System.out.print(sAndB[i][j]);
                    }
                    System.out.println();
                }
                menu();
            }

            if (option == 2) {

                // ask for row and seat number
                System.out.println("Enter a row number:");
                rowNo = scan.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNo = scan.nextInt();

                while (rowNo > rows || seatNo > seats) {
                    System.out.println("Wrong input! \n");
                    System.out.println("Enter a row number:");
                    rowNo = scan.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNo = scan.nextInt();
                }

                boolean success = false;
                while (!success) {
                    try {
                        while (sAndB[rowNo][seatNo * 2] == 'B') {
                            System.out.println("That ticket has already been purchased! \n");
                            System.out.println("Enter a row number:");
                            rowNo = scan.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seatNo = scan.nextInt();
                        }
                        success = true;
                    } catch (Exception e) {
                        System.out.println("Wrong input! \n");
                        System.out.println("Enter a row number:");
                        rowNo = scan.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNo = scan.nextInt();
                    }
                }


                // mark the seat
                sAndB = updateCinemaPlan(rowNo, seatNo, sAndB);


                // ticket price
                int ticketPrice;
                if (rows * seats < 60) {
                    ticketPrice = 10;
                } else {
                    if (rowNo <= rows / 2) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                }
                System.out.println("Ticket price: $" + ticketPrice + "\n");
                income += ticketPrice;

                menu();

            }

            if (option == 3) {
                statistics(rows, seats, sAndB, income);
                menu();
            }
            option = scan.nextInt();
        }
    }

    // menu function
    public static void menu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    // show the seats
    public static char[][] seatArrangement(int rows, int seats){
        char[][] sAndB = new char[rows + 1][seats * 2 + 1];
        sAndB[0][0] = ' ';
        for (int i = 1; i < rows + 1; i++) {
            sAndB[i][0] = (char) (i + '0');
            for (int j = 1; j < seats * 2 + 1; j++) {
                sAndB[i][j] = 'S';
                sAndB[0][j] = (char) (j / 2 + '0');
                if (j % 2 != 0) {
                    sAndB[0][j] = ' ';
                    sAndB[i][j] = ' ';
                }
            }
        }

        return sAndB;

    }

    public static char[][] updateCinemaPlan(int rowNo, int seatNo, char[][] sAndB) {
        // update cinema plan
        sAndB[rowNo][seatNo * 2] = 'B';
        return sAndB;
    }

    public static void statistics(int rows, int seats, char[][] sAndB, int income) {
        // read through sAndB, find number of B
        int counter = 0; // number of purchased tickets
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats * 2 + 1; j++) {
                if (sAndB[i][j] == 'B') {
                    counter++;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + counter);

        // % of tickets sold
        double percentage = counter / ((double)rows * (double)seats) * 100.0;
        BigDecimal bd = new BigDecimal(Double.toString(percentage));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Percentage: " + bd + "%");
        
        // current income
        System.out.println("Current income: " + "$" + income);

        // total income
        int totalIncome;
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = (rows / 2 * seats * 10) + ((rows - rows / 2) * seats * 8);
        }
        System.out.println("Total income: " + "$" + totalIncome);
    }
}
