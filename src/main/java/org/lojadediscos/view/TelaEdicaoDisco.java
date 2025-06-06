package org.lojadediscos.view;

import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Disco;

import javax.swing.*;
import java.awt.*;

/**
 * Classe para a tela quando apertarmos o botão "Editar" na nossa tela de cadastro de disco.
 */


public class TelaEdicaoDisco extends JDialog {

    private final JTextField txtTitulo;
    private final JTextField txtArtista;
    private final JTextField txtGenero;
    private final JTextField txtPreco;
    private final JTextField txtEstoque;

    public TelaEdicaoDisco(JFrame parent, Disco disco) {
        super(parent, "Editar Disco", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Campos
        txtTitulo = new JTextField(disco.getTitulo());
        txtArtista = new JTextField(disco.getArtista());
        txtGenero = new JTextField(disco.getGenero());
        txtPreco = new JTextField(String.valueOf(disco.getPreco()));
        txtEstoque = new JTextField(String.valueOf(disco.getEstoque()));

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(6, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelCampos.add(new JLabel("Título:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Artista:"));
        painelCampos.add(txtArtista);
        painelCampos.add(new JLabel("Gênero:"));
        painelCampos.add(txtGenero);
        painelCampos.add(new JLabel("Preço:"));
        painelCampos.add(txtPreco);
        painelCampos.add(new JLabel("Estoque:"));
        painelCampos.add(txtEstoque);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                disco.setTitulo(txtTitulo.getText());
                disco.setArtista(txtArtista.getText());
                disco.setGenero(txtGenero.getText());
                disco.setPreco(Double.parseDouble(txtPreco.getText()));
                disco.setEstoque(Integer.parseInt(txtEstoque.getText()));

                new DiscoDAO().atualizar(disco);
                JOptionPane.showMessageDialog(this, "Disco atualizado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Preço ou estoque inválidos.");
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}
