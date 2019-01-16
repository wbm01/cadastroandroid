//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Willian Barboza Marioti
//******************************************************

package com.example.willian.cadastro;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btInserir; //Criação dos atributos da classe


    @Override
    protected void onCreate(Bundle savedInstanceState) { //Inicialização dos atributos
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInserir = (Button) findViewById(R.id.bottonInserir);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Ao clicar no botão, ele irá vibrar e chamar a Main2Activity - Método
                vibrar();
                chamarsegundatela();
            }
        });

    }

    private void vibrar(){ //Criação do método Vibrar
        Vibrator vv = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milisegundos = 100;
        vv.vibrate(milisegundos);
    }

    void chamarsegundatela(){ //Criação do método para chamar a Segunda Tela
        Intent chamar = new Intent();
        chamar.setClass(MainActivity.this, Main2Activity.class);
        startActivity(chamar);
        finish();
    }
}
