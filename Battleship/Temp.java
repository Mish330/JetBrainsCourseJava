class Field {
  
    //private char[][]  shipsField = new char[10][10];
    private char[][] shipsField; 
    private boolean[][] booleanShipsField; 
    
    // Create initial field with empty signs '~'
    public void initializeField() { } 
    
    // Print a game Field
    public void printField() {} 

     // Update field matrix
    public void setUpdateField(int[] coordinateInt1, int[] coordinateInt2) {} 

}

class Coordinates {

    // Input coordinates from Players
    public void setCoordinates() throws IOException {} 

    // return start and end coordinates
    public int[][] getStartEnd(int[] coordinateInt1, int[] coordinateInt2) {}

    // Transfer Letters to index in ships matrix[10][10]  (A, 1) -> (0, 0) / (J, 10) -> (9, 9)
    public int[] CoordinateTransfer(String coordinate) {} 

    // check if the ship coordinates are correct and returns boolean value
    public boolean CheckShips(int[] coordinateInt1, int[] coordinateInt2, int shipCells) {} 

}

class Ships {

}
