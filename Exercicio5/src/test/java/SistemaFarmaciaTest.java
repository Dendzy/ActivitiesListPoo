import br.com.yagocode.CategoriaRemedio;
import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;
import br.com.yagocode.Remedio;
import br.com.yagocode.SistemaFarmacia;
import br.com.yagocode.TipoRemedio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static br.com.yagocode.CategoriaRemedio.*;
import static br.com.yagocode.TipoRemedio.*;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaFarmaciaTest {
    SistemaFarmacia sistema;
    @BeforeEach
    void setUp() {
        sistema = new SistemaFarmacia();
    }

    @Test
    void testTipoRemedio() {
        assertTrue(sistema.tamanho() == 0);
        Remedio remedioTeste1 = new Remedio("Dipirona","001",
                                            20.00,0,
                ANALGESICOS, TipoRemedio.TIPO_COMPRIMIDO);
        assertTrue(remedioTeste1.getNome().equals("Dipirona"));
        assertTrue(remedioTeste1.getCodigo().equals("001"));
        assertTrue(remedioTeste1.getPreco() == 20.00);
        assertTrue(remedioTeste1.getQuantidade() == 0);
        assertTrue(remedioTeste1.getCategoria().equals(ANALGESICOS));
        assertTrue(remedioTeste1.getTipo().equals(TIPO_COMPRIMIDO));
    }

    @Test
    void testSistemaCadastrarERemove() throws RemedioExistenteException, RemedioInexistenteException {
        assertTrue(sistema.tamanho() == 0);
        Remedio remedioTeste1 = new Remedio("Dipirona","001",
                20.00,0,
                ANALGESICOS, TipoRemedio.TIPO_COMPRIMIDO);
        assertTrue(sistema.cadastrarRemedio(remedioTeste1));
        assertThrows(RemedioExistenteException.class, ()->
                sistema.cadastrarRemedio(remedioTeste1));
        Remedio remedioTeste2 = new Remedio("Cefalexina","002",
                50.00,0,
                ANALGESICOS, TipoRemedio.TIPO_COMPRIMIDO);
        assertTrue(sistema.cadastrarRemedio(remedioTeste2));
        assertTrue(sistema.tamanho() == 2);
        assertTrue(sistema.removeRemedioDaLista(remedioTeste1.getCodigo()));
        assertThrows(RemedioInexistenteException.class, ()->
                sistema.removeRemedioDaLista(remedioTeste1.getCodigo()));
        assertTrue(sistema.tamanho() == 1);
    }

    @Test
    void testaAdicionaRemedioPorQuantidade() throws RemedioExistenteException, RemedioInexistenteException {
        Remedio remedioTeste1 = new Remedio("Dipirona","001",
                20.00,0,
                ANALGESICOS, TipoRemedio.TIPO_COMPRIMIDO);
        sistema.cadastrarRemedio(remedioTeste1);
        assertThrows(RemedioInexistenteException.class, ()->
                sistema.adicionaRemedioPorQuantidade(2,"0023"));
        assertTrue(sistema.adicionaRemedioPorQuantidade(5,"001"));
        assertTrue(sistema.getRemedio("001").getQuantidade() == 5);
        assertThrows(RemedioInexistenteException.class, ()->
                sistema.removeRemedioPorQuantidade(2,"123"));
        assertTrue(sistema.removeRemedioPorQuantidade(3,"001"));
        assertTrue(sistema.getRemedio("001").getQuantidade() == 2);
    }
}
