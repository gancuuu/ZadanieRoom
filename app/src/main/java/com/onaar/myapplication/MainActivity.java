package com.onaar.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    GraDatabase graDatabase;

    ListView listView;
    Button btnDodaj, btnAktualizuj;
    EditText etNazwa, etProducent, etRok, etGatunek;

    List<Gra> gryList;
    ArrayAdapter<Gra> arrayAdapter;

    Gra kliknieGra;


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
                listView = findViewById(R.id.listView);
                etNazwa = findViewById(R.id.etName);
                etProducent = findViewById(R.id.etProducer);
                etRok = findViewById(R.id.etRok);
                etGatunek = findViewById(R.id.etGatunek);

                btnDodaj.setOnClickListener(view -> {

                    Gra gra = new Gra(
                            etNazwa.getText().toString(),
                            etProducent.getText().toString(),
                            Integer.parseInt(etRok.getText().toString()),
                            etGatunek.getText().toString()
                    );

                    graDatabase.zwrocGryDao().wstawGreDoBazy(gra);

                    gryList.clear();
                    gryList.addAll(graDatabase.zwrocGryDao().zwrocWszystkieGry());
                    arrayAdapter.notifyDataSetChanged();


                });
                gryList = graDatabase.zwrocGryDao().zwrocWszystkieGry();

                arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                gryList);

                listView.setAdapter(arrayAdapter);

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        graDatabase.zwrocGryDao().usunZBazy(gryList.get(position));
                        gryList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        return true;
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        kliknieGra = gryList.get(position);

                        etNazwa.setText(kliknieGra.nazwa);
                        etProducent.setText(kliknieGra.producent);
                        etRok.setText(""+kliknieGra.rokWydania);
                        etGatunek.setText(kliknieGra.gatunek);

                        btnAktualizuj.setVisibility(View.VISIBLE);
                        listView.setItemChecked(position,true);
                    }
                });

                btnAktualizuj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kliknieGra.nazwa= etNazwa.getText().toString();
                        kliknieGra.producent = etProducent.getText().toString();
                        kliknieGra.rokWydania = Integer.parseInt(etRok.getText().toString());
                        kliknieGra.gatunek = etGatunek.getText().toString();


                        graDatabase.zwrocGryDao().aktualizujGre(kliknieGra);
                        btnAktualizuj.setVisibility(View.INVISIBLE);
                        listView.clearChoices();
                        kliknieGra = null;
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

    }
}
