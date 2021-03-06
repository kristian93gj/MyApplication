package com.example.krist.myapplication;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Informacion  {
    OkHttpClient client = new OkHttpClient();
     static String cadena="";
    static String cadenagoogle="";
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String conexion()   throws IOException, ParseException {
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\krist\\Documents\\alma");
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        //StrictMode.setThreadPolicy(policy); escapar un fallo de conexion no recomendable

        Informacion info =new Informacion();
        String response1 = info.run("https://opendata.aemet.es/opendata/api/prediccion/nacional/hoy/" + "?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrcmlzdGlhbl92a18zMzMyQGhvdG1haWwuY29tIiwianRpIjoiMjkwNjY5ZTctOWJjYy00NDU0LTg2MzEtZWQ1MmQ4MGQ4MjU0IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE1NDIwNDY4MDcsInVzZXJJZCI6IjI5MDY2OWU3LTliY2MtNDQ1NC04NjMxLWVkNTJkODBkODI1NCIsInJvbGUiOiIifQ.iS-KaYWvAenb_5WqGYA1ec9fwDvG5XYTEl_jgeZxJCQ");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response1);
        String httpDatos = (String) json.get("datos");
        System.out.println("Dirección para conectar : " + httpDatos);
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\krist\\Documents\\alma");


        String conect= info.run(httpDatos);
        System.out.println(conect);
        cadena=cadena+conect;

        return cadena;
    }
    public String conexiontiempo_horas()   throws IOException, ParseException {
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\krist\\Documents\\alma");
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        //StrictMode.setThreadPolicy(policy); escapar un fallo de conexion no recomendable

        Informacion info =new Informacion();
       // String response1 = info.run("https://opendata.aemet.es/opendata/api/observacion/convencional/datos/estacion/3195/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrcmlzdGlhbl92a18zMzMyQGhvdG1haWwuY29tIiwianRpIjoiMjkwNjY5ZTctOWJjYy00NDU0LTg2MzEtZWQ1MmQ4MGQ4MjU0IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE1NDIwNDY4MDcsInVzZXJJZCI6IjI5MDY2OWU3LTliY2MtNDQ1NC04NjMxLWVkNTJkODBkODI1NCIsInJvbGUiOiIifQ.iS-KaYWvAenb_5WqGYA1ec9fwDvG5XYTEl_jgeZxJCQ");
        String response1 = info.run("https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/horaria/28079/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrcmlzdGlhbl92a18zMzMyQGhvdG1haWwuY29tIiwianRpIjoiMjkwNjY5ZTctOWJjYy00NDU0LTg2MzEtZWQ1MmQ4MGQ4MjU0IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE1NDIwNDY4MDcsInVzZXJJZCI6IjI5MDY2OWU3LTliY2MtNDQ1NC04NjMxLWVkNTJkODBkODI1NCIsInJvbGUiOiIifQ.iS-KaYWvAenb_5WqGYA1ec9fwDvG5XYTEl_jgeZxJCQ");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response1);
        String httpDatos = (String) json.get("datos");
        System.out.println("Dirección para conectar : " + httpDatos);
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\krist\\Documents\\alma");


        String conect= info.run(httpDatos);
        System.out.println(conect);
        cadena=conect;

        return cadena;
    }
    public String conexion(String url) throws IOException {
        Informacion info =new Informacion();
        String response1 = info.run(url);
        ObjectMapper objectMapper = new ObjectMapper();

        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(response1);
        Iterator<JsonNode> nodos = rootNode.elements();
        JsonNode locatedNode = rootNode.path("rows").findValue("elements").findValue("duration_in_traffic").findValue("text");
        System.out.println("########################33");
        cadenagoogle="El destino -> "+nodos.next().get(0)+" el origen ->"+nodos.next().get(0);
        System.out.println(locatedNode);
        return locatedNode.toString();
    }
    public static void main(String[] args) throws IOException, ParseException {
        Informacion infi=new Informacion();
        infi.conexion();
    }


}