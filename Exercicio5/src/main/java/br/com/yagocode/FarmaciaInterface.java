package br.com.yagocode;

import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;

public interface FarmaciaInterface {
    public boolean cadastrarRemedio(Remedio remedio) throws RemedioExistenteException;
    public boolean removeRemedioDaLista(Remedio remedio) throws RemedioInexistenteException;
    public boolean adicionaRemedioPorQuantidade(int quantidade, String codigo) throws RemedioInexistenteException;
    public boolean removeRemedioPorQuantidade(int quantidade,String codigo) throws RemedioInexistenteException;
}
