package br.com.yagocode;

import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;

import java.io.IOException;
import java.util.ArrayList;

public interface FarmaciaInterface {
    boolean cadastrarRemedio(Remedio remedio) throws RemedioExistenteException;
    boolean removeRemedioDaLista(String codigo) throws RemedioInexistenteException;
    boolean adicionaRemedioPorQuantidade(int quantidade, String codigo) throws RemedioInexistenteException;
    boolean removeRemedioPorQuantidade(int quantidade,String codigo) throws RemedioInexistenteException;
    ArrayList<Remedio> pesquisaPeloCodigo(String codigo);
    ArrayList<Remedio> pesquisaPeloNome(String nome);
    boolean isEmpty();
    void salvarDados() throws IOException;

}
