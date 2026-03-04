package model;

import java.util.Collections;
import java.util.List;


public final class DepreciationReport {

    private final Asset                   asset;
    private final int                     years;
    private final List<DepreciationRecord> records;

    public DepreciationReport(Asset asset, int years, List<DepreciationRecord> records) {
        this.asset   = asset;
        this.years   = years;
        this.records = Collections.unmodifiableList(records);
    }

    public Asset                    getAsset()   { return asset; }
    public int                      getYears()   { return years; }
    public List<DepreciationRecord> getRecords() { return records; }

    public double getFinalValue() {
        return records.isEmpty() ? 0 : records.get(records.size() - 1).getClosingValue();
    }

    public double getTotalDepreciated() {
        return asset.getPurchaseValue() - getFinalValue();
    }

    public double getRetentionPercentage() {
        return (getFinalValue() / asset.getPurchaseValue()) * 100;
    }
}
