package com.edimilsonborges.conversormoedas.core;

import com.edimilsonborges.conversormoedas.menu.Menu;
import com.edimilsonborges.conversormoedas.service.ApiService;

import java.io.IOException;
import java.util.*;

public class Start {

    private final Map<Integer, String> currency = new HashMap<>();
    ApiService apiService = new ApiService();

    public Start() {
        addCurrency();
        start();
    }

    private void start() {
        Map<String, String> hitoryList = new HashMap<>();

        while (true) {
            new Menu();

            try {
                Scanner scanner = new Scanner(System.in);
                int selected = scanner.nextInt();

                if (selected == 11) {
                    System.out.println("Você saiu!");
                    break;
                } else if (selected > 0 && selected <= currency.size()) {
                    System.out.println("Digite o valor que deseja converter");
                    Scanner scan = new Scanner(System.in);
                    String input = scan.nextLine().replace(',', '.');
                    double value = Double.parseDouble(input);
                    String currencySelected = currency.get(selected);
                    System.out.println(selection(currencySelected, value, hitoryList));
                    pause("Pressione enter para continuar");
                } else {
                    pause("Opção inválida, selecione entre 1 e " + (currency.size() + 1) + ", pressione enter para repetir!");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                pause("Opção inválida, pressione enter para repetir!");
            }
        }
    }

    private String selection(String selected, double value, Map<String, String> historyList) {
        String[] currencys = selected.split("-");
        String ofCurrency = currencys[0];
        String toCurrency = currencys[1];
        String result = apiService.toConvert(ofCurrency, toCurrency, value);

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

    private void pause(String message) {
        System.out.println(message);
        try {
            final int read = System.in.read();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
