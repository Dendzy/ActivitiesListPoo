package br.com.yagocode;

import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;
import java.util.NoSuchElementException;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SistemaFarmacia implements FarmaciaInterface {

    private HashMap<String,Remedio> remedioMap;

    public SistemaFarmacia() {
        remedioMap = new HashMap<>();
    }

    public Remedio getRemedio(String codigo) throws RemedioInexistenteException {
        if(remedioMap.isEmpty()) {
            throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(codigo)) {
                return r;
            }
        }
        throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
    }

    @Override
    public boolean cadastrarRemedio(Remedio remedio) throws RemedioExistenteException {
        if(remedioMap.isEmpty()) {
            remedioMap.put(remedio.getCodigo(),remedio);
            return true;
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(remedio.getCodigo())) {
                throw new RemedioExistenteException("Esse Remédio Já está Cadastrado no Sistema!");
            }
        }
        remedioMap.put(remedio.getCodigo(),remedio);
        return true;
    }

    @Override
    public boolean removeRemedioDaLista(String codigo) throws RemedioInexistenteException {
        if(remedioMap.isEmpty()) {
            throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(codigo)) {
                remedioMap.remove(codigo);
                return true;
            }
        }
        throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
    }

    @Override
    public boolean adicionaRemedioPorQuantidade(int quantidade, String codigo) throws RemedioInexistenteException {
        if(remedioMap.isEmpty()) {
            throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(codigo)) {
                r.setQuantidade(r.getQuantidade() + quantidade);
                return true;
            }
        }
        throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
    }

    @Override
    public boolean removeRemedioPorQuantidade(int quantidade, String codigo) throws RemedioInexistenteException{
        if(remedioMap.isEmpty()) {
            throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(codigo)) {
                r.setQuantidade(r.getQuantidade() - quantidade);
                return true;
            }
        }
        throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
    }

    @Override
    public ArrayList<Remedio> pesquisaPeloCodigo(String codigo) {
        ArrayList<Remedio> remediosPesquisados = new ArrayList<>();
        if(remedioMap.isEmpty()) throw new NoSuchElementException();
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(codigo)) {
                remediosPesquisados.add(r);
            }
        }
        return remediosPesquisados;
    }

    @Override
    public ArrayList<Remedio> pesquisaPeloNome(String nome) {
        ArrayList<Remedio> remediosPesquisados = new ArrayList<>();
        if(remedioMap.isEmpty()) throw new NoSuchElementException();
        for(Remedio r: remedioMap.values()) {
            if(r.getNome().equals(nome)) {
                remediosPesquisados.add(r);
            }
        }
        return remediosPesquisados;
    }

    public int tamanho(){
        return remedioMap.size();
    }
}
