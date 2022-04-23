import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

enum Ship {
    AIRCRAFT(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    int cells; //Enum Field with number of occupied cells
    String type; //Enum Field with type of the ship for text messages

    Ship (int cells, String type)//Private constructor
    {
        this.cells = cells;
        this.type = type;
    }
}

enum Letter {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10);

    int numbs;

    Letter (int numbs) {
        this.numbs = numbs;
    }
}


class Main {
    public static void main(String[] args) throws Exception {

        Field gamer1Field = new Field();
        // initialize Field matrix with a 'fog of war' symbol
        gamer1Field.initializeField();
        // print initialized Field with 'fog of war' symbols
        gamer1Field.printField();
        // set coordinates for all ships
        Coordinate.setCoordinates();

    }
}

class Coordinate {

    public static void setCoordinates() throws IOException {
        // Try coordinates
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line; // reads the whole line with coordinates
            String[] strCoordinates; // a matrix with 2 coordinates in String format

            // *SERVICE* print type - cells - check if match
            for (Ship s : Ship.values()) {
                // Ask Gamer to enter new Ship coordinates
                System.out.printf( "%s %s %s %s %s%n","Enter the coordinates of the", s.type, "(", s.cells, "cells):");

                // Coordinates for the field initialization
                line = reader.readLine();
                strCoordinates = line.split(" ");

                // Make coordinates!!!!*****
                // transfer coordinates types to matrix style (A, 1) -> (0, 0) / (J, 10) -> (9, 9)
                int[] coordinateInt1 = CoordinateTransfer(strCoordinates[0]);
                int[] coordinateInt2 = CoordinateTransfer(strCoordinates[1]);

                // *SERVICE* print possibility result of the ship
                System.out.print(s.cells + " ");
                // new input in int
                System.out.println(CheckShips(coordinateInt1, coordinateInt2, s.cells));

            }

        }
    }


    // Transfer Letters to index in ships matrix[10][10]  (A, 1) -> (0, 0) / (J, 10) -> (9, 9)
    public static int[] CoordinateTransfer(String coordinate) {
        // write coordinate number for Letter field
        int cor1 = Letter.valueOf(coordinate.substring(0,1).toUpperCase(Locale.ROOT)).numbs;
        // write coordinate number for number field
        int cor2 = Integer.parseInt(coordinate.substring(1));

        return new int[] {cor1, cor2};
    }

    // check if the ship coordinates are correct and returns boolean value
    public static boolean CheckShips(int[] coordinateInt1, int[] coordinateInt2, int shipCells) {
        boolean possibleShip = false;

        int deltaLetters; // difference in coordinates "LETTERS"
        int deltaNumbers; // difference in coordinates "NUMBERS"

        // check ship length based on the input coordinates
        deltaLetters = Math.abs(coordinateInt1[0] - coordinateInt2[0]);
        deltaNumbers = Math.abs(coordinateInt1[1] - coordinateInt2[1]);
        int shipLength = deltaLetters + deltaNumbers + 1;

        System.out.println("shipLength = " + shipLength);

        // check if the ship coordinates are correct
        if (shipLength == shipCells && (deltaLetters == 0 || deltaNumbers == 0)) {
            possibleShip = true;
        }

        return possibleShip;
    }

}


class Field {
    private final static String[] NUMBERS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final static char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    //private char[][]  shipsField = new char[10][10];
    private char[][]  shipsField;


    private int coordinateNumb;
    private int coordinateLtr;
    
    
    /* public Field () {
        this.shipsField = shipsField;
    } */

    // Create initial field with empty signs '~'
    public void initializeField() {
        // Fill the array with '~'
        this.shipsField = new char[10][10];
        Arrays.stream(this.shipsField).forEach(a -> Arrays.fill(a, '~'));
    }

    // Update field matrix
    public static void setUpdatesField(int corNum1) {

    }

    // Print a game Field
    public void printField() {
        System.out.print(" ");
        // Print top row with numbers
        for (String ch : NUMBERS) {
            System.out.print(" ");
            System.out.print(ch);
        }
        System.out.println();

        // Print Letters and ships / fog
        for (int i = 0; i < 10; i++) {
            System.out.print(LETTERS[i]);
            for (char chShip : this.shipsField[i]) {
                System.out.printf(" %c", chShip);
            }
            System.out.println("");
        }

    }
}
