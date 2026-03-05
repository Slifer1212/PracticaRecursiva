package ui;

import model.Asset;
import validator.AssetValidators;
import validator.InputValidator;

import java.util.Scanner;


public class ConsoleAssetInputReader implements AssetInputReader {

    /**
     * Scanner instance used to read user input from the console.
     * This scanner reads lines of text that are then parsed and validated
     * for asset information and calculation parameters.
     */
    private final Scanner                    scanner;
    /**
     * Validator for the purchase value of an asset. This validator ensures that the
     * purchase value entered by the user meets the required constraints before being
     * used to create an Asset instance.
     */
    private final InputValidator<Double>     purchaseValueValidator;
    /**
     * Validator used to ensure that depreciation rate values entered by the user
     * meet the required business rules and constraints before being used in asset
     * depreciation calculations.
     */
    private final InputValidator<Double>     rateValidator;
    /**
     * Validator used to verify that the number of years input for asset depreciation
     * calculation is valid according to business rules. Ensures the years value meets
     * the necessary constraints before being used in depreciation calculations.
     */
    private final InputValidator<Integer>    yearsValidator;

    /**
     * Constructs a ConsoleAssetInputReader with the specified scanner for reading user input from the console.
     * Initializes validators for purchase value, depreciation rate, and years to ensure input data integrity.
     *
     * @param scanner the Scanner instance used to read input from the console
     */
    public ConsoleAssetInputReader(Scanner scanner) {
        this.scanner                = scanner;
        this.purchaseValueValidator = new AssetValidators.PurchaseValueValidator();
        this.rateValidator          = new AssetValidators.DepreciationRateValidator();
        this.yearsValidator         = new AssetValidators.YearsValidator();
    }

    /**
     *
     */
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

    /**
     * Reads and validates the number of useful life years for an asset from console input.
     * Prompts the user to enter a non-negative integer representing the years of useful life.
     * The input is validated using the configured years validator and will repeatedly prompt
     * until a valid value is provided.
     *
     * @return the validated number of years as a non-negative integer
     */
    @Override
    public int readYears() {
        return readValidInt(
                "  Anios de vida util (t >= 0)    : ",
                yearsValidator);
    }

    // ── Helpers privados ─────────────────────────────────────────────────────

    /**
     * Reads and validates a double value from user input.
     * Continuously prompts the user until a valid double value that passes validation is entered.
     * Handles invalid number formats and validation errors by displaying error messages and re-prompting.
     *
     * @param prompt the message to display when prompting the user for input
     * @param validator the validator to apply to the parsed double value
     * @return the validated double value entered by the user
     */
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

    /**
     *
     */
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
