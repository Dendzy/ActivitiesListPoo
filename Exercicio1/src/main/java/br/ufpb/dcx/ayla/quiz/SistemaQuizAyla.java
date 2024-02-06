package br.ufpb.dcx.ayla.quiz;

import java.util.LinkedList;
import java.util.List;

public class SistemaQuizAyla implements SistemaQuiz{

    private List<Pergunta> perguntas;

    public SistemaQuizAyla(){
        this.perguntas = new LinkedList<>();
    }

    public SistemaQuizAyla(List<Pergunta> perguntas){
        this.perguntas = perguntas;
    }

    @Override
    public void cadastraPergunta(Pergunta p) {
        this.perguntas.add(p);
    }

    @Override
    public Pergunta sorteiaPergunta() {
        if (this.perguntas.size()==0) {
            return null;
        } else {
            //TODO: Depois melhorar usado random
            return this.perguntas.get(0);
        }
    }

    public List<Pergunta> getTodasAsPerguntas(){
        return this.perguntas;
    }

}
