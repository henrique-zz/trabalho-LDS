package org.lojadediscos.model;

import jakarta.persistence.*;
/**
 * Classe responsável por representar um disco na loja.
 */
@Entity
@Table(name = "discos") // diz que a classe irá virar a tabela "discos" do banco de dados
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String artista;
    private String genero;
    private double preco;
    private int estoque;

    public Disco() {

    }

    public Disco(int id, String titulo, String artista, String genero, double preco, int estoque) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Disco(String titulo, String artista, String genero, double preco, int estoque) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return titulo + " (ID: " + id + ")";
    }

}