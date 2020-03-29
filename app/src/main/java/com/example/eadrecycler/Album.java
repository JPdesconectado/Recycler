package com.example.eadrecycler;

import java.io.Serializable;

public class Album implements Serializable {
    private int imgCapa;
    private String titulo;
    private String genero;
    private int ano;

    public Album(){

    }

    public Album(int imgCapa, String titulo, String genero, int ano) {
        this.imgCapa = imgCapa;
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    public int getImgCapa() {
        return imgCapa;
    }

    public void setImgCapa(int imgCapa) {
        this.imgCapa = imgCapa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
