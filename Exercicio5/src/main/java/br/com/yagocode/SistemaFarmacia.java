package br.com.yagocode;

import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;

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
    public boolean removeRemedioDaLista(Remedio remedio) throws RemedioInexistenteException {
        if(remedioMap.isEmpty()) {
            throw new RemedioInexistenteException("Esse remédio não existe no Sistema!");
        }
        for(Remedio r: remedioMap.values()) {
            if(r.getCodigo().equals(remedio.getCodigo())) {
                remedioMap.remove(remedio.getCodigo());
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

    public int tamanho(){
        return remedioMap.size();
    }
}
