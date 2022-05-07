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
        gamer1Field.setCoordinates();


    }
}

class Field {
    private final static String[] NUMBERS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final static char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    //private char[][]  shipsField = new char[10][10];
    private char[][]  shipsField;
    private boolean[][]  booleanShipsField;

    // Create initial field with empty signs '~'
    public void initializeField() {
        // Fill the matrix with '~'
        this.shipsField = new char[10][10];
        Arrays.stream(this.shipsField).forEach(a -> Arrays.fill(a, '~'));

        // Fill the logic matrix with 'true' which means that fields could be used for ships placement
        this.booleanShipsField = new boolean[10][10];
        Arrays.stream(this.booleanShipsField).forEach(a -> Arrays.fill(a, true));
    }

    // return start and end coordinates
    public int[][] getStartEnd(int[] coordinateInt1, int[] coordinateInt2) {
        int[] start = new int[2];
        int[] end = new int[2];

        start[0] = Math.min(coordinateInt1[0], coordinateInt2[0]); // Letter
        end[0] = Math.max(coordinateInt1[0], coordinateInt2[0]); // Letter
        start[1] = Math.min(coordinateInt1[1], coordinateInt2[1]); // Number
        end[1] = Math.max(coordinateInt1[1], coordinateInt2[1]); // Number

        start[0] = Math.max(start[0] - 1, 0); // Letter
        end[0] = Math.min(end[0] + 1, 9); // Letter
        start[1] = Math.max(start[1] - 1, 0); // Number
        end[1] = Math.min(end[1] + 1, 9); // Number

        //int[][] startEnd = new int[][]{start, end};
        return new int[][]{start, end};
    }

    // Update field matrix
    public void setUpdateField(int[] coordinateInt1, int[] coordinateInt2) {
        // Update booleanShipsField with "FALSE" around
        int[][] startEnd = getStartEnd (coordinateInt1, coordinateInt2); // coordinates with start and end are around ship
        // Make booleanShipsField array items 'false' around any ship
        for (int i = startEnd[0][0]; i <= startEnd[1][0]; i++) {
            for (int j = startEnd[0][1]; j <= startEnd[1][1]; j++) {
                this.booleanShipsField[i][j] = false;
            }
        }
        // Fill the cells of Ships matrix with 'O' ship symbol
        for (int i = coordinateInt1[0]; i <= coordinateInt2[0]; i++) {
            for (int j = coordinateInt1[1]; j <= coordinateInt2[1]; j++) {
                this.shipsField[i][j] = 'O';
                // this.booleanShipsField[i][j] = false;
            }
        }
        // print updated field
        printField();
        /*
        // Print Letters and booleanShips matrix
        for (int i = 0; i < 10; i++) {
            System.out.print(LETTERS[i]);
            for (boolean chShip : this.booleanShipsField[i]) {
                // System.out.printf(" %b", chShip);
                System.out.printf(" %s", chShip ? "t" : "F");
            }
            System.out.println("");
        } */
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

    // ***** Coordinate ex Class *******
    public void setCoordinates() throws IOException {
        // Try coordinates
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line; // reads the whole line with coordinates
            String[] strCoordinates; // a matrix with 2 coordinates in String format

            // *SERVICE* print type - cells - check if match
            for (Ship s : Ship.values()) {
                // Ask Gamer to enter new Ship coordinates
                System.out.printf( "%s %s %s %s %s%n","Enter the coordinates of the", s.type, "(", s.cells, "cells):");

                // Coordinates for the field initialization INPUT
                line = reader.readLine();
                strCoordinates = line.split(" ");

                // Make coordinates!!!!*****
                // transfer coordinates types to matrix style (A, 1) -> (0, 0) / (J, 10) -> (9, 9)
                // TO DO: arrange coordinates in ascending order
                int[] coordinateInt1 = CoordinateTransfer(strCoordinates[0]);
                int[] coordinateInt2 = CoordinateTransfer(strCoordinates[1]);
                
                // reassignment coordinate1 always MIN, coordinate2 always MAX
                int coord1Sum = coordinateInt1[0] + coordinateInt1[1];
                int coord2Sum = coordinateInt1[0] + coordinateInt1[1];
                int[] coordinateTemp = coordinateInt1;
                coordinateInt1 = coord1Sum < coord2Sum ? coordinateInt1 : coordinateInt2; // coordinate1 always MIN
                coordinateInt2 = coord1Sum > coord2Sum ? coordinateTemp : coordinateInt2; // coordinate2 always MAX

                // *SERVICE* print possibility result of the ship
                // System.out.print(s.cells + " ");
                // new input in int
                //System.out.println(CheckShips(coordinateInt1, coordinateInt2, s.cells));

                if (CheckShips(coordinateInt1, coordinateInt2, s.cells)) {
                    setUpdateField(coordinateInt1, coordinateInt2);
                }


            }
        }
    }

    // Transfer Letters to index in ships matrix[10][10]  (A, 1) -> (0, 0) / (J, 10) -> (9, 9)
    public int[] CoordinateTransfer(String coordinate) {
        // write coordinate number for Letter field
        int cor1 = Letter.valueOf(coordinate.substring(0,1).toUpperCase(Locale.ROOT)).numbs - 1;
        // write coordinate number for number field
        int cor2 = Integer.parseInt(coordinate.substring(1)) - 1;
        return new int[] {cor1, cor2};
    }

    // check if the ship coordinates are correct and returns boolean value
    public boolean CheckShips(int[] coordinateInt1, int[] coordinateInt2, int shipCells) {
        boolean possibleShip = false; // For ship length verification
        boolean possibleCoordinate = true; // For coordinates of ship placement verification
        int deltaLetters; // difference in coordinates "LETTERS"
        int deltaNumbers; // difference in coordinates "NUMBERS"
        // check ship length based on the input coordinates
        deltaLetters = Math.abs(coordinateInt1[0] - coordinateInt2[0]);
        deltaNumbers = Math.abs(coordinateInt1[1] - coordinateInt2[1]);
        int shipLength = deltaLetters + deltaNumbers + 1;

        // System.out.println("shipLength = " + shipLength);

        // Check possible coordinates in the booleanShipsField matrix
        for (int i = coordinateInt1[0]; i <= coordinateInt2[0]; i++) {
            for (int j = coordinateInt1[1]; j <= coordinateInt2[1]; j++) {
                if (!this.booleanShipsField[i][j]) {
                    possibleCoordinate = false;
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    i = coordinateInt2[0] + 1; // breaks i cycle
                    break;
                }
            }
        }

        // check if the ship coordinates are correct
        if (shipLength == shipCells && (deltaLetters == 0 || deltaNumbers == 0)) {
            possibleShip = true;
        }

        // Return result of Ship length and possible coordinates verification
        return possibleShip && possibleCoordinate;
    }

}
