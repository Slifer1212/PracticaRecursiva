package ui;

import model.Asset;


public interface AssetInputReader {
    Asset readAsset();
    int   readYears();
}
