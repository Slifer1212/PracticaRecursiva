package model;


public final class Asset {

    /**
     * The identifying name of this asset.
     */
    private final String name;
    /**
     * The original purchase value of the asset in monetary units.
     * This represents the initial cost or acquisition value of the asset before any depreciation
     * is applied. The purchase value serves as the starting point for calculating depreciation
     * over the asset's lifetime.
     */
    private final double purchaseValue;
    /**
     * The rate at which the asset depreciates per year, expressed as a percentage.
     * This rate is applied to calculate the reduction in the asset's value over time
     * in depreciation calculations. The value is stored as a decimal representation
     * where 100 represents 100%.
     */
    private final double depreciationRate;

    /**
     * Constructs an Asset instance using the provided builder.
     * This private constructor is used by the Builder pattern to create immutable Asset objects
     * with the specified name, purchase value, and depreciation rate.
     *
     * @param builder the Builder instance containing the asset configuration values
     */
    private Asset(Builder builder) {
        this.name             = builder.name;
        this.purchaseValue    = builder.purchaseValue;
        this.depreciationRate = builder.depreciationRate;
    }

    /**
     * Returns the name of this asset.
     *
     * @return the asset name
     */
    public String getName()             { return name; }
    /**
     * Returns the original purchase value of the asset.
     *
     * @return the purchase value of the asset as a double
     */
    public double getPurchaseValue()    { return purchaseValue; }
    /**
     * Returns the depreciation rate of this asset.
     * The depreciation rate is expressed as a percentage value that determines
     * how much the asset's value decreases over time.
     *
     * @return the depreciation rate as a percentage
     */
    public double getDepreciationRate() { return depreciationRate; }

    public static class Builder {
        private String name;
        private double purchaseValue;
        private double depreciationRate;

        public Builder name(String name)                       { this.name = name;                       return this; }
        public Builder purchaseValue(double purchaseValue)     { this.purchaseValue = purchaseValue;     return this; }
        public Builder depreciationRate(double depreciationRate){ this.depreciationRate = depreciationRate; return this; }

        public Asset build() { return new Asset(this); }
    }

    /**
     * Returns a string representation of this Asset object.
     * The returned string contains the asset's name, purchase value formatted to two decimal places,
     * and depreciation rate formatted as a percentage with two decimal places.
     *
     * @return a formatted string in the form "Asset{name='...', purchaseValue=..., depreciationRate=...%}"
     */
    @Override
    public String toString() {
        return String.format("Asset{name='%s', purchaseValue=%.2f, depreciationRate=%.2f%%}",
                name, purchaseValue, depreciationRate);
    }
}
