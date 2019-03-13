package com.example.krist.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static java.lang.Thread.sleep;

public class inicio extends AppCompatActivity implements View.OnClickListener  {
    TextView pronostico;
    String cadena = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getSupportActionBar().hide();
        Button boton4,boton5,buttonmapa;
        boton4 = findViewById(R.id.button4);
        boton5=findViewById(R.id.button5);
        buttonmapa=findViewById(R.id.buttonmapa);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);
        buttonmapa.setOnClickListener(this);
        pronostico=findViewById(R.id.pronostico);
        pronostico.setMovementMethod(new ScrollingMovementMethod());
        pronostico=findViewById(R.id.pronostico);
        pronostico.setShadowLayer(4,0,0, Color.BLACK);
        final Informacion infi;  //lanza el hilo para conectarse a la api aemet
        new Thread(new Runnable() {
            public void run() {

                Informacion   infi =new Informacion();
                try {

                    infi.conexion();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        }).start();
        try {

            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pronostico.setText(Informacion.cadena);
        System.out.println("*****************************");
        System.out.println(Informacion.cadena);
        System.out.println("*****************************");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4: //boton de alarma
                Intent intent2 = new Intent(this, MainActivity.class);

                startActivity(intent2);
                break;
            case R.id.button5: //boton de tiempo
                Intent intent3 = new Intent(this, Main3Activity.class);

                startActivity(intent3);
                break;
            case R.id.buttonmapa:
                Intent intent4 = new Intent(this, MapsActivity.class);

                startActivity(intent4);

        }
    }
}
