package com.edimilsonborges.conversormoedas.service;

import com.edimilsonborges.conversormoedas.exceptions.ErrorApiKeyUrlException;
import com.edimilsonborges.conversormoedas.model.coin.Coin;
import com.edimilsonborges.conversormoedas.model.coin.CoinOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class ApiService {
    public String toConvert(String ofCurrency, String toCurrency, double value) {

        final String apiKey = ""; // coloque sua apikey aqui
        final URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + ofCurrency);

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            CoinOmdb coinOmdb = gson.fromJson(json, CoinOmdb.class);
            Coin coin = new Coin(coinOmdb);
            double coinValue = coin.getConversionRates().get(toCurrency) * value;
            String coinValueFinal = formatValue(coinValue);
            String valueFinal = formatValue(value);

            return "O valor de " + valueFinal + " [" + ofCurrency + "] " + "corresponde ao valor final de " + coinValueFinal + " [" + toCurrency + "]";
        } catch (NullPointerException e) {
            throw new ErrorApiKeyUrlException("Erro na api, verifique se você inseriu sua apikey corretamente.");
        } catch (ConnectException | JsonSyntaxException e) {
            throw new ErrorApiKeyUrlException("Erro na api, verifique se você inseriu a Uri da Api corretamente.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String formatValue(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(value);
    }
}

