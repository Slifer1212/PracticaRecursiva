package ui;

import model.Asset;
import validator.AssetValidators;
import validator.InputValidator;

import java.util.Scanner;


public class ConsoleAssetInputReader implements AssetInputReader {

    private final Scanner                    scanner;
    private final InputValidator<Double>     purchaseValueValidator;
    private final InputValidator<Double>     rateValidator;
    private final InputValidator<Integer>    yearsValidator;

    public ConsoleAssetInputReader(Scanner scanner) {
        this.scanner                = scanner;
        this.purchaseValueValidator = new AssetValidators.PurchaseValueValidator();
        this.rateValidator          = new AssetValidators.DepreciationRateValidator();
        this.yearsValidator         = new AssetValidators.YearsValidator();
    }

    @Override
    public Asset readAsset() {
        System.out.print("  Nombre del activo            : ");
        String name = scanner.nextLine().trim();

        double purchaseValue = readValidDouble(
                "  Valor de compra original (V0): $ ",
                purchaseValueValidator);

        double depreciationRate = readValidDouble(
                "  Tasa de depreciacion anual (d): ",
                rateValidator);

        return new Asset.Builder()
                .name(name)
                .purchaseValue(purchaseValue)
                .depreciationRate(depreciationRate)
                .build();
    }

    @Override
    public int readYears() {
        return readValidInt(
                "  Anos de vida util (t >= 0)    : ",
                yearsValidator);
    }

    // ── Helpers privados ─────────────────────────────────────────────────────

    private double readValidDouble(String prompt, InputValidator<Double> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                validator.validate(value);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    private int readValidInt(String prompt, InputValidator<Integer> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                validator.validate(value);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero entero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }
}
