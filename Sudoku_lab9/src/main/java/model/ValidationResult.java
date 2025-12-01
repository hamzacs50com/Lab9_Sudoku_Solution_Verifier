package model; // Simplified package

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidationResult {
    private final List<String> errors = new CopyOnWriteArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void printErrors() {
        List<String> sortedErrors = new ArrayList<>(errors);
        
        // Requirements: Sort Rows -> Cols -> Boxes
        Collections.sort(sortedErrors, (a, b) -> {
            int typeA = getTypeWeight(a);
            int typeB = getTypeWeight(b);
            if (typeA != typeB) return typeA - typeB;
            return a.compareTo(b);
        });

        boolean printingRows = true;
        boolean printingCols = false;
        boolean printingBoxes = false;

        for (String err : sortedErrors) {
            if (printingRows && err.startsWith("COL")) {
                System.out.println("------------------------------------------");
                printingRows = false;
                printingCols = true;
            }
            if ((printingRows || printingCols) && err.startsWith("BOX")) {
                if (printingRows || printingCols) System.out.println("------------------------------------------");
                printingRows = false;
                printingCols = false;
                printingBoxes = true;
            }
            System.out.println(err);
        }
    }
    
    private int getTypeWeight(String s) {
        if (s.startsWith("ROW")) return 1;
        if (s.startsWith("COL")) return 2;
        if (s.startsWith("BOX")) return 3;
        return 4;
    }
}
