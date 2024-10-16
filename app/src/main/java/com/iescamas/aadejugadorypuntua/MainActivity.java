package com.iescamas.aadejugadorypuntua;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    HashSet<String>listaJugadoresMadrid = new HashSet<>();
    HashSet<String>listaJugadoresBetis = new HashSet<>();
    HashSet<String>listaJugadoresBarcelona = new HashSet<>();
    HashSet<String>listaJugadoresSevilla = new HashSet<>();

    int idEquipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEquipo = ((RadioGroup)findViewById(R.id.rgEquipos)).getCheckedRadioButtonId();

        ((RadioGroup)findViewById(R.id.rgEquipos)).setOnCheckedChangeListener((radioGroup, i) -> {
            idEquipo = radioGroup.getCheckedRadioButtonId();
            obtenerSpinner(radioGroup.getCheckedRadioButtonId());

        });
        findViewById(R.id.btn_add).setOnClickListener(view -> annadeJugador(idEquipo));

        findViewById(R.id.lbl_jugador).setVisibility(View.INVISIBLE);
        findViewById(R.id.ratingBar).setVisibility(View.INVISIBLE);
        findViewById(R.id.lbl_rating).setVisibility(View.INVISIBLE);

        ((Spinner) findViewById(R.id.spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valorarJugador();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((RatingBar)findViewById(R.id.ratingBar)).setOnRatingBarChangeListener((ratingBar, v, b) -> {
            valorarJugador();
            mostrarToast();

        });

    }

    private void annadeJugador(int i){

        String nombreJugador =((EditText)findViewById(R.id.txt_jugador)).getText().toString();
        ((EditText)findViewById(R.id.txt_jugador)).setText("");

        if(i == R.id.rb1){
            listaJugadoresMadrid.add((nombreJugador));

        }
        if(i == R.id.rb2){
            listaJugadoresBarcelona.add(nombreJugador);

        }
        if(i == R.id.rb3){
            listaJugadoresBetis.add(nombreJugador);

        }
        if(i == R.id.rb4){
            listaJugadoresSevilla.add(nombreJugador);

        }
        obtenerSpinner(i);

    }
    private void obtenerSpinner(int i){

        HashSet<String> miArray = new HashSet<>();

        if(i == R.id.rb1){
            miArray =  new HashSet<>(listaJugadoresMadrid);
        }
        if(i == R.id.rb2){
            miArray =  new HashSet<>(listaJugadoresBarcelona);
        }
        if(i == R.id.rb3){
            miArray =  new HashSet<>(listaJugadoresBetis);
        }
        if(i == R.id.rb4){
            miArray =  new HashSet<>(listaJugadoresSevilla);
        }
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<>(miArray));
        ((Spinner)findViewById(R.id.spinner)).setAdapter(adapter);
        tieneJugadores(miArray);

    }
    private void valorarJugador(){
        ((TextView)findViewById(R.id.lbl_jugador)).setText((String)((Spinner)findViewById(R.id.spinner)).getSelectedItem());
        ((TextView)findViewById(R.id.lbl_rating)).setText(String.valueOf(((RatingBar)findViewById(R.id.ratingBar)).getRating()));
    }
    private void mostrarToast(){
        if(!((TextView)findViewById(R.id.lbl_jugador)).getText().toString().isEmpty()){
            Toast.makeText(this, "El jugador "+ ((TextView)findViewById(R.id.lbl_jugador)).getText().toString() +
                    " con un "+ ((TextView)findViewById(R.id.lbl_rating)).getText().toString() , Toast.LENGTH_SHORT).show();
        }
    }
    private void tieneJugadores(HashSet<String>miArray){

        if(!miArray.isEmpty()) {

            findViewById(R.id.lbl_jugador).setVisibility(View.VISIBLE);
            findViewById(R.id.ratingBar).setVisibility(View.VISIBLE);
            findViewById(R.id.lbl_rating).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.lbl_jugador).setVisibility(View.INVISIBLE);
            findViewById(R.id.ratingBar).setVisibility(View.INVISIBLE);
            findViewById(R.id.lbl_rating).setVisibility(View.INVISIBLE);

        }
    }


}