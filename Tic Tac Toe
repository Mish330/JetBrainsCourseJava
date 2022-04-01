package tictactoe;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        boardPrinter(arr); // print the board as per the "arr[]" filling
        coordinateX (sc, arr); // verify coordinates and update the array
        sc.close();
    }
    // print the Boards
    private static void boardPrinter(char[] arr) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", arr[0], arr[1], arr[2]);
        System.out.printf("| %c %c %c |%n", arr[3], arr[4], arr[5]);
        System.out.printf("| %c %c %c |%n", arr[6], arr[7], arr[8]);
        System.out.println("---------");
    }
    // verify game status by counting X, 0 and print a result
    private static int[] gameChecker (char[] arr, int cordXY, int[] xoNum) {
        int[] sumXO = {0, 0, 0, 0, 0, 0, 0, 0}; // sum in ASCII for 8: 3 horizontal, 3 vertical and 2 diagonal
        int[] counterXOwin = {0, 0}; // {X, O} win check
        sumXO[0] = arr[0] + arr[1] +  arr[2]; // horizontal 1 verifies X / O in ASCII
        sumXO[1] = arr[3] + arr[4] +  arr[5]; // horizontal 2 verifies X / O in ASCII
        sumXO[2] = arr[6] + arr[7] +  arr[8]; // horizontal 3 verifies X / O in ASCII
        sumXO[3] = arr[0] + arr[3] +  arr[6]; // vertical 1 verifies X / O in ASCII
        sumXO[4] = arr[1] + arr[4] +  arr[7]; // vertical 2 verifies X / O in ASCII
        sumXO[5] = arr[2] + arr[5] +  arr[8]; // vertical 3 verifies X / O in ASCII
        sumXO[6] = arr[0] + arr[4] +  arr[8]; // diagonal 1 verifies X / O in ASCII
        sumXO[7] = arr[6] + arr[4] +  arr[2]; // diagonal 2 verifies X / O in ASCII
        for (int i = 0; i < 8; i++) {
            if (sumXO[i] == 264) counterXOwin[0]++;
            if (sumXO[i] == 237) counterXOwin[1]++;
        }
        return counterXOwin;
    }
    // verify status and print the result
    private static boolean resultPrinter (int[] counterXOwin, int[] xoNum) {
        boolean endGame = false;
        String messageResult = "Game not finished";
        if (Math.abs(xoNum[0] - xoNum[1]) > 1 || counterXOwin[0] > 0 && counterXOwin[1] > 0) {
            messageResult = "Impossible";
            endGame = true;
        } else if (counterXOwin[0] == 1) {
            messageResult = "X wins";
            endGame = true;
        } else if (counterXOwin[1] == 1) {
            messageResult = "O wins";
            endGame = true;
        } else if (xoNum[0] + xoNum[1] == 9 && counterXOwin[0] == 0 && counterXOwin[1] == 0) {
            messageResult = "Draw";
            endGame = true;
        }
        System.out.println(messageResult);
        return endGame;
    }
    // verify coordinates and update the array
    private static void coordinateX (Scanner sc, char[] arr) {
        boolean strXYCheck = false; // boolean for logic to verify if coordinates are correct and can be accepted
        int x = 0;
        int y = 0;
        int cordXY = 0;
        int[] xoNum = {0,0};
        boolean endGame = false; // boolean for logic to verify if game is finished
        while (!endGame) {
            while (!strXYCheck) {
                System.out.print("Enter the coordinates: ");
                char[] strXY = sc.nextLine().toCharArray();
                if (strXY.length == 3 && (strXY[0] < 48 || strXY[0] > 58 || strXY[2] < 48 || strXY[2] > 58)) {
                    System.out.println("You should enter numbers!");
                } else {
                    if (strXY.length == 3 && strXY[0] >= 49 && strXY[0] <= 51 && strXY[2] >= 49 && strXY[2] <= 51) {
                        x = strXY[0] - 48;
                        y = strXY[2] - 48;
                        cordXY = 3 * (x - 1) + y - 1;
                        if (arr[cordXY] != 'X' && arr[cordXY] != 'O') {
                            if ((xoNum[0] + xoNum[1]) % 2 == 0) {
                                xoNum[0]++;
                                arr[cordXY] = 'X'; // update array with X on the board
                            } else if ((xoNum[0] + xoNum[1]) % 2 != 0) {
                                xoNum[1]++;
                                arr[cordXY] = 'O'; // update array with X on the board
                            }
                            strXYCheck = true;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                }
            }
            boardPrinter(arr); // print the updated board as per the "arr[]" filling
            endGame = resultPrinter (gameChecker (arr, cordXY, xoNum), xoNum);
            strXYCheck = false;
        }
    }
}
