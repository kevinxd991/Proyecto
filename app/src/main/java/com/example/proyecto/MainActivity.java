package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Perfil", "Cursos", "Horario Semanal", "Salir"};
    String mDescription[] = {" El perfil del usuario", "Tu agenda de hoy", "Tu agenda Semanal ", "Salir de las aplicación "};
    int images[] = {R.drawable.perfil, R.drawable.calendario, R.drawable.calendario, R.drawable.alir};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position ==  0) {
                    Toast.makeText(MainActivity.this, "Ingresaste a tu perfil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Perfil.class);
                    startActivity(intent);

                }
                if (position ==  1) {
                    Toast.makeText(MainActivity.this, "Ingresaste a tu horario de clase", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Horario.class);
                    startActivity(intent);
                }
                if (position ==  2) {
                    Toast.makeText(MainActivity.this, "Ingresaste a tu horario semanal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HorarioSemanal.class);
                    startActivity(intent);
                }
                if (position ==  3) {
                    Toast.makeText(MainActivity.this, "Saliste", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.txtDia, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.txtDia);
            TextView myDescription = row.findViewById(R.id.txFecha);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);




            return row;
        }
    }
}
