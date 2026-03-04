package model;


public final class DepreciationRecord {

    private final int    year;
    private final double openingValue;
    private final double depreciationAmount;
    private final double closingValue;

    public DepreciationRecord(int year, double openingValue, double depreciationAmount, double closingValue) {
        this.year               = year;
        this.openingValue       = openingValue;
        this.depreciationAmount = depreciationAmount;
        this.closingValue       = closingValue;
    }

    public int    getYear()               { return year; }
    public double getOpeningValue()       { return openingValue; }
    public double getDepreciationAmount() { return depreciationAmount; }
    public double getClosingValue()       { return closingValue; }
}
