package util; // Simplified package

import model.SudokuBoard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {
    public static SudokuBoard parse(String filePath) throws IOException {
        int[][] grid = new int[9][9];
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                String[] values = line.split(",");
                if (values.length != 9) throw new IllegalArgumentException("Invalid cols row " + row);
                for (int col = 0; col < 9; col++) {
                    grid[row][col] = Integer.parseInt(values[col].trim());
                }
                row++;
            }
            if (row != 9) throw new IllegalArgumentException("Invalid rows.");
        }
        return new SudokuBoard(grid);
    }
}