package validator;


public final class AssetValidators {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class is designed to contain only static nested validator classes
     * for asset-related validation operations.
     *
     * @throws UnsupportedOperationException implicitly if reflection is used to instantiate
     */
    private AssetValidators() {  }


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


    public static class YearsValidator implements InputValidator<Integer> {
        @Override
        public void validate(Integer years) {
            if (years == null || years < 0) {
                throw new IllegalArgumentException(
                        "Los anios de vida util no pueden ser negativos. Recibidos: " + years);
            }
        }
    }
}
