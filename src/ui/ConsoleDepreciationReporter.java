package ui;

import model.Asset;
import model.DepreciationRecord;
import model.DepreciationReport;


public class ConsoleDepreciationReporter implements DepreciationReporter {

    private static final String SEPARATOR =
            "=================================================================";
    private static final String TABLE_TOP =
            "+------+-------------------+-------------------+-------------------+";
    private static final String TABLE_MID =
            "+------+-------------------+-------------------+-------------------+";
    private static final String TABLE_BOT =
            "+------+-------------------+-------------------+-------------------+";

    /**
     * Displays a complete depreciation report to the console, including the asset header,
     * calculation parameters, detailed annual depreciation table, and summary information.
     * The report is formatted for console output with appropriate sections and formatting.
     *
     * @param report the depreciation report containing asset information, depreciation records,
     *               and calculation results to be displayed
     */
    @Override
    public void displayReport(DepreciationReport report) {
        displayHeader(report.getAsset());
        displayParameters(report);
        displayTable(report);
        displaySummary(report);
    }


    /**
     * Displays the header section of the depreciation report.
     * Prints a formatted header containing the report title and the asset name,
     * surrounded by separator lines for visual distinction.
     *
     * @param asset the Asset object whose name will be displayed in the header
     */
    private void displayHeader(Asset asset) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("   REPORTE DE DEPRECIACION - SALDO DECRECIENTE");
        System.out.println("   Activo: " + asset.getName());
        System.out.println(SEPARATOR);
    }

    /**
     *
     */
    private void displayParameters(DepreciationReport report) {
        Asset asset = report.getAsset();
        System.out.println("\n  PARAMETROS");
        System.out.println("  ----------");
        System.out.printf("  Valor original (V0)  : %s%n", currency(asset.getPurchaseValue()));
        System.out.printf("  Tasa depreciacion (d): %.2f%%%n", asset.getDepreciationRate());
        System.out.printf("  Factor retencion  (r): %.4f%n",
                1.0 - asset.getDepreciationRate() / 100.0);
        System.out.printf("  Anos evaluados    (t): %d%n", report.getYears());
    }

    /**
     * Displays the annual depreciation table to the console.
     * The table includes column headers and a row for each year showing the year number,
     * opening value, depreciation amount, and closing value. All monetary values are
     * formatted as currency using the currency helper method. The table is bordered
     * with predefined table formatting constants for visual presentation.
     *
     * @param report the depreciation report containing the records to display in the table
     */
    private void displayTable(DepreciationReport report) {
        System.out.println("\n  TABLA DE DEPRECIACION ANUAL");
        System.out.println("  " + TABLE_TOP);
        System.out.printf("  | %-4s | %-17s | %-17s | %-17s |%n",
                "Anio", "Valor Inicial", "Depreciacion", "Valor Final");
        System.out.println("  " + TABLE_MID);

        for (DepreciationRecord record : report.getRecords()) {
            System.out.printf("  | %-4d | %-17s | %-17s | %-17s |%n",
                    record.getYear(),
                    currency(record.getOpeningValue()),
                    currency(record.getDepreciationAmount()),
                    currency(record.getClosingValue()));
        }

        System.out.println("  " + TABLE_BOT);
    }

    /**
     * Displays the summary section of the depreciation report to the console.
     * The summary includes the final residual value of the asset after depreciation,
     * the total amount depreciated over the calculation period, and the retention
     * percentage representing the proportion of the original value that remains.
     * All monetary values are formatted as currency.
     *
     * @param report the depreciation report containing the final value, total depreciated amount, and retention percentage to be displayed
     */
    private void displaySummary(DepreciationReport report) {
        System.out.println("\n  RESUMEN");
        System.out.println("  -------");
        System.out.printf("  Valor residual final   : %s%n", currency(report.getFinalValue()));
        System.out.printf("  Total depreciado       : %s%n", currency(report.getTotalDepreciated()));
        System.out.printf("  Porcentaje conservado  : %.2f%%%n", report.getRetentionPercentage());
        System.out.println("\n" + SEPARATOR + "\n");
    }

    /**
     * Formats a monetary amount as a currency string with dollar sign, thousands separators, and two decimal places.
     * The formatted value is right-aligned in a 12-character wide field.
     *
     * @param amount the numeric value to be formatted as currency
     * @return a formatted string representing the amount in currency format (e.g., "$  1,234.56")
     */
    private String currency(double amount) {
        return String.format("$%,12.2f", amount);
    }

}
