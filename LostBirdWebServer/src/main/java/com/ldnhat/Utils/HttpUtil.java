package com.ldnhat.Utils;



import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class HttpUtil {

    private String value;

    public HttpUtil(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass){
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpUtil of(BufferedReader reader){
        StringBuilder json = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                json.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return new HttpUtil(json.toString());
    }


    public static String handleRequest(BufferedReader reader){
        StringBuilder json = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                json.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return json.toString();
    }
}
