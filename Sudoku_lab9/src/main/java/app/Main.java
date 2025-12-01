package app;

import model.SudokuBoard;
import model.ValidationResult;
import util.CSVParser;
import verifier.Verifier;
import verifier.VerifierFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar target/Sudoku_lab9-1.0-SNAPSHOT.jar <csv_filepath> <mode>");
            System.out.println("Modes: 0 (Sequential), 3 (4 Threads), 27 (28 Threads)");
            return;
        }

        String filePath = args[0];
        int mode;

        try {
            mode = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Mode must be an integer (0, 3, or 27).");
            return;
        }

        try {
            SudokuBoard board = CSVParser.parse(filePath);
            Verifier verifier = VerifierFactory.getVerifier(mode);
            ValidationResult result = verifier.verify(board);

            if (result.isValid()) {
                System.out.println("VALID");
            } else {
                System.out.println("INVALID");
                System.out.println();
                result.printErrors();
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}