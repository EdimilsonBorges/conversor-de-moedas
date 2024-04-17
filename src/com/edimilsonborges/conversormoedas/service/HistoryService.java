package com.edimilsonborges.conversormoedas.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HistoryService {
    private Map<String, String> historyList = new HashMap<>();

    public HistoryService(){
        loadHistory();
    }

    public void addHistory(String result) {
        LocalDateTime dateHourNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String timeFormatted = dateHourNow.format(formatter);
        this.historyList.put(timeFormatted, result);
    }

    public void saveHistory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        try {
            FileWriter fileWriter = new FileWriter("history.json");
            if(!historyList.isEmpty()){
                fileWriter.write(gson.toJson(historyList));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean loadHistory(){
        try (Reader reader = new FileReader("history.json")) {
            Gson gson = new GsonBuilder().create();

            Map<String, String> dataHistory = gson.fromJson(reader, Map.class);

           if(dataHistory != null){
                historyList = dataHistory;
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void readHistory() {
        if (loadHistory()) {
            printHistory(historyList);
        } else {
            System.out.println("Nenhum registro encontrado!");
        }
    }

    private void printHistory(Map<String, String> historyList) {

        System.out.println("***************************************************************************************");
        for (Map.Entry<String, String> data : historyList.entrySet()) {
            String key = data.getKey();
            String[] dateTime = key.split("\\|");
            String date = dateTime[0];
            String time = dateTime[1];
            String value = data.getValue();
            System.out.println( "Dia " + date + " às " + time + " => "+ value);
        }
        System.out.println("***************************************************************************************");
    }

}
