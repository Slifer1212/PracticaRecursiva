package config;

import model.Asset;
import model.DepreciationReport;
import service.DepreciationCalculator;
import ui.AssetInputReader;
import ui.DepreciationReporter;


public class DepreciationApplication {

    private final AssetInputReader      inputReader;
    private final DepreciationCalculator calculator;
    private final DepreciationReporter  reporter;

    public DepreciationApplication(
            AssetInputReader      inputReader,
            DepreciationCalculator calculator,
            DepreciationReporter  reporter) {

        this.inputReader = inputReader;
        this.calculator  = calculator;
        this.reporter    = reporter;
    }

    public void run() {
        printBanner();

        Asset asset = inputReader.readAsset();
        int   years = inputReader.readYears();

        DepreciationReport report = calculator.generateReport(asset, years);

        reporter.displayReport(report);
    }

    private void printBanner() {
        System.out.println("=================================================================");
        System.out.println("   CALCULO DE DEPRECIACION POR SALDO DECRECIENTE");
        System.out.println("=================================================================");
        System.out.println("\n  Ingrese los datos del activo:\n");
    }
}
