package com.onaar.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    GraDatabase graDatabase;

    Button btnDodaj, btnAktualizuj;
    EditText etNazwa, etProducent, etRok, etGatunek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
                graDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        GraDatabase.class,
                        "gry_db"
                ).allowMainThreadQueries().build();

                btnDodaj = findViewById(R.id.btnAdd);
                btnAktualizuj = findViewById(R.id.btnUpdate);

                etNazwa = findViewById(R.id.etName);
                etProducent = findViewById(R.id.etProducer);
                etRok = findViewById(R.id.etYear);
                etGatunek = findViewById(R.id.etGenre);

                btnDodaj.setOnClickListener(view -> {

                    Gra gra = new Gra(
                            etNazwa.getText().toString(),
                            etProducent.getText().toString(),
                            Integer.parseInt(etRok.getText().toString()),
                            etGatunek.getText().toString()
                    );

                    graDatabase.zwrocGryDao().wstawGreDoBazy(gra);
                });
            }
        }
