package model; // Simplified package

public class SudokuBoard {
    private final int[][] grid;

    public SudokuBoard(int[][] grid) {
        if (grid.length != 9 || grid[0].length != 9) {
            throw new IllegalArgumentException("Board must be 9x9");
        }
        this.grid = grid;
    }

    public int getValue(int row, int col) { return grid[row][col]; }
    public int[] getRow(int row) { return grid[row]; }
    public int[] getCol(int col) {
        int[] column = new int[9];
        for (int i = 0; i < 9; i++) { column[i] = grid[i][col]; }
        return column;
    }
    public int[] getBox(int boxIndex) {
        int[] box = new int[9];
        int startRow = (boxIndex / 3) * 3;
        int startCol = (boxIndex % 3) * 3;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { box[k++] = grid[startRow + i][startCol + j]; }
        }
        return box;
    }
}