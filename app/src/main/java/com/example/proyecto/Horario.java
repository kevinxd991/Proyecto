package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class  Horario extends AppCompatActivity {


    private ListView lv1;
   int i=1;
    private List<String> listCursos;

    private DatabaseReference BDReferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_semanal);

        lv1 = (ListView)findViewById(R.id.lv1);
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String user = preferences.getString("user","user");
        listCursos= new ArrayList<>();

        BDReferencia= FirebaseDatabase.getInstance().getReference().child("Usuario").child (user);
        BDReferencia.child("Cursos").child("cantCursos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                while(i<= (Integer.parseInt(dataSnapshot.getValue().toString()))){
                    BDReferencia.child("Cursos").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             listCursos.add(dataSnapshot.child("Nombre").getValue().toString()+" "+dataSnapshot.child("Seccion").getValue().toString()+"\nProfesor: "+dataSnapshot.child("Profesor").getValue().toString()+"\nAula: "+dataSnapshot.child("aula").getValue().toString());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,listCursos);
                            lv1.setAdapter(adapter);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }


}
