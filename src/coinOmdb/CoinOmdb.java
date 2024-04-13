package coinOmdb;

import java.util.Map;
public record CoinOmdb(String result, String baseCode, Map<String, Double> conversionRates) {
}

