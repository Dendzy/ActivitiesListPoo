package br.com.dandy;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private String email;

    public abstract String getId();

    public Cliente(String nome, String endereco, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    public Cliente() {
        this("","","");
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return  "Nome do Cliente: " + this.nome +"\n" +
                "Endereço do Cliente: " + this.endereco +"\n" +
                "Email do Cliente: " + this.email ;
    }

}