package com.example.app_volquetas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class Volquetas extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_volquetas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textView2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Map<String, String> datos = new HashMap<>();
        WebService ws= new WebService(
                "https://uteqia.com/api/volquetas",
                datos, Volquetas.this, Volquetas.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtSaludo = findViewById(R.id.txtResp);

        String lstLista = "";
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            lstLista += (i + 1) + ".- " +
                    "Placa: " + objeto.getString("placa") + " - " +
                    "Dispositivo: " + objeto.getString("dispositivo_id") + "\n";
        }

        txtSaludo.setText(lstLista);}
}