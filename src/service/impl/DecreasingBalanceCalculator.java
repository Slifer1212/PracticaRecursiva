package service.impl;

import model.Asset;
import model.DepreciationRecord;
import model.DepreciationReport;
import service.DepreciationCalculator;

import java.util.ArrayList;
import java.util.List;


public class DecreasingBalanceCalculator implements DepreciationCalculator {


    /**
     * Calculates the residual value of an asset at the end of a specified year using the decreasing balance method.
     * For year 0, returns the original purchase value of the asset. For subsequent years, recursively calculates
     * the residual value by applying the retention factor to the previous year's residual value.
     * The retention factor is derived from the asset's depreciation rate, reducing the asset's value by a
     * percentage each year.
     *
     * @param asset the asset for which to calculate the residual value
     * @param year  the number of years elapsed since purchase, must be greater than or equal to 0
     * @return the residual value of the asset at the end of the specified year
     */
    @Override
    public double calculateResidualValue(Asset asset, int year) {
        if (year == 0) {
            return asset.getPurchaseValue();
        }
        return calculateResidualValue(asset, year - 1)
                * retentionFactor(asset.getDepreciationRate());
    }


    /**
     * Generates a comprehensive depreciation report for the specified asset over the given number of years.
     * This method creates a year-by-year breakdown of the asset's depreciation using the decreasing balance method.
     * For each year from 0 to the specified number of years, it calculates the opening value, depreciation amount,
     * and closing value, creating a depreciation record for each period. Year 0 represents the initial state of the asset
     * at purchase, with subsequent years showing progressive depreciation.
     *
     * @param asset the asset for which to generate the depreciation report
     * @param years the number of years over which to calculate depreciation, must be non-negative
     * @return a DepreciationReport containing the asset details and a complete list of depreciation records for each year
     */
    @Override
    public DepreciationReport generateReport(Asset asset, int years) {
        List<DepreciationRecord> records = new ArrayList<>();

        for (int year = 0; year <= years; year++) {
            double closing = calculateResidualValue(asset, year);
            double opening = (year == 0) ? asset.getPurchaseValue()
                    : calculateResidualValue(asset, year - 1);
            double deprAmt = opening - closing;
            records.add(new DepreciationRecord(year, opening, deprAmt, closing));
        }

        return new DepreciationReport(asset, years, records);
    }


    /**
     * Calculates the retention factor for the decreasing balance depreciation method.
     * The retention factor represents the proportion of value that remains after applying
     * the depreciation rate for one period. It is computed as 1 minus the depreciation rate
     * expressed as a decimal.
     *
     * @param depreciationRate the depreciation rate expressed as a percentage (e.g., 20 for 20%)
     * @return the retention factor as a decimal between 0 and 1
     */
    private double retentionFactor(double depreciationRate) {
        return 1.0 - (depreciationRate / 100.0);
    }

}
