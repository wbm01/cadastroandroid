//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Willian Barboza Marioti
//******************************************************

package com.example.willian.cadastro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private DBHelper dh; //Criação dos atributos da classe
    EditText etNome, etCpf, etIdade, etTelefone, etEmail;
    Button btInserir, btListar;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Inicialização dos atributos
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.dh = new DBHelper(this);
        etNome = (EditText) findViewById(R.id.editTextNome);
        etCpf = (EditText) findViewById(R.id.editTextCPF);
        etIdade = (EditText) findViewById(R.id.editTextIdade);
        etTelefone = (EditText) findViewById(R.id.editTextTelefone);
        etEmail = (EditText) findViewById(R.id.editTextEmail);

        btInserir = (Button) findViewById(R.id.buttonInserirD);
        btListar = (Button) findViewById(R.id.buttonListar);
        btVoltar = (Button) findViewById(R.id.buttonVoltar);



        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Condições e ações após clicar o botão Inserir
                if(etNome.getText().length()>0 && etCpf.getText().length()>0 && etIdade.getText().length()>0 && etTelefone.getText().length()>0 && etEmail.getText().length()>0){
                    dh.insert(etNome.getText().toString(), etCpf.getText().toString(), etIdade.getText().toString(), etTelefone.getText().toString(), etEmail.getText().toString());
                    vibrar2();
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Sucesso!");
                    adb.setMessage("Cadastro Realizado!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
                else{
                    vibrar2();
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Erro!");
                    adb.setMessage("Todos os campos devem ser preenchidos!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Condições e ações após clicar o botão Listar
                vibrar2();
                List<Contato> contatos = dh.recuperaDados();
                if (contatos == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastrados!");
                    vibrar2();
                    adb.show();
                    return;
                }
                for (int i = 0; i < contatos.size(); i++) {
                    Contato contato = (Contato) contatos.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: " + contato.getNome() + "\nCPF: " + contato.getCpf() + "\nIdade: " + contato.getIdade() + "\nTelefone: " + contato.getTelefone() + "\nE-mail: " + contato.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            vibrar2();
                            dialogInterface.dismiss();
                        }
                    });
                    adb.show();
                }
            }
            });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Ações após clicar o botão Voltar
                vibrar2();
                chamarprimeiratela();
            }
        });
    }

    private void vibrar2(){ //Criação do método Vibrar2
        Vibrator vv = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milisegundos = 100;
        vv.vibrate(milisegundos);
    }

    void chamarprimeiratela(){ //Criação do método Chamar Primeira Tela
        Intent chamar = new Intent();
        chamar.setClass(Main2Activity.this, MainActivity.class);
        startActivity(chamar);
        finish();
    }
}
