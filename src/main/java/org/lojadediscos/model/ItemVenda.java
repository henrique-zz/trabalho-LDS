package org.lojadediscos.model;

import jakarta.persistence.*;

/**
 * Classe base para a criação da tabela "ItemVenda" no banco de dados, com os atributos sendo as colunas desta tabela.
 */

@Entity
@Table(name = "itens_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // Cada item está ligado a uma venda
    @JoinColumn(name = "venda_id") // define a chave estrangeira
    private Venda venda;

    @ManyToOne // Cada item está ligado a um disco
    @JoinColumn(name = "disco_id")
    private Disco disco;

    private int quantidade;
    private double precoUnitario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
