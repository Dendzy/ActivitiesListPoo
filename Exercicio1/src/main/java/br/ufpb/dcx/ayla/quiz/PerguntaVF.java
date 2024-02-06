package br.ufpb.dcx.ayla.quiz;

public class PerguntaVF extends Pergunta {
    private String afirmativa;

    public PerguntaVF(String enunciado, String afirmativa, String respostaCorreta) {
        super(enunciado,respostaCorreta);
        this.afirmativa = afirmativa;
    }

    public PerguntaVF() {
        this("","","");
    }

    @Override
    public boolean estahCorretaResposta(String resposta) {
        if(getRespostaCorreta().equals(resposta)) {
            return true;
        }
        return false;
    }
}
