package verifier; // Simplified package

import model.SudokuBoard;
import model.ValidationResult;
import java.util.ArrayList;
import java.util.List;

public class Mode3Verifier extends BaseVerifier {
    @Override
    public ValidationResult verify(SudokuBoard board) {
        ValidationResult result = new ValidationResult();
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(() -> { 
            for (int i = 0; i < 9; i++) checkArray("ROW", i, board.getRow(i), result); 
        }));
        threads.add(new Thread(() -> { 
            for (int i = 0; i < 9; i++) checkArray("COL", i, board.getCol(i), result); 
        }));
        threads.add(new Thread(() -> { 
            for (int i = 0; i < 9; i++) checkArray("BOX", i, board.getBox(i), result); 
        }));

        for (Thread t : threads) t.start();
        for (Thread t : threads) { try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); } }
        return result;
    }
}
