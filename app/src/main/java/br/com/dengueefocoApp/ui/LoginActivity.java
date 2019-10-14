package br.com.dengueefocoApp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dengueefocoApp.Configuracao;
import br.com.dengueefocoApp.R;
import br.com.dengueefocoApp.api.Api;
import br.com.dengueefocoApp.api.RetrofitClient;
import br.com.dengueefocoApp.model.TipoUsuario;
import br.com.dengueefocoApp.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

	private Api api = RetrofitClient.getApi();
	private Configuracao configuracao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		configuracao = new Configuracao(this);

		TextView edtLogin = findViewById(R.id.editTextLogin);
		TextView edtSenha = findViewById(R.id.editTextSenha);
		Button entrar = findViewById(R.id.buttonEntrar);
		entrar.setOnClickListener(v -> {
			String email = edtLogin.getText().toString();
			String senha = edtSenha.getText().toString();
			boolean sucess = true;
			if (isEmpty(email)) {
				edtLogin.setError("Campo obrigatório não informado");
				sucess = false;
			}
			if (isEmpty(senha)) {
				edtSenha.setError("Campo obrigatório não informado");
				sucess = false;
			}
			if (sucess) {
				Usuario usuario = new Usuario();
				usuario.setEmail(email);
				usuario.setSenha(senha);
				login(edtLogin, usuario);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (configuracao.getUsuarioLogado() != null) {
			iniciaTelaPrincipal();
		}
	}

	private void login(TextView edtLogin, Usuario usuario) {
		if (isAcessoUsuarioPadrao(usuario)) {
			criaUsuarioPadrao();
			iniciaTelaPrincipal();
			return;
		}
		api.login(usuario).enqueue(new Callback<JsonElement>() {
			@Override
			public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
				if (response.code() == 200) {
					Usuario usuarioLogado = new Gson().fromJson(response.body(), Usuario.class);
					configuracao.setUsuarioLogado(usuarioLogado);
					iniciaTelaPrincipal();
				} else {
					edtLogin.setError("Usuário não encontrado");
				}
			}

			@Override
			public void onFailure(Call<JsonElement> call, Throwable t) {
				Log.e(this.getClass().toString(), t.getMessage());
			}
		});
	}

	private void criaUsuarioPadrao() {
		Usuario usuario = new Usuario();
		usuario.setId(99L);
		usuario.setEmail("dengue@foco");
		usuario.setSenha("1234");
		usuario.setTipoUsuario(TipoUsuario.ADMIM);
		usuario.setNome("Dengue e foco");
		configuracao.setUsuarioLogado(usuario);
	}

	private boolean isAcessoUsuarioPadrao(Usuario usuario) {
		return usuario.getEmail().equals("dengue@foco") && usuario.getSenha().equals("1234");
	}

	private void iniciaTelaPrincipal() {
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
		return super.onCreateView(parent, name, context, attrs);
	}

	private Boolean isEmpty(String text) {
		return text == null || text.equals("");
	}

}