package br.com.dengueefocoApp.model;

public enum Distrito {
    NOROESTE("Noroeste"), NORTE("Norte"), SUDOESTE("Sudoeste"), LESTE("Sudoeste"), CENTRO("Centro"), OESTE("Oeste"), Sul("Sul");

    public String valor;
    Distrito(String valor) {
        this.valor = valor;
    }
}
