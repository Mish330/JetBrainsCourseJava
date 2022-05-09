/**
* Sample by Arseniy Lachimov
https://hyperskill.org/projects/125/stages/663/implement#solutions
*/

package Battleship;

import java.util.*;

class Main {
    public static void main(String[] args) {

        Ship[] ships = {new Ship("Destroyer", 2),
                        new Ship("Cruiser", 3),
                        new Ship("Submarine", 3),
                        new Ship("Battleship", 4),
                        new Ship("Aircraft Carrier", 5)};
        Battlefield myBattlefield = new Battlefield(ships);

        GamePlay.PlacementOfShips(myBattlefield);
    }
}

class GamePlay extends GameRules {
    static Scanner scanner = new Scanner(System.in);

    public static void PlacementOfShips(Battlefield myBattlefield) {
        myBattlefield.createBattlefield();
        myBattlefield.printBattlefield();

        while (myBattlefield.getUnplacedShips() != 0) {

            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n",
                    myBattlefield.getShip().getNAME(), myBattlefield.getShip().getSIZE());

            while (true) {
                try {
                    myBattlefield.getShip().setDeckCoordinates(scanner.nextLine().toUpperCase(Locale.ROOT).split(" "));

                    CheckShipPlacementRules(myBattlefield);

                    myBattlefield.placeShipOnBattlefield();
                    myBattlefield.printBattlefield();
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage().contains("Error") ? "\n" + e.getMessage()
                            : "\n" + new Exception(String.format("Error! %s. Try again:" + "\n",
                            e.getLocalizedMessage())).getMessage());
                }
            }
        }
        scanner.close();
    }
}

class Battlefield {
    private final char[][] BATTLEFIELD = new char[12][12];;
    private final Ship[] SHIPS;
    private int unplacedShips;

    public Battlefield(Ship[] ships) {
        SHIPS = ships.clone();
        this.unplacedShips = ships.length;
    }

    public char[][] getBATTLEFIELD() {
        return BATTLEFIELD;
    }

    public Ship getShip() {
        return this.SHIPS[this.unplacedShips - 1];
    }

    public int getUnplacedShips() {
        return this.unplacedShips;
    }

    public void createBattlefield() {
        for (int i = 1; i < BATTLEFIELD.length - 1; i++) {
            for (int j = 1; j < BATTLEFIELD.length - 1; j++) {
                BATTLEFIELD[i][j] = '~';
            }
        }
    }

    public void printBattlefield() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < BATTLEFIELD.length - 1; i++) {
            System.out.print((char) ('@' + i) + " ");
            for (int j = 1; j < BATTLEFIELD.length - 1; j++) {
                System.out.print(j == BATTLEFIELD.length - 2 ? BATTLEFIELD[i][j] + "\n" : BATTLEFIELD[i][j] + " ");
            }
        }
    }

    public void placeShipOnBattlefield() {
        for (int i = 0; i < getShip().getDeckCoordinates()[0].length; i++) {
            BATTLEFIELD[getShip().getDeckCoordinates()[0][i]][getShip().getDeckCoordinates()[1][i]] = 'O';
        }
        this.unplacedShips--;
    }
}

class Ship {
    private final String NAME;
    private final int SIZE;
    private int[][] deckCoordinates;

    public Ship(String name, int size) {
        NAME = name;
        SIZE = size;
    }

    public String getNAME() {
        return NAME;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setDeckCoordinates(String[] coordinates) {

        int[] row = {coordinates[0].charAt(0) - '@', coordinates[1].charAt(0) - '@'};
        int[] col = {Integer.parseInt(coordinates[0].substring(1)), Integer.parseInt(coordinates[1].substring(1))};

        this.deckCoordinates = new int[2][Math.abs((row[0] + col[0]) - (row[1] + col[1])) + 1];

        for (int i = 0; i < this.deckCoordinates[0].length; i++) {
            this.deckCoordinates[0][i] = row[0] == row[1] ? Math.min(row[0], row[1]) : Math.min(row[0], row[1]) + i;
            this.deckCoordinates[1][i] = row[0] == row[1] ? Math.min(col[0], col[1]) + i : Math.min(col[0], col[1]);
        }
    }

    public int[][] getDeckCoordinates() {
        return this.deckCoordinates;
    }
}

class GameRules {
    public static void CheckShipPlacementRules(Battlefield myBattlefield) throws Exception {
        int[][] d = myBattlefield.getShip().getDeckCoordinates();
        if (d[0][0] != d[0][d[0].length - 1] && d[1][0] != d[1][d[0].length - 1]
                || d[0][d[0].length - 1] > 10 || d[1][d[0].length - 1] > 10) {
            throw new Exception("Error! Wrong ship location! Try again:\n");
        } else if (myBattlefield.getShip().getSIZE() != d[0].length) {
            throw new Exception(String.format("Error! Wrong length of the %s! Try again:\n", myBattlefield.getShip().getNAME()));
        } else {
            for (int i = 0; i < d[0].length; i++) {
                for (int j = d[0][i] - 1; j <= d[0][i] + 1; j++) {
                    for (int l = d[1][i] - 1; l <= d[1][i] + 1; l++) {
                        if (myBattlefield.getBATTLEFIELD()[j][l] == 'O') {
                            throw new Exception("Error! You placed it too close to another one. Try again:\n");
                        }
                    }
                }
            }
        }
    }
}
