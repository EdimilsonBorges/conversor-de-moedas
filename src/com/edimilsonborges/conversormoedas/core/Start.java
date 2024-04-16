package com.edimilsonborges.conversormoedas.core;

import com.edimilsonborges.conversormoedas.menu.Menu;
import com.edimilsonborges.conversormoedas.service.ApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Start {

    private final Map<Integer, String> currency = new HashMap<>();
    private final Map<String, String> historyList = new HashMap<>();
    private final ApiService apiService = new ApiService();

    public Start() {
        addCurrency();
        start();
    }

    private void start() {

        while (true) {
            new Menu();

            try {
                Scanner scanner = new Scanner(System.in);
                int selected = scanner.nextInt();

                if (selected == 11) {
                    saveHistory();
                    System.out.println("Você saiu!");
                    break;
                } else if (selected > 0 && selected <= currency.size()) {
                    System.out.println("Digite o valor que deseja converter");
                    Scanner scan = new Scanner(System.in);
                    String input = scan.nextLine().replace(',', '.');
                    double value = Double.parseDouble(input);
                    String currencySelected = currency.get(selected);
                    System.out.println(selection(currencySelected, value));
                    pause("Pressione enter para continuar");
                } else {
                    pause("Opção inválida, selecione entre 1 e " + (currency.size() + 1) + ", pressione enter para repetir!");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                pause("Opção inválida, pressione enter para repetir!");
            }
        }
    }

    private void saveHistory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        try {
            FileWriter fileWriter = new FileWriter("history.json");
            fileWriter.write(gson.toJson(historyList));
            fileWriter.close();
            System.out.println("Histórico salvo com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String selection(String selected, double value) {
        String[] currencys = selected.split("-");
        String ofCurrency = currencys[0];
        String toCurrency = currencys[1];
        String result = apiService.toConvert(ofCurrency, toCurrency, value);
        addHistory(result);

        return """
                *******************************************************************
                """ +
                result
                + """
                 
                *******************************************************************
                """;
    }

    private void addCurrency() {
        currency.put(1, "USD-ARS");
        currency.put(2, "ARS-USD");
        currency.put(3, "USD-BRL");
        currency.put(4, "BRL-USD");
        currency.put(5, "USD-COP");
        currency.put(6, "COP-USD");
        currency.put(7, "EUR-BRL");
        currency.put(8, "BRL-EUR");
        currency.put(9, "ARS-BRL");
        currency.put(10, "BRL-ARS");
    }

    private void addHistory(String result) {
        LocalDateTime dateHourNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String timeFormatted = dateHourNow.format(formatter);
        this.historyList.put(timeFormatted, result);
    }

    private void pause(String message) {
        System.out.println(message);
        try {
            System.in.read();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
