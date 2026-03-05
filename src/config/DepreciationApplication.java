package config;

import model.Asset;
import model.DepreciationReport;
import service.DepreciationCalculator;
import ui.AssetInputReader;
import ui.DepreciationReporter;


/**
 *
 */
public class DepreciationApplication {

    /**
     * Reader responsible for collecting asset data and calculation parameters from the input source.
     * This component handles all input operations required by the depreciation application,
     * including reading asset information and the number of years for depreciation calculation.
     */
    private final AssetInputReader inputReader;
    /**
     * Calculator responsible for computing depreciation values and generating depreciation reports.
     * This component performs the core business logic for asset depreciation calculations using
     * the declining balance method. It is injected as a dependency to enable different calculation
     * strategies and facilitate testing.
     */
    private final DepreciationCalculator calculator;
    /**
     * Component responsible for displaying the depreciation report to the user.
     * This reporter handles the presentation of the calculated depreciation data,
     * including yearly records and summary information.
     */
    private final DepreciationReporter reporter;

    /**
     * Constructs a new DepreciationApplication with the specified dependencies.
     * This constructor initializes the application with the necessary components to read asset data,
     * calculate depreciation, and report the results.
     *
     * @param inputReader the reader responsible for obtaining asset information and evaluation period from the user
     * @param calculator  the calculator that performs depreciation computations and generates reports
     * @param reporter    the reporter that handles the presentation of depreciation results
     */
    public DepreciationApplication(
            AssetInputReader inputReader,
            DepreciationCalculator calculator,
            DepreciationReporter reporter) {

        this.inputReader = inputReader;
        this.calculator = calculator;
        this.reporter = reporter;
    }

    /**
     * Executes the main workflow of the depreciation application.
     * <p>
     * This method orchestrates the complete depreciation calculation process by:
     * printing an application banner, reading asset information and time period from the user,
     * generating a depreciation report using the configured calculator, and displaying
     * the results through the configured reporter.
     *
     * @throws RuntimeException if any error occurs during input reading, calculation, or report display
     */
    public void run() {
        printBanner();

        Asset asset = inputReader.readAsset();
        int years = inputReader.readYears();

        DepreciationReport report = calculator.generateReport(asset, years);

        reporter.displayReport(report);
    }

    /**
     * Prints the application banner and input prompt header to the console.
     * <p>
     * Displays a formatted header with the title "CALCULO DE DEPRECIACION POR SALDO DECRECIENTE"
     * (Declining Balance Depreciation Calculation) surrounded by separator lines, followed by
     * a prompt requesting the user to enter asset data.
     * <p>
     * This method is called at the beginning of the application run to provide visual context
     * and user instructions before requesting input.
     */
    private void printBanner() {
        System.out.println("=================================================================");
        System.out.println("   CALCULO DE DEPRECIACION POR SALDO DECRECIENTE");
        System.out.println("=================================================================");
        System.out.println("\n  Ingrese los datos del activo:\n");
    }
}
