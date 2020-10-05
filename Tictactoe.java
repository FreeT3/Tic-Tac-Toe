package tictactoe;

import java.util.*;

public class Tictactoe {
    public static void main(String[] args) {
        /* for stage 1
        System.out.println("X O X");
        System.out.println("O X O");
        System.out.println("X X O");
        */

        /* Stage 2: outputting a board

        // outputting the first line //
        System.out.println("---------");

        // reading outputs to separate lines //
        Scanner scanner = new Scanner(System.in);
        String original = scanner.next();
        for (int i = 0; i < 9; i++) {
            char char1 = original.charAt(i);
            char char2 = original.charAt(++i);
            char char3 = original.charAt(++i);
            char[] arrayOfThree = {'|', char1, char2, char3, '|'};
            String strTic = Arrays.toString(arrayOfThree);
            String strTicFin = strTic.replace(",", "").replace("[", "").replace("]", "");
            System.out.println(strTicFin);
        }
        System.out.println("---------");

         */

        // set up the scanner
        Scanner scanner = new Scanner(System.in);

        //initial state

        // setting up the matrix
        char k = 32;
        char[][] board = new char [3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = k;
            }
        }

        // outputting empty matrix
        System.out.println("---------"); // first line
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        // setting up the parameters
        int yCoordinate;
        int xCoordinate;

        // ask for coordinates
        System.out.println("Enter the coordinates:");

        // setting up the loop structure
        outerLoop:
        for (int i = 1; i <= 9; i++) {

            // check if inputs integer or not
            while (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates:");
                yCoordinate = scanner.nextInt();
                xCoordinate = scanner.nextInt();
            }

            // when scanner.hasNextInt()
            yCoordinate = scanner.nextInt();
            xCoordinate = scanner.nextInt();

            // check if within 1 and 3
            while (yCoordinate > 3 || xCoordinate > 3 || yCoordinate < 1 || xCoordinate < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.println("Enter the coordinates:");
                yCoordinate = scanner.nextInt();
                xCoordinate = scanner.nextInt();
            }

            // turning inputs into correct matrix indices
            int yReal = yCoordinate - 1;
            int xReal = Math.abs(xCoordinate - 3);

            // check if cell occupied
            while (board[xReal][yReal] != 32) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.println("Enter the coordinates:");
                yCoordinate = scanner.nextInt();
                xCoordinate = scanner.nextInt();
                while (yCoordinate > 3 || xCoordinate > 3 || yCoordinate < 1 || xCoordinate < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.println("Enter the coordinates:");
                    yCoordinate = scanner.nextInt();
                    xCoordinate = scanner.nextInt();
                }
                yReal = yCoordinate - 1;
                xReal = Math.abs(xCoordinate - 3);
            }

            // putting in X or O
            if (i % 2 != 0) {
                board[xReal][yReal] = 88;
            } else {
                board[xReal][yReal] = 79;
            }

            // output the new matrix
            System.out.println("---------"); // first line
            for (int a = 0; a < 3; a++) {
                System.out.print("| ");
                for (int b = 0; b < 3; b++) {
                    System.out.print(board[a][b] + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

            // count X and O for DRAW status
            int countX = 0;
            int countO = 0;
            for (int c = 0; c < 3; c++) {
                for (int d = 0; d < 3; d++) {
                    if (board[c][d] == 'X') {
                        countX++;
                    }
                    if (board[c][d] == 'O') {
                        countO++;
                    }
                }
            }

            // check board status
            int leftDiag = board[0][0] + board[1][1] + board[2][2];
            int rightDiag = board[0][2] + board[1][1] + board[2][0];
            if (leftDiag == 264 || rightDiag == 264) {
                System.out.println("X wins");
                break;
            } else if (leftDiag == 237 || rightDiag == 237) {
                System.out.println("O wins");
                break;
            } else {
                // horizontal & vertical
                int sumH = 0;
                int sumV = 0;
                for (int m = 0; m < 3; m++) {
                    for (int j = 0; j < 3; j++) {
                        sumH += board[m][j];
                        sumV += board[j][m];
                    }
                    if (sumH == 264 || sumV == 264) {
                        System.out.println("X wins");
                        break outerLoop;
                    } else if (sumH == 237 || sumV == 237) {
                        System.out.println("O wins");
                        break outerLoop;
                    }
                    sumH = 0;
                    sumV = 0;
                }
                if (countX + countO == 9) {
                    System.out.println("Draw");
                    break;
                }
            }




        /* checking board status for previous stages

        // difference of XO
        int countX = 0;
        int countO = 0;
        boolean xWin = false;
        boolean oWin = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                }
                if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }
        if (Math.abs(countX - countO) > 1) {
            System.out.println("Impossible");
        } else {
            // diagonals

            }
        }

        */
        }
    }
}