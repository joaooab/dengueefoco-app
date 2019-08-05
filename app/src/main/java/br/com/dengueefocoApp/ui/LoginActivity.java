package br.com.dengueefocoApp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dengueefocoApp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView edtLogin = findViewById(R.id.editTextLogin);
        TextView edtSenha = findViewById(R.id.editTextSenha);
        Button entrar = findViewById(R.id.buttonEntrar);
        entrar.setOnClickListener(v -> {
            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();
            boolean sucess = true;
            if (isEmpty(login)) {
                edtLogin.setError("Campo obrigatório não informado");
                sucess = false;
            }
            if (isEmpty(senha)) {
                edtSenha.setError("Campo obrigatório não informado");
                sucess = false;
            }
            if(sucess) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private Boolean isEmpty(String text) {
        return text == null || text.equals("");
    }

}
