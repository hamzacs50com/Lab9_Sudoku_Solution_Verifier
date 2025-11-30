package verifier; // Simplified package

import model.ValidationResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale;

public abstract class BaseVerifier implements Verifier {
    protected void checkArray(String type, int index, int[] data, ValidationResult result) {
        Map<Integer, List<Integer>> positionsMap = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            positionsMap.putIfAbsent(data[i], new ArrayList<>());
            positionsMap.get(data[i]).add(i + 1);
        }
        for (Map.Entry<Integer, List<Integer>> entry : positionsMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                String errorMsg = String.format(Locale.US, "%s %d, #%d, %s", 
                    type, index + 1, entry.getKey(), entry.getValue().toString());
                result.addError(errorMsg);
            }
        }
    }
}
