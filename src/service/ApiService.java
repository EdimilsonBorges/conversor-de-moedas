package service;

import Exceptions.ErrorApiKeyUrlException;
import coin.Coin;
import coinOmdb.CoinOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public String toConvert(String ofCurrency, String toCurrency, double value) {

        final String apiKey = ""; // coloque sua apikey aqui
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + ofCurrency);

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
            double finalValue = coin.getConversionRates().get(toCurrency) * value;

            return "O valor de " + value + " [" + ofCurrency + "] " + "corresponde ao valor final de " + finalValue + " [" + toCurrency + "]";
        } catch (NullPointerException e) {
            throw new ErrorApiKeyUrlException("Erro na api, verifique se você inseriu sua apikey corretamente.");
        }catch (ConnectException | JsonSyntaxException e) {
            throw new ErrorApiKeyUrlException("Erro na api, verifique se você inseriu a Uri da Api corretamente.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

