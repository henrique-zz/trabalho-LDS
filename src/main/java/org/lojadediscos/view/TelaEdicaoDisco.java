package org.lojadediscos.view;

import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Disco;
import org.lojadediscos.util.I18n;

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
        // título traduzido
        super(parent, I18n.getString("window.title.editDisc"), true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        txtTitulo = new JTextField(disco.getTitulo());
        txtArtista = new JTextField(disco.getArtista());
        txtGenero = new JTextField(disco.getGenero());
        txtPreco = new JTextField(String.valueOf(disco.getPreco()));
        txtEstoque = new JTextField(String.valueOf(disco.getEstoque()));

        JPanel painelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // labels traduzidas
        painelCampos.add(new JLabel(I18n.getString("label.title")));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel(I18n.getString("label.artist")));
        painelCampos.add(txtArtista);
        painelCampos.add(new JLabel(I18n.getString("label.genre")));
        painelCampos.add(txtGenero);
        painelCampos.add(new JLabel(I18n.getString("label.price")));
        painelCampos.add(txtPreco);
        painelCampos.add(new JLabel(I18n.getString("label.stock")));
        painelCampos.add(txtEstoque);

        // botões traduzidos
        JButton btnSalvar = new JButton(I18n.getString("button.save"));
        btnSalvar.addActionListener(e -> {
            try {
                disco.setTitulo(txtTitulo.getText());
                disco.setArtista(txtArtista.getText());
                disco.setGenero(txtGenero.getText());
                disco.setPreco(Double.parseDouble(txtPreco.getText()));
                disco.setEstoque(Integer.parseInt(txtEstoque.getText()));

                new DiscoDAO().atualizar(disco);
                // mensagem traduzida
                JOptionPane.showMessageDialog(this, I18n.getString("message.success.discUpdated"));
                dispose();
            } catch (NumberFormatException ex) {
                // mensagem traduzida
                JOptionPane.showMessageDialog(this, I18n.getString("message.error.invalidPriceOrStock"));
            }
        });

        JButton btnCancelar = new JButton(I18n.getString("button.cancel"));
        btnCancelar.addActionListener(e -> dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        }
    }
