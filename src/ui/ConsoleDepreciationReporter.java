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

    @Override
    public void displayReport(DepreciationReport report) {
        displayHeader(report.getAsset());
        displayParameters(report);
        displayTable(report);
        displaySummary(report);
    }

    // ── Secciones del reporte ────────────────────────────────────────────────

    private void displayHeader(Asset asset) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("   REPORTE DE DEPRECIACION - SALDO DECRECIENTE");
        System.out.println("   Activo: " + asset.getName());
        System.out.println(SEPARATOR);
    }

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

    private void displayTable(DepreciationReport report) {
        System.out.println("\n  TABLA DE DEPRECIACION ANUAL");
        System.out.println("  " + TABLE_TOP);
        System.out.printf("  | %-4s | %-17s | %-17s | %-17s |%n",
                "Ano", "Valor Inicial", "Depreciacion", "Valor Final");
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

    private void displaySummary(DepreciationReport report) {
        System.out.println("\n  RESUMEN");
        System.out.println("  -------");
        System.out.printf("  Valor residual final   : %s%n", currency(report.getFinalValue()));
        System.out.printf("  Total depreciado       : %s%n", currency(report.getTotalDepreciated()));
        System.out.printf("  Porcentaje conservado  : %.2f%%%n", report.getRetentionPercentage());
        System.out.println("\n" + SEPARATOR + "\n");
    }

    // ── Utilidades de formato ────────────────────────────────────────────────

    private String currency(double amount) {
        return String.format("$%,12.2f", amount);
    }
}
