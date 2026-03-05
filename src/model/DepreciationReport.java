package model;

import java.util.Collections;
import java.util.List;


public final class DepreciationReport {

    /**
     * The asset for which this depreciation report is generated.
     * This field contains the complete asset information including its name, purchase value,
     * and depreciation rate, which are used to calculate the depreciation records over time.
     */
    private final Asset asset;
    /**
     * The number of years over which the asset depreciation is calculated.
     * This value determines the time period covered by the depreciation records
     * in this report and is used to project the asset's value decline over time.
     */
    private final int years;
    /**
     * The list of individual depreciation records for each year of the asset's depreciation period.
     * This collection maintains the chronological sequence of depreciation calculations, with each
     * record capturing the opening value, depreciation amount, and closing value for a specific year.
     * The list is immutable and provides a complete history of the asset's value decline over time.
     */
    private final List<DepreciationRecord> records;

    /**
     * Constructs a DepreciationReport with the specified asset, time period, and depreciation records.
     * The records list is made unmodifiable to ensure immutability of the report.
     *
     * @param asset the asset for which this depreciation report is generated
     * @param years the number of years covered by this depreciation report
     * @param records the list of depreciation records documenting the asset's value changes over time
     */
    public DepreciationReport(Asset asset, int years, List<DepreciationRecord> records) {
        this.asset = asset;
        this.years = years;
        this.records = Collections.unmodifiableList(records);
    }

    /**
     * Returns the asset for which this depreciation report was generated.
     *
     * @return the asset associated with this depreciation report
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * Returns the number of years over which the depreciation is calculated in this report.
     *
     * @return the number of years for the depreciation calculation
     */
    public int getYears() {
        return years;
    }

    /**
     * Returns an unmodifiable list of depreciation records contained in this report.
     * Each record represents the depreciation calculation for a specific year in the
     * asset's depreciation schedule, including opening value, depreciation amount,
     * and closing value for that year.
     *
     * @return an unmodifiable list of DepreciationRecord objects representing the yearly depreciation history
     */
    public List<DepreciationRecord> getRecords() {
        return records;
    }

    /**
     * Returns the final value of the asset after all depreciation periods.
     * If no depreciation records exist, returns 0. Otherwise, returns the closing value
     * from the last depreciation record, which represents the asset's remaining value
     * after all depreciation has been applied over the specified years.
     *
     * @return the final depreciated value of the asset in monetary units, or 0 if no records exist
     */
    public double getFinalValue() {
        return records.isEmpty() ? 0 : records.get(records.size() - 1).getClosingValue();
    }

    /**
     * Calculates and returns the total amount by which the asset has depreciated.
     * This is computed as the difference between the asset's original purchase value
     * and its final value after depreciation over the specified period.
     *
     * @return the total depreciation amount as a double
     */
    public double getTotalDepreciated() {
        return asset.getPurchaseValue() - getFinalValue();
    }

    /**
     * Calculates and returns the percentage of the asset's original value that is retained
     * after depreciation. This is computed by dividing the final value of the asset by its
     * original purchase value and multiplying by 100.
     *
     * @return the retention percentage as a double, representing what percentage of the original
     *         purchase value remains after depreciation
     */
    public double getRetentionPercentage() {
        return (getFinalValue() / asset.getPurchaseValue()) * 100;
    }
}
