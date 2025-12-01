package verifier; // Simplified package

public class VerifierFactory {
    public static Verifier getVerifier(int mode) {
        switch (mode) {
            case 0: return new SequentialVerifier();
            case 3: return new Mode3Verifier();
            case 27: return new Mode27Verifier();
            default: throw new IllegalArgumentException("Invalid mode.");
        }
    }
}