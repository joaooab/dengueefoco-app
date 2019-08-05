package br.com.dengueefocoApp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dengueefocoApp.R;

public class LoginActivity extends AppCompatActivity {

    private TextView login;
    private TextView senha;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextSenha);
        entrar = findViewById(R.id.buttonEntrar);
        entrar.setOnClickListener(v -> {
            if (isEmpty(login.getText())) {
                login.setError("Campo obrigatório não informado");
            }
            if (isEmpty(senha.getText())) {
                senha.setError("Campo obrigatório não informado");
            }
        });
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private Boolean isEmpty(CharSequence text) {
        return text == null || text.equals("");
    }

}
