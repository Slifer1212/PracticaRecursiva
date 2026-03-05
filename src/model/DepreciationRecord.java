package model;


public final class DepreciationRecord {

    /**
     * The calendar year for which this depreciation record applies.
     * This field represents the specific year in the asset's depreciation schedule
     * and is used to track when the depreciation values were calculated.
     */
    private final int    year;
    /**
     * The value of the asset at the beginning of the depreciation year.
     * This represents the asset's book value before any depreciation is applied for the current year.
     * For the first year, this equals the asset's purchase value. For subsequent years, this equals
     * the closing value from the previous year.
     */
    private final double openingValue;
    /**
     * The amount by which the asset depreciated during this year.
     * This value represents the monetary decrease in the asset's value for the specific year
     * covered by this depreciation record, calculated based on the asset's depreciation rate
     * and opening value.
     */
    private final double depreciationAmount;
    /**
     * The value of the asset at the end of the depreciation period for this year.
     * This represents the remaining value after the depreciation amount has been subtracted
     * from the opening value. The closing value is calculated as openingValue minus depreciationAmount,
     * and it becomes the opening value for the next depreciation period.
     */
    private final double closingValue;

    /**
     * Constructs a DepreciationRecord representing the depreciation state of an asset for a specific year.
     * This record captures the asset's value at the beginning of the year, the amount depreciated during
     * the year, and the resulting value at the end of the year.
     *
     * @param year the year for which this depreciation record applies
     * @param openingValue the value of the asset at the beginning of the year
     * @param depreciationAmount the amount by which the asset depreciated during the year
     * @param closingValue the value of the asset at the end of the year after depreciation
     */
    public DepreciationRecord(int year, double openingValue, double depreciationAmount, double closingValue) {
        this.year               = year;
        this.openingValue       = openingValue;
        this.depreciationAmount = depreciationAmount;
        this.closingValue       = closingValue;
    }

    /**
     * Returns the year associated with this depreciation record.
     * The year represents the specific period in the asset's depreciation timeline
     * for which this record captures depreciation calculations.
     *
     * @return the year of this depreciation record
     */
    public int    getYear()               { return year; }
    /**
     * Returns the opening value of the asset at the beginning of the depreciation year.
     * This represents the asset's value before depreciation is applied for this particular year.
     * For the first year, this value equals the asset's purchase value. For subsequent years,
     * it equals the closing value from the previous year.
     *
     * @return the opening value of the asset for this depreciation year
     */
    public double getOpeningValue()       { return openingValue; }
    /**
     * Returns the depreciation amount for this record.
     * The depreciation amount represents the reduction in the asset's value
     * during the year covered by this record. This value is calculated based
     * on the asset's opening value and depreciation rate for the corresponding year.
     *
     * @return the depreciation amount as a double
     */
    public double getDepreciationAmount() { return depreciationAmount; }
    /**
     * Returns the closing value of the asset at the end of the depreciation period for this record.
     * The closing value represents the asset's remaining value after the depreciation amount
     * has been subtracted from the opening value for the year.
     *
     * @return the closing value of the asset in monetary units
     */
    public double getClosingValue()       { return closingValue; }
}
