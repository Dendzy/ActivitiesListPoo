package br.com.yagocode.GUI;

import br.com.yagocode.CategoriaRemedio;
import br.com.yagocode.FarmaciaInterface;
import br.com.yagocode.Remedio;
import br.com.yagocode.TipoRemedio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FarmaciaCadastro extends JFrame implements ActionListener {
    private FarmaciaInterface sistema;
    private JFrame janelaPrincipal;
    private static final Map<String, CategoriaRemedio> mapaCategoria = new HashMap<>();
    private static final Map<String, TipoRemedio> mapaTipo = new HashMap<>();
    JButton cadastrar,cancelar;

    public FarmaciaCadastro(FarmaciaInterface sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        setTitle("Cadastro de Remédio");
        setSize(400,300);
        setVisible(true);
        janelaPrincipal.setVisible(false);
        setLayout(new GridLayout(1,2));

        JPanel info = new JPanel(new GridLayout(7,2));
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        JLabel nome = new JLabel("Nome do remédio: ");
        JTextField nomeField = new JTextField();

        JLabel codigo = new JLabel("Código do remédio: ");
        JTextField codigoField = new JTextField();

        JLabel preco = new JLabel("Preço do remédio: ");
        JTextField precoField = new JTextField();
        precoField.setText("0");

        JLabel quantidade = new JLabel("Quantidade do remédio: ");
        JTextField quantidadeField = new JTextField();
        quantidadeField.setText("0");

        JLabel categoria = new JLabel("Categoria do remédio: ");
        JComboBox<String> categoriaEscolha = new JComboBox<>(new String[]{"Analgésico", "Anestésicos",
                "Anti-alérgicos", "Anti-inflamatório", "Anti-bacteriano", "Anti-microbiano", "Outros"});

        JLabel tipo = new JLabel("Tipo do remédio: ");
        JComboBox<String> tipoEscolha = new JComboBox<>(new String[]{"Comprimido", "Gota",});

        cadastrar = new JButton("Cadastrar");

        painelBotoes.add(cadastrar);
        info.add(nome);
        info.add(nomeField);
        info.add(codigo);
        info.add(codigoField);
        info.add(preco);
        info.add(precoField);
        info.add(quantidade);
        info.add(quantidadeField);
        info.add(categoria);
        info.add(categoriaEscolha);
        info.add(tipo);
        info.add(tipoEscolha);
        info.add(painelBotoes);

        add(info);

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    JOptionPane.showMessageDialog(janelaPrincipal, "Por favor, insira um preço válido.",
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

            }
        });
    }

}
