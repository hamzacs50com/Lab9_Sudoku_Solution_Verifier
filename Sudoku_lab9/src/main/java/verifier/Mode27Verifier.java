package verifier; // Simplified package

import model.SudokuBoard;
import model.ValidationResult;
import java.util.ArrayList;
import java.util.List;

public class Mode27Verifier extends BaseVerifier {
    @Override
    public ValidationResult verify(SudokuBoard board) {
        ValidationResult result = new ValidationResult();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            final int index = i;
            threads.add(new Thread(() -> checkArray("ROW", index, board.getRow(index), result)));
            threads.add(new Thread(() -> checkArray("COL", index, board.getCol(index), result)));
            threads.add(new Thread(() -> checkArray("BOX", index, board.getBox(index), result)));
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) { try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); } }
        return result;
    }
}
