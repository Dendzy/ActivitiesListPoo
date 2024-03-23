package br.com.yagocode;

import java.io.Serializable;
import java.util.Objects;

public class Remedio implements Comparable<Remedio>, Serializable {
    private String nome;
    private String codigo;
    private double preco;
    private int quantidade;
    private CategoriaRemedio categoria;
    private TipoRemedio tipo;

    public Remedio(String nome, String codigo, double preco,int quantidade
                   ,CategoriaRemedio categoria, TipoRemedio tipo) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remedio remedio = (Remedio) o;

        return Objects.equals(codigo, remedio.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public int compareTo(Remedio o) {
        if(this.nome.compareTo(o.getNome()) < 0) {
            return -1;
        } else if (this.nome.compareTo(o.getNome()) > 0) {
            return 1;
        }
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public CategoriaRemedio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRemedio categoria) {
        this.categoria = categoria;
    }

    public TipoRemedio getTipo() {
        return tipo;
    }

    public void setTipo(TipoRemedio tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "Informações do Remedio - " + "\n" +
                "Nome: " + nome + "\n" +
                "Código: " + codigo + "\n" +
                "Preço: " + preco + "\n" +
                "Quantidade Disponível: " + quantidade + "\n" +
                "Categoria: " + categoria + "\n" +
                "Tipo: " + tipo;
    }
}

