package service;

import coinOmdb.CoinOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public String toConvert(String ofCurrency, String toCurrency, double value){

        String apiKey = ""; // coloque sua apikey aqui
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+ofCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            CoinOmdb coinOmdb = gson.fromJson(json,CoinOmdb.class);
            double finalValue = coinOmdb.conversionRates().get(toCurrency) * value;

            return  "O valor de "+value+" ["+ofCurrency+"] "+"corresponde ao valor final de "+finalValue+" ["+toCurrency+"]";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
