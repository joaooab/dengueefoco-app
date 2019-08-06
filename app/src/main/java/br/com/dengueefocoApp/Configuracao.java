package br.com.dengueefocoApp;

import br.com.dengueefocoApp.model.TipoUsuario;
import br.com.dengueefocoApp.model.Usuario;

public class Configuracao {

    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Configuracao.usuarioLogado = usuarioLogado;
    }

    public static Boolean isUsuarioLogadoAgente() {
		Usuario usuarioLogado = Configuracao.getUsuarioLogado();
		return usuarioLogado != null && usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE);
    }

}
