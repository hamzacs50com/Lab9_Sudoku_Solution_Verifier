package verifier; // Simplified package

import model.SudokuBoard;
import model.ValidationResult;

public interface Verifier {
    ValidationResult verify(SudokuBoard board);
}