import config.DepreciationApplication;
import service.DepreciationCalculator;
import service.impl.DecreasingBalanceCalculator;
import ui.AssetInputReader;
import ui.ConsoleAssetInputReader;
import ui.ConsoleDepreciationReporter;
import ui.DepreciationReporter;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AssetInputReader reader = new ConsoleAssetInputReader(scanner);
        DepreciationCalculator calculator = new DecreasingBalanceCalculator();
        DepreciationReporter reporter = new ConsoleDepreciationReporter();

        DepreciationApplication app = new DepreciationApplication(reader, calculator, reporter);

        app.run();

        scanner.close();
    }
}
