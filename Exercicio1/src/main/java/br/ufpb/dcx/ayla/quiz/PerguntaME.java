package br.ufpb.dcx.ayla.quiz;

import java.util.Arrays;
import java.util.Objects;

public class PerguntaME extends Pergunta {

    private String [] alternativas;

    public PerguntaME(String enunciado, String [] alternativas,
                      String respostaCorreta){
        super(enunciado, respostaCorreta);
        this.alternativas = alternativas;
    }

    public PerguntaME(){
        this("",new String[]{}, "");
//        super();
//        this.alternativas = new String[]{};
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerguntaME that = (PerguntaME) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Objects.equals(super.getEnunciado(), that.getEnunciado())
                && Objects.equals (super.getRespostaCorreta(), that.getRespostaCorreta())
                && Arrays.equals(alternativas, that.alternativas);
    }

    @Override
    public int hashCode() {
        return super.getEnunciado().hashCode()
                + super.getRespostaCorreta().hashCode()
                +Arrays.hashCode(alternativas);
    }

    @Override
    public boolean estahCorretaResposta(String resposta) {
        return (resposta.startsWith(super.getRespostaCorreta()));
    }
}
