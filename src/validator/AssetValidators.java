package validator;


public final class AssetValidators {

    private AssetValidators() { /* Utility class – no instanciar */ }

    // ── Validador de valor de compra ─────────────────────────────────────────

    public static class PurchaseValueValidator implements InputValidator<Double> {
        @Override
        public void validate(Double value) {
            if (value == null || value <= 0) {
                throw new IllegalArgumentException(
                        "El valor de compra debe ser mayor a cero. Recibido: " + value);
            }
        }
    }


    public static class DepreciationRateValidator implements InputValidator<Double> {
        private static final double MIN_RATE =  0.01;
        private static final double MAX_RATE = 99.99;

        @Override
        public void validate(Double rate) {
            if (rate == null || rate < MIN_RATE || rate > MAX_RATE) {
                throw new IllegalArgumentException(String.format(
                        "La tasa de depreciacion debe estar entre %.2f%% y %.2f%%. Recibida: %.2f%%",
                        MIN_RATE, MAX_RATE, rate));
            }
        }
    }

    // ── Validador de años ────────────────────────────────────────────────────

    public static class YearsValidator implements InputValidator<Integer> {
        @Override
        public void validate(Integer years) {
            if (years == null || years < 0) {
                throw new IllegalArgumentException(
                        "Los anos de vida util no pueden ser negativos. Recibidos: " + years);
            }
        }
    }
}
