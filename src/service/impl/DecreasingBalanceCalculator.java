package service.impl;

import model.Asset;
import model.DepreciationRecord;
import model.DepreciationReport;
import service.DepreciationCalculator;

import java.util.ArrayList;
import java.util.List;


public class DecreasingBalanceCalculator implements DepreciationCalculator {


    @Override
    public double calculateResidualValue(Asset asset, int year) {
        if (year == 0) {
            return asset.getPurchaseValue();
        }
        return calculateResidualValue(asset, year - 1)
                * retentionFactor(asset.getDepreciationRate());
    }


    @Override
    public DepreciationReport generateReport(Asset asset, int years) {
        List<DepreciationRecord> records = new ArrayList<>();

        for (int year = 0; year <= years; year++) {
            double closing  = calculateResidualValue(asset, year);
            double opening  = (year == 0) ? asset.getPurchaseValue()
                                          : calculateResidualValue(asset, year - 1);
            double deprAmt  = opening - closing;
            records.add(new DepreciationRecord(year, opening, deprAmt, closing));
        }

        return new DepreciationReport(asset, years, records);
    }


    private double retentionFactor(double depreciationRate) {
        return 1.0 - (depreciationRate / 100.0);
    }

}
