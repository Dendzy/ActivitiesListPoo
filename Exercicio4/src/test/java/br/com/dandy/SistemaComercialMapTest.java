package br.com.dandy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;

public class SistemaComercialMapTest {
    SistemaComercialMap sistemaComercialMap;
    @BeforeEach
    void setUp() {
        this.sistemaComercialMap = new SistemaComercialMap();
    }
    @Test
    public void testaCadastroProdutos() {
        SistemaComercialMap sistema = new SistemaComercialMap();
        Collection<Produto> alimentos =
                sistema.pesquisaProdutosDaCategoria(CategoriaProduto.ALIMENTO);
        assertTrue(alimentos.size()==0);

    }

    @Test
    public void testaCadastraProdutoSetsAndGets() {
        Produto produtoTeste = new Produto("122","Produto Teste",20.00,
                1,CategoriaProduto.ALIMENTO);
        sistemaComercialMap.cadastraProduto(produtoTeste);
        assertTrue(sistemaComercialMap.existeProduto(produtoTeste));
        Collection<Produto> alimentos =
                sistemaComercialMap.pesquisaProdutosDaCategoria(CategoriaProduto.ALIMENTO);
        assertTrue(alimentos.size()==1);

        assertTrue(produtoTeste.getCodigo().equals("122"));
        produtoTeste.setCodigo("001");
        assertTrue(produtoTeste.getCodigo().equals("001"));

        assertTrue(produtoTeste.getDescricao().equals("Produto Teste"));
        produtoTeste.setDescricao("Produto Teste Mudou");
        assertTrue(produtoTeste.getDescricao().equals("Produto Teste Mudou"));

        assertTrue(produtoTeste.getPrecoVenda() == 20.00);
        produtoTeste.setPrecoVenda(10.00);
        assertTrue(produtoTeste.getPrecoVenda() == 10.00);

        assertTrue(produtoTeste.getCategoria().equals(CategoriaProduto.ALIMENTO));
        produtoTeste.setCategoria(CategoriaProduto.PRODUTO_DE_LIMPEZA);
        assertTrue(produtoTeste.getCategoria().equals(CategoriaProduto.PRODUTO_DE_LIMPEZA));


    }

}
