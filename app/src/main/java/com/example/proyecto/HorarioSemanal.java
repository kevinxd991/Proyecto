package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HorarioSemanal extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO"};
    private String edades[] = {"MATEMATICA DISCRETA-A305-ELIAS SOTO," +
            "ANALISIS Y DISEÑO DE ALGORITMOS-C101-MARKS CALDERON," +
            "ESTADISTICA INFERENCIAL-B202-JOSE LARA",
            "FUNDAMENTOS","TEORIA","MODELAMIENTO","ROBOTICA","MOVILES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_semanal);

        tv1 = (TextView)findViewById(R.id.tv1);
        lv1 = (ListView)findViewById(R.id.lv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, nombres);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("HORARIO DE " + lv1.getItemAtPosition(i) + " ES EL SIGUIENTE: " +  edades[i] + " ");
            }
        });
    }
}
