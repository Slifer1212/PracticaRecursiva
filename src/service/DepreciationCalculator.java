package service;

import model.Asset;
import model.DepreciationReport;

public interface DepreciationCalculator {

    /**
     * Calcula el valor residual de un activo al final del año {@code year}.
     *
     * @param asset el activo a depreciar
     * @param year  años transcurridos (>= 0)
     * @return valor residual al final del año indicado
     */
    double calculateResidualValue(Asset asset, int year);

    /**
     * Genera el reporte completo de depreciación año a año.
     *
     * @param asset el activo a depreciar
     * @param years número total de años a evaluar
     * @return reporte inmutable con todos los registros
     */
    DepreciationReport generateReport(Asset asset, int years);
}
