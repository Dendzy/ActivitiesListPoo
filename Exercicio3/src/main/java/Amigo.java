import java.util.Objects;

public class Amigo {
    private String nome;
    private String email;
    private String emailAmigoSorteado;

    public Amigo(String nome,String email,String emailAmigoSorteado) {
        this.nome = nome;
        this.email = email;
        this.emailAmigoSorteado = emailAmigoSorteado;
    }

    public Amigo() {
        this("","","");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amigo amigo)) return false;

        if (!Objects.equals(nome, amigo.nome)) return false;
        if (!Objects.equals(email, amigo.email)) return false;
        return Objects.equals(emailAmigoSorteado, amigo.emailAmigoSorteado);
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (emailAmigoSorteado != null ? emailAmigoSorteado.hashCode() : 0);
        return result;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAmigoSorteado() {
        return emailAmigoSorteado;
    }

    public void setEmailAmigoSorteado(String emailAmigoSorteado) {
        this.emailAmigoSorteado = emailAmigoSorteado;
    }


}
