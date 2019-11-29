package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class Perfil extends AppCompatActivity {
    private DatabaseReference BDReference;

    private String BDcarrera;
    private String BDciclo;
    private String BDapellido;

    private TextView c;
    private TextView a;
    private ImageView foto1;
    private TextView b;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        foto1= findViewById(R.id.foto);

        a = findViewById(R.id.txtCarrera);

        b = findViewById(R.id.txtCicloIni);
        c = findViewById(R.id.txtNomb);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String user = preferences.getString("user","user");


        BDReference= FirebaseDatabase.getInstance().getReference().child("Usuario").child(user);

        a.setText(BDapellido);
        b.setText(BDciclo);
        c.setText(BDcarrera);


        BDReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Apellido=dataSnapshot.child("Apellido").getValue().toString();
                String Carrera=dataSnapshot.child("Carrera").getValue().toString();
                String CicloIni=dataSnapshot.child("CicloIni").getValue().toString();
                String foto = dataSnapshot.child("foto").getValue().toString();
                a.setText(Carrera);
                b.setText(CicloIni);
                c.setText(Apellido);
                foto1.setImageBitmap(stringToImage(foto));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private Bitmap stringToImage(String encodedString) {
        try{
            byte[] encodedByte  = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodedByte,0,encodedByte.length);
            return bitmap;

        }catch (Exception e){
            return null;
        }
    }
}
