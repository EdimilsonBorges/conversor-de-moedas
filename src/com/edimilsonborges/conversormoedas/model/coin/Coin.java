package com.edimilsonborges.conversormoedas.model.coin;

import java.util.Map;

public class Coin {
    private final Map<String, Double> conversionRates;

    public Coin(CoinOmdb coinOmdb) {
        this.conversionRates = coinOmdb.conversionRates();
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}

