package com.edimilsonborges.conversormoedas;

import com.edimilsonborges.conversormoedas.menu.Menu;
import com.edimilsonborges.conversormoedas.service.ApiService;

public class Main {
    public static void main(String[] args) {
        new Menu();
        ApiService apiService = new ApiService();
        System.out.println(apiService.toConvert("USD", "BRL", 100));
    }
}