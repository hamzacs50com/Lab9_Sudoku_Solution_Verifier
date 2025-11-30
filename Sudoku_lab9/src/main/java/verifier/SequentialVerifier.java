package verifier; // Simplified package

import model.SudokuBoard;
import model.ValidationResult;

public class SequentialVerifier extends BaseVerifier {
    @Override
    public ValidationResult verify(SudokuBoard board) {
        ValidationResult result = new ValidationResult();
        for (int i = 0; i < 9; i++) checkArray("ROW", i, board.getRow(i), result);
        for (int i = 0; i < 9; i++) checkArray("COL", i, board.getCol(i), result);
        for (int i = 0; i < 9; i++) checkArray("BOX", i, board.getBox(i), result);
        return result;
    }
}
