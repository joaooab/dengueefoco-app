package br.com.dengueefocoApp.model;

import java.util.ArrayList;
import java.util.List;

public enum Distrito {
    NOROESTE("Noroeste"), NORTE("Norte"), SUDOESTE("Sudoeste"), LESTE("Sudoeste"), CENTRO("Centro"), OESTE("Oeste"), Sul("Sul");

    public String valor;

    Distrito(String valor) {
        this.valor = valor;
    }

    static public List<String> getDistrito() {
        List<String> distritos = new ArrayList<>();
        for (Distrito distrito : Distrito.values()) {
            distritos.add(distrito.valor);
        }

        return distritos;
    }
}
