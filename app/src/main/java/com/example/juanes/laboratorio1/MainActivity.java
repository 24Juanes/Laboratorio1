package com.example.juanes.laboratorio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String[] datos = new String[]{"Dolares", "Euros", "Libras"};

    private Spinner moneda1, moneda2;
    private EditText valor;
    private TextView cambio;

    final private double factorDolarEuro = 0.86;
    final private double factorEuroLibra = 1.11;
    final private double factorDolarLibra = 0.78;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneda1 = findViewById(R.id.sMoneda1);
        moneda2 = findViewById(R.id.sMoneda2);
        valor = findViewById(R.id.Evalor);
        cambio = findViewById(R.id.tvalor);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, datos);

        moneda1.setAdapter(adapter);
    }

    public void change(View view) {
        String m1 = moneda1.getSelectedItem().toString();
        String m2 = moneda2.getSelectedItem().toString();
        double v1 = Double.parseDouble(valor.getText().toString());
        double change = Conversion(m1, m2, v1);

        if (change>0){
            cambio.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s",v1,m1,change,m2));
            valor.setText("");

        }else {
            cambio.setText(String.format("Usted recibirá"));
            Toast.makeText(getApplicationContext(), "No se puede convertir", Toast.LENGTH_SHORT).show();
        }


    }

    private double Conversion(String m1, String m2, double v1) {
        double cambiototal=0;
        switch (m1){
            case "Dolares":
                if(m2.equals("Euros")){
                    cambiototal=v1  *factorDolarEuro;
                }
                if(m2.equals("Libras")){
                    cambiototal=v1/factorDolarLibra;
                }

                break;
            case "Euros":
                if(m2.equals("Dolares")){
                    cambiototal=v1  /factorDolarEuro;
                }
                if(m2.equals("Libras")){
                    cambiototal=v1*factorEuroLibra;
                }
                break;
            case "Libras":
                if (m2.equals("Dolares"))
                    cambiototal = v1/factorDolarLibra;
                if(m2.equals("Euros"))
                    cambiototal =v1/factorEuroLibra;
                break;

        }
        return cambiototal;
    }
}

