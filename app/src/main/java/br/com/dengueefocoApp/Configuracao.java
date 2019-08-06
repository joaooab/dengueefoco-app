package br.com.dengueefocoApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import br.com.dengueefocoApp.model.TipoUsuario;
import br.com.dengueefocoApp.model.Usuario;

public class Configuracao {

    private Usuario usuario;
    private Context context;
    private SharedPreferences sharedPreferences;

    public Configuracao(Context contexto) {
        this.context = contexto;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexto);
    }

    public Usuario getUsuarioLogado() {
        if (usuario != null) return usuario;
        String usuarioJson = sharedPreferences
                .getString(this.context.getString(R.string.config_usuario_logado), "");
        try {
            if (!usuarioJson.equals("")) {
                usuario = new Gson().fromJson(usuarioJson, Usuario.class);
                return usuario;
            }
        } catch (Exception e) {
            Log.e(Configuracao.class.getSimpleName(), "Erro ao obter o usuário: " + e.getMessage());
        }
        return new Usuario();
    }

    public void setUsuarioLogado(Usuario usuario) {
        String usuarioJson = new Gson().toJson(usuario);
        sharedPreferences.edit().putString(
                this.context.getString(R.string.config_usuario_logado), usuarioJson).apply();
    }

    public Boolean isUsuarioLogadoAgente() {
        Usuario usuarioLogado = getUsuarioLogado();
        return usuarioLogado != null && usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE);
    }

}
