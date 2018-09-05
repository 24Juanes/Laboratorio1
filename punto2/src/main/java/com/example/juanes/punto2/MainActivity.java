package com.example.juanes.punto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText eNombre,ePassword, eRepPassword, eCorreo;
    private TextView tResultado;
    private String sexo = "Masculino" , ciudad= "Medellin";
    private Spinner sCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre= findViewById(R. id.eNombre);
        ePassword= findViewById(R .id.ePassword);
        eRepPassword= findViewById(R. id.ePassword);
        eCorreo= findViewById(R. id.eCorreo);
        tResultado=findViewById(R. id.tResultado);
        sCiudades=findViewById(R.id.sCiudades);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);
        sCiudades.setOnItemSelectedListener(this);

    }

    public void guardarClicked(View view) {
        String name, pass, repass, email;

        name= eNombre.getText().toString();
        pass= ePassword.getText().toString();
        repass= eRepPassword.getText().toString();
        email= eCorreo.getText().toString();

        if (name.equals("") || pass.equals("")||repass.equals("")||email.equals(""))
            Toast.makeText(getApplicationContext(),  "Debe digitar todos los campos", Toast.LENGTH_SHORT).show();
        else
            if (pass.equals(repass))
                tResultado.setText(name + " - "+email + " - "+sexo  + " - "+ciudad);
            else
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();



    }

    public void onRadioButtonClicked(View view) {
        int id=view.getId();

        if (id==R.id.rmasculino)
            sexo="Masculino";
        else
            sexo="Femenino";
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ciudad=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
