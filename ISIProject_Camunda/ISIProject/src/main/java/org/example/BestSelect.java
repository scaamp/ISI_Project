package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.logging.Logger;
import static org.apache.commons.lang3.math.NumberUtils.min;

public class BestSelect implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    private double btcUsdBinancePrice = GetBTCUSDBinancePrice.getBtcUsdBinancePrice();
    private double btcUsdKucoinPrice = GetBTCUSDKucoinPrice.getBtcUsdKucoinPrice();
    private double btcUsdCoinbasePrice = GetBTCUSDCoinbasePrice.getBtcUsdCoinbasePrice();
    private double btcEurBinancePrice = GetBTCEURBinancePrice.getBtcEurBinancePrice();
    private double btcEurKucoinPrice = GetBTCEURKucoinPrice.getBtcEurKucoinPrice();
    private double btcEurCoinbasePrice = GetBTCEURCoinbasePrice.getBtcEurCoinbasePrice();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        double bestPrice = 0;
        String exchangeName = "";
        if (btcUsdBinancePrice != 0 && btcUsdCoinbasePrice != 0 && btcUsdKucoinPrice != 0) {
            bestPrice = min(btcUsdBinancePrice, btcUsdKucoinPrice, btcUsdCoinbasePrice);
        }
        else {
            bestPrice = min(btcEurBinancePrice, btcEurKucoinPrice, btcEurCoinbasePrice);
        }
        if (bestPrice == btcUsdBinancePrice || bestPrice == btcEurBinancePrice) exchangeName = "Binance";
        else if (bestPrice == btcUsdKucoinPrice || bestPrice == btcEurKucoinPrice) exchangeName = "KuCoin";
        else if (bestPrice == btcUsdCoinbasePrice || bestPrice == btcEurCoinbasePrice) exchangeName = "Coinbase";
        LOGGER.info("Best price to buy is: " + bestPrice + " on " + exchangeName);
    }
}
