package br.com.yagocode.GUI;

import br.com.yagocode.FarmaciaInterface;

import javax.swing.*;
import java.awt.*;

public class FarmacioGUI extends JFrame {
    private FarmaciaInterface sistema;
    JButton botaoCadastro, botaoPesquisa, removerPorQuantidade;
    JLabel titulo;
    JPanel painelTitulo, painelBotoes;

    public static final Font FONTE_TITULO = new Font("Monospaced",Font.BOLD,17);
    public static final Font FONTE_BOTOES = new Font("Monospaced",Font.PLAIN, 17);

    public FarmacioGUI() {
        //Inicializando o Sistema Farmácia.
        setTitle("Sistema Farmácia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLayout(new GridLayout(3,1));
        setResizable(false);
        //Definindo o título.
        titulo = new JLabel("Seja Bem vindo ao Sistema Farmácia!");
        titulo.setFont(FONTE_TITULO);
        painelTitulo = new JPanel();
        painelTitulo.add(titulo);
        //Configurando os Botões
        botaoCadastro = new JButton("Cadastrar Remédio");
        botaoCadastro.setFont(FONTE_BOTOES);
        botaoCadastro.addActionListener(new FarmaciaCadastro(sistema, this));
        botaoPesquisa = new JButton("Pesquisar Remédio");
        botaoPesquisa.setFont(FONTE_BOTOES);
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(botaoCadastro);
        painelBotoes.add(botaoPesquisa);
        //Adicionando os painéis ao painel principal;
        getContentPane().add(painelTitulo);
        getContentPane().add(painelBotoes);
    }

    public static void main(String[] args) {
        FarmacioGUI janela = new FarmacioGUI();
        janela.setVisible(true);
    }
}
