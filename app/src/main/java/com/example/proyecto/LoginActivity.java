package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    private DatabaseReference BDReferencia;
    private EditText txtUsuario, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtClave = findViewById(R.id.txtClave);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave.setText("abcde");
        txtUsuario.setText("16100916");
        String user =txtUsuario.getText().toString();
        String path ="Usuario/"+user;
        BDReferencia = FirebaseDatabase.getInstance().getReference("Usuario");
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user =txtUsuario.getText().toString();
                BDReferencia.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Usuario user1=new Usuario(dataSnapshot.child("Contraseña").getValue().toString(),
                                    dataSnapshot.child("Contraseña").getValue().toString(),
                                    dataSnapshot.child("Nombre").getValue().toString());

                            String pass = txtClave.getText().toString();
                            if(pass.equals(user1.getContraseña().toString())){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("Apellido",user1.getApellido());
                                intent.putExtra("Nombre",user1.getNombre());
                                //--------------------------
                                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("user",user);
                                editor.commit();
                                //--------------------------
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this,"Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();

    }


}
