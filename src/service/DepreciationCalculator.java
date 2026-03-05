package service;

import model.Asset;
import model.DepreciationReport;

public interface DepreciationCalculator {

    /**
     * Calculates the residual value of an asset after a specified number of years of depreciation.
     * The residual value represents the remaining worth of the asset after accounting for depreciation
     * over the given time period. The calculation method used depends on the specific implementation
     * of this interface.
     *
     * @param asset the asset for which to calculate the residual value
     * @param year the number of years elapsed since the asset's purchase, must be greater than or equal to 0
     * @return the residual value of the asset after the specified number of years
     */
    double calculateResidualValue(Asset asset, int year);

    /**
     * Generates a comprehensive depreciation report for the specified asset over the given number of years.
     * The report contains detailed depreciation records for each year, including opening values,
     * depreciation amounts, and closing values, calculated according to the asset's depreciation rate.
     *
     * @param asset the asset for which to generate the depreciation report
     * @param years the number of years over which to calculate depreciation
     * @return a DepreciationReport containing the complete depreciation analysis for the asset
     */
    DepreciationReport generateReport(Asset asset, int years);
}
