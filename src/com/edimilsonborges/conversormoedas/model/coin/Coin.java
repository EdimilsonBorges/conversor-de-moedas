package com.edimilsonborges.conversormoedas.model.coin;

import java.util.Map;

public class Coin {
    private final String result;
    private final String baseCode;
    private final Map<String, Double> conversionRates;

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

