package coin;

import coinOmdb.CoinOmdb;

import java.util.Map;

public class Coin {
    private String result;
    private String baseCode;
    private Map<String, Double> conversionRates;

    public Coin(CoinOmdb coinOmdb) {
        this.result = coinOmdb.result();
        this.baseCode = coinOmdb.baseCode();
        this.conversionRates = coinOmdb.conversionRates();
    }

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}

