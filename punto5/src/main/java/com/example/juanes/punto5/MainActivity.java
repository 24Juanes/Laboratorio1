package com.example.juanes.punto5;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {
    private Spinner Spcolor1,Spcolor2, Spcolor3, Spcolor4;
    private TextView tresultado;
    private Button Bcalcular;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bcalcular=(Button)findViewById(R.id.Bcalcular);
        Spcolor1=findViewById(R.id.sColor1);
        Spcolor2=findViewById(R.id.sColor2);
        Spcolor3=findViewById(R.id.sColor3);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.Colores1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spcolor1.setAdapter(adapter);
        Spcolor2.setAdapter(adapter);
        Spcolor3.setAdapter(adapter);

        Spcolor4=findViewById(R.id.sColores4);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Colores2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spcolor4.setAdapter(adapter2);

        Bcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ShowDialog("El Resultado es:",BuildNumber(Spcolor1.getSelectedItemPosition(),Spcolor2.getSelectedItemPosition(),
                        Spcolor3.getSelectedItemPosition())+" "+Tolerancia (Spcolor4.getSelectedItemPosition()));
            }
        });
    }

    private void ShowDialog(String Title, String Caption)
    {
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage(Caption)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_menu_info_details)
                .show();
    }

    private String Tolerancia (int Value)
    {
        if (Value == 0)
            return "+5% tolerancia";
        else
            return "+10% tolerancia";
    }

    private String BuildNumber(int Value1, int Value2, int Value3)
    {
        String Solucion;

        Solucion = Integer.toString(Value1) + Integer.toString(Value2);

        double Total = Integer.parseInt(Solucion)*pow(10,Value3);

        if (Total/1000 >= 1 && Total/1e3 < 1000 )
        {
            return (String.valueOf(Total / 1e3) + "KΩ");
        }

        if (Total/1e6 >= 1)
        {
            return (String.valueOf(Total / 1e6) + "MΩ");
        }else{

            return (String.valueOf(Total)+"Ω");
        }



    }


}
