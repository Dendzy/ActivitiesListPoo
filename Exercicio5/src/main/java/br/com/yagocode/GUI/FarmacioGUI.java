package br.com.yagocode.GUI;

import br.com.yagocode.*;
import br.com.yagocode.Exceptions.RemedioExistenteException;
import br.com.yagocode.Exceptions.RemedioInexistenteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class FarmacioGUI extends JFrame {
    private FarmaciaInterface sistema;
    JButton botaoCadastro, botaoPesquisa, removerPorQuantidade, removerRemedio;
    JLabel titulo;
    JPanel painelTitulo, painelBotoes;
    FarmaciaCadastroGUI janelaCadastro;
    FarmaciaPesquisaGUI janelaPesquisa;

    public static final Font FONTE_TITULO = new Font("Monospaced",Font.BOLD,17);
    public static final Font FONTE_BOTOES = new Font("Arial",Font.BOLD, 13);

    public FarmacioGUI() {
        //Inicializando o Sistema Farmácia.
        setTitle("Sistema Farmácia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,150);
        setLayout(new GridLayout(2,1));
        setResizable(false);
        sistema = new SistemaFarmacia();
        if(sistema.isEmpty()) JOptionPane.showMessageDialog(null,"Erro ao carregar os dados!");
        //Definindo o título.
        titulo = new JLabel("Seja Bem vindo ao Sistema Farmácia!");
        titulo.setFont(FONTE_TITULO);
        painelTitulo = new JPanel();
        painelTitulo.add(titulo);
        //Configurando o menu
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu cadastroMenu = new JMenu("Cadastrar");
        JMenuItem cadastroMenuItem = new JMenuItem("Cadastrar Remédio");
        cadastroMenuItem.addActionListener(e -> janelaCadastro.mostrar());
        cadastroMenu.add(cadastroMenuItem);
        JMenu pesquisaMenu = new JMenu("Pesquisar");
        JMenuItem pesquisaMenuItem = new JMenuItem("Pesquisar Remédio");
        pesquisaMenuItem.addActionListener(e -> janelaPesquisa.mostrar());
        pesquisaMenu.add(pesquisaMenuItem);
        JMenu removeMenu = new JMenu("Remover");
        JMenuItem removeMenuItem = new JMenuItem("Remover Remédio");
        removeMenuItem.addActionListener(e -> logicaRemover());
        removeMenu.add(removeMenuItem);
        JMenu salvarMenu = new JMenu("Salvar");
        JMenuItem salvarMenuItem = new JMenuItem("Salvar Dados");
        salvarMenu.add(salvarMenuItem);
        salvarMenuItem.addActionListener(e -> {
            try {
                sistema.salvarDados();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Erro ao Salvar Dados");
            }
        });
        menu.add(cadastroMenu);
        menu.add(pesquisaMenu);
        menu.add(removeMenu);
        menu.add(salvarMenu);
        //Configurando os Botões
        botaoCadastro = new JButton("Cadastrar Remédio");
        botaoCadastro.setFont(FONTE_BOTOES);
        janelaCadastro = new FarmaciaCadastroGUI(sistema, this);
        botaoCadastro.addActionListener(e -> janelaCadastro.mostrar());
        janelaPesquisa = new FarmaciaPesquisaGUI(sistema,this);
        botaoPesquisa = new JButton("Pesquisar Remédio");
        botaoPesquisa.addActionListener(e -> janelaPesquisa.mostrar());
        botaoPesquisa.setFont(FONTE_BOTOES);
        removerRemedio = new JButton("Remover Remédio");
        removerRemedio.addActionListener(e -> logicaRemover());
        removerRemedio.setFont(FONTE_BOTOES);
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(botaoCadastro);
        painelBotoes.add(botaoPesquisa);
        painelBotoes.add(removerRemedio);
        //Adicionando os painéis ao painel principal;
        getContentPane().add(painelTitulo);
        getContentPane().add(painelBotoes);

    }
    public void logicaRemover() {
        String codigo = JOptionPane.showInputDialog(this,"Digite o código do Remédio!");
        try {
            if(sistema.removeRemedioDaLista(codigo)) {
                JOptionPane.showMessageDialog(this,"Remédio Removido!");
            }
        } catch (RemedioInexistenteException i) {
            JOptionPane.showMessageDialog(this,"Esse remédio não Existe!");
        }
    }
    public class FarmaciaCadastroGUI extends JFrame {
        private FarmaciaInterface sistema;
        private JFrame janelaPrincipal;
        private static final Map<String, CategoriaRemedio> mapaCategoria = new HashMap<>();
        private static final Map<String, TipoRemedio> mapaTipo = new HashMap<>();
        JButton cadastrar,cancelar;

        public FarmaciaCadastroGUI(FarmaciaInterface sistema, JFrame janela) {
            this.sistema = sistema;
            this.janelaPrincipal = janela;
            inicializar();
        }

        static {
            mapaCategoria.put("Analgésico", CategoriaRemedio.ANALGESICOS);
            mapaCategoria.put("Anestésicos", CategoriaRemedio.ANESTESICOS);
            mapaCategoria.put("Anti-alérgicos", CategoriaRemedio.ANTIALERGICO);
            mapaCategoria.put("Anti-inflamatório", CategoriaRemedio.ANTI_INFLAMATORIO);
            mapaCategoria.put("Anti-bacteriano", CategoriaRemedio.ANTIBACTERIANOS);
            mapaCategoria.put("Anti-microbiano", CategoriaRemedio.ANTIMICROBIANOS);
            mapaCategoria.put("Outros", CategoriaRemedio.OUTROS);

            mapaTipo.put("Comprimido",TipoRemedio.TIPO_COMPRIMIDO);
            mapaTipo.put("Gota",TipoRemedio.TIPO_COMPRIMIDO);
        }

        private void inicializar() {
            setTitle("Cadastro de Remédio");
            setSize(430,370);
            setLayout(new GridLayout(1,2));
            setResizable(false);

            JPanel info = new JPanel(new GridLayout(7,1));
            JPanel painelBotoes = new JPanel();
            painelBotoes.setLayout(new FlowLayout());
            JPanel painelNome = new JPanel(new FlowLayout());
            JPanel painelCodigo = new JPanel(new FlowLayout());
            JPanel painelPreco = new JPanel(new FlowLayout());
            JPanel painelQuantidade = new JPanel(new FlowLayout());
            JPanel painelCategoria = new JPanel(new FlowLayout());
            JPanel painelTipo = new JPanel(new FlowLayout());

            JLabel nome = new JLabel("Nome do remédio: ");
            JTextField nomeField = new JTextField(30);
            painelNome.add(nome);
            painelNome.add(nomeField);

            JLabel codigo = new JLabel("Código do remédio: ");
            JTextField codigoField = new JTextField(30);
            painelCodigo.add(codigo);
            painelCodigo.add(codigoField);

            JLabel preco = new JLabel("Preço do remédio: ");
            JTextField precoField = new JTextField(30);
            precoField.setText("0");
            painelPreco.add(preco);
            painelPreco.add(precoField);

            JLabel quantidade = new JLabel("Quantidade do remédio: ");
            JTextField quantidadeField = new JTextField(30);
            quantidadeField.setText("0");
            painelQuantidade.add(quantidade);
            painelQuantidade.add(quantidadeField);

            JLabel categoria = new JLabel("Categoria do remédio: ");
            JComboBox<String> categoriaEscolha = new JComboBox<>(new String[]{"Analgésico", "Anestésicos",
                    "Anti-alérgicos", "Anti-inflamatório", "Anti-bacteriano", "Anti-microbiano", "Outros"});
            painelCategoria.add(categoria);
            painelCategoria.add(categoriaEscolha);

            JLabel tipo = new JLabel("Tipo do remédio: ");
            JComboBox<String> tipoEscolha = new JComboBox<>(new String[]{"Comprimido", "Gota",});
            painelTipo.add(tipo);
            painelTipo.add(tipoEscolha);

            cadastrar = new JButton("Cadastrar");
            cancelar = new JButton("Cancelar");

            painelBotoes.add(cadastrar);
            painelBotoes.add(cancelar);
            info.add(painelNome);
            info.add(painelCodigo);
            info.add(painelPreco);
            info.add(painelQuantidade);
            info.add(painelCategoria);
            info.add(painelTipo);
            info.add(painelBotoes,CENTER_ALIGNMENT);

            add(info);

            cadastrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(nomeField.getText().equals("") || codigoField.getText().equals("") ||
                            nomeField.getText().equals("")) {
                        JOptionPane.showMessageDialog(janelaCadastro, "Por favor preencha todos os " +
                                "campos válidos!");
                        return;
                    }
                    String nomeRemedio = nomeField.getText();
                    String codigoUser = codigoField.getText();
                    double precoUser;
                    try {
                        if(precoField.getText().isEmpty()) {
                            precoUser = 0.0;
                        } else {
                            precoUser = Double.parseDouble(precoField.getText());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(janelaCadastro, "Por favor, insira um preço válido.",
                                "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int quantidadeUser;
                    try {
                        if(quantidadeField.getText().isEmpty()) {
                            quantidadeUser = 0;
                        } else {
                            quantidadeUser = Integer.parseInt(quantidadeField.getText());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(janelaPrincipal, "Por favor, insira uma quantidade válida.",
                                "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    CategoriaRemedio categoriaSelecionada = mapaCategoria.get((String) categoriaEscolha.getSelectedItem());
                    TipoRemedio tipoSelecionado = mapaTipo.get((String) tipoEscolha.getSelectedItem());
                    Remedio remedioParaCadastrar = new Remedio(nomeRemedio,codigoUser,precoUser,quantidadeUser,
                            categoriaSelecionada,tipoSelecionado);
                    Remedio remedioCadastro = new Remedio(nomeRemedio,codigoUser,precoUser,quantidadeUser,
                                                            categoriaSelecionada,tipoSelecionado);
                    try {
                        if(sistema.cadastrarRemedio(remedioCadastro)) {
                            JOptionPane.showMessageDialog(janelaCadastro,"Remédio Cadastrado!");
                        }
                    } catch (RemedioExistenteException ex) {
                        JOptionPane.showMessageDialog(janelaCadastro,"Esse remédio já existe no sistema!");
                        return;
                    }

                }
            });
            cancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelar();
                }
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   setVisible(false);
                   janelaPrincipal.setVisible(true);
                }
            });
        }

        public void mostrar() {
            setVisible(true);
            janelaPrincipal.setVisible(false);
        }

        public void cancelar() {
            setVisible(false);
            janelaPrincipal.setVisible(true);
        }
    }

    public class FarmaciaPesquisaGUI extends JFrame {
        private FarmaciaInterface sistema;
        private JFrame janelaPrincipal;
        private JButton pesquisaPrecoButton, pesquisaNomeButton, pesquisaCodigoButton,
                        voltarButton;
        JLabel tituloPesquisa;
        boolean opcaoCorreta;
        ArrayList<Remedio> pesquisa;

        public FarmaciaPesquisaGUI(FarmaciaInterface sistema, JFrame janelaPrincipal) {
            this.sistema = sistema;
            this.janelaPrincipal = janelaPrincipal;
            inicializar();
        }

        public void mostrar() {
            setVisible(true);
            janelaPrincipal.setVisible(false);
        }

        public void inicializar() {
            setTitle("Pesquisar");
            setSize(500,150);
            setLayout(new GridLayout(2,1));
            setResizable(false);

            tituloPesquisa = new JLabel("Qual método de Pesquisa você gostaria de usar?");
            tituloPesquisa.setFont(FarmacioGUI.FONTE_TITULO);
            pesquisaNomeButton = new JButton("Pesquisar pelo Nome");
            pesquisaNomeButton.setFont(FarmacioGUI.FONTE_BOTOES);
            pesquisaCodigoButton = new JButton("Pesquisar pelo Código");
            pesquisaCodigoButton.setFont(FarmacioGUI.FONTE_BOTOES);
            voltarButton = new JButton("Voltar");
            voltarButton.setFont(FarmacioGUI.FONTE_BOTOES);

            JPanel painelTitulo = new JPanel(new FlowLayout());
            painelTitulo.add(tituloPesquisa);
            JPanel painelButtons = new JPanel(new FlowLayout());
            painelButtons.add(pesquisaNomeButton);
            painelButtons.add(pesquisaCodigoButton);
            painelButtons.add(voltarButton);
            add(painelTitulo);
            add(painelButtons);

            pesquisaCodigoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    opcaoCorreta = true;
                    while (opcaoCorreta) {
                        String codigo = JOptionPane.showInputDialog(janelaPesquisa, "Digite o código: ");
                        if(codigo.equals("")) {
                            JOptionPane.showMessageDialog(janelaPesquisa,"Insira um código!");
                        } else {
                            try {
                                pesquisa = sistema.pesquisaPeloCodigo(codigo);
                            } catch (NoSuchElementException n) {
                                JOptionPane.showMessageDialog(janelaPesquisa,
                                        "Não existem remédios cadastrados!");
                                opcaoCorreta = false;
                            }
                            if(pesquisa.isEmpty()) {
                                JOptionPane.showMessageDialog(janelaPesquisa,
                                        "Não existem remédios com esse código");
                                break;
                            } else {
                                exibirRemedios(pesquisa);
                                break;
                            }
                        }
                    }
                }
            });

            pesquisaNomeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    opcaoCorreta = true;
                    while (opcaoCorreta) {
                        String nome = JOptionPane.showInputDialog(janelaPesquisa,
                                                            "Digite o nome do Remédio: ");
                        if(nome.equals("")) {
                            JOptionPane.showMessageDialog(janelaPesquisa,"Insira um nome!");
                        } else {
                            try {
                                pesquisa = sistema.pesquisaPeloCodigo(nome);
                            } catch (NoSuchElementException n) {
                                JOptionPane.showMessageDialog(janelaPesquisa,
                                        "Não existem remédios cadastrados!");
                                opcaoCorreta = false;
                            }
                            if(pesquisa.isEmpty()) {
                                JOptionPane.showMessageDialog(janelaPesquisa,
                                        "Não existem remédios com esse nome");
                                break;
                            } else {
                                exibirRemedios(pesquisa);
                                break;
                            }
                        }
                    }
                }
            });

            voltarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    janelaPrincipal.setVisible(true);
                }
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    janelaPrincipal.setVisible(true);
                }
            });
        }

        public void exibirRemedios(ArrayList<Remedio> remedios) {
            StringBuilder formatacao = new StringBuilder();

            for(Remedio r: remedios) {
                formatacao.append("------------------------------------------------------------\n");
                formatacao.append("Nome do Remédio: ").append(r.getNome()).append("\n");
                formatacao.append("Código do Remédio: ").append(r.getCodigo()).append("\n");
                formatacao.append("Preço do Remédio: R$ ").append(r.getPreco()).append("\n");
                formatacao.append("Quantidade: ").append(r.getQuantidade()).append("\n");
                formatacao.append("Categoria do Remédio: ").append(r.getCategoria()).append("\n");
                formatacao.append("Tipo do Remédio: ").append(r.getTipo()).append("\n");
                formatacao.append("------------------------------------------------------------\n");
            }

            JTextArea areaDoTexto = new JTextArea(formatacao.toString());
            areaDoTexto.setEditable(false);

            JScrollPane areaDeRolar = new JScrollPane(areaDoTexto);
            areaDeRolar.setPreferredSize(new Dimension(400,350));

            JOptionPane.showMessageDialog(janelaPesquisa,areaDeRolar,"Lista de Remédios: ",JOptionPane.PLAIN_MESSAGE);

        }
    }
    public static void main(String[] args) {
        FarmacioGUI janela = new FarmacioGUI();
        janela.setVisible(true);
    }
}
