package com.edimilsonborges.conversormoedas.menu;

public class Menu {

    public Menu() {
        String menu = """
                *********************************************
                * Seja bem-vindo/a ao Conversor de Moeda =] *
                *********************************************
                *    1) Dólar =>> Peso Argentino            *
                *    2) Peso Argentino =>> Dólar            *
                *    3) Dólar =>> Real Brasileiro           *
                *    4) Real Brasileiro =>> Dólar           *
                *    5) Dólar =>> Peso Colombiano           *
                *    6) Peso Colombiano =>> Dólar           *
                *    7) Euro =>> Real Brasileiro            *
                *    8) Real Brasileiro =>> Euro            *
                *    9) Peso Argentino =>> Real Brasileiro  *
                *    10) Real Brasileiro =>> Peso Argentino *
                *    11) Ver historico de consultas         *
                *    12) Sair                               *
                *********************************************
                Escolha uma opção válida:""";
        System.out.println(menu);
    }
}
