package com.edimilsonborges.conversormoedas.model.coin;

import java.util.Map;
public record CoinOmdb(String result, String baseCode, Map<String, Double> conversionRates) {
}

