package model;


public final class Asset {

    private final String name;
    private final double purchaseValue;
    private final double depreciationRate;

    private Asset(Builder builder) {
        this.name             = builder.name;
        this.purchaseValue    = builder.purchaseValue;
        this.depreciationRate = builder.depreciationRate;
    }

    public String getName()             { return name; }
    public double getPurchaseValue()    { return purchaseValue; }
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

    @Override
    public String toString() {
        return String.format("Asset{name='%s', purchaseValue=%.2f, depreciationRate=%.2f%%}",
                name, purchaseValue, depreciationRate);
    }
}
