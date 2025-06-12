package org.lojadediscos.view;

import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Disco;
import org.lojadediscos.util.I18n;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Classe para o cadastro de discos no nosso banco de dados, usando a TelaCadastroBase como pai, além de implementações
 * que diferem dos atributos prsentes nesta.
 */

public class TelaCadastroDisco extends TelaCadastroBase {

    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtArtista = new JTextField(20);
    private JTextField txtGenero = new JTextField(20);
    private JTextField txtPreco = new JTextField(10);
    private JTextField txtEstoque = new JTextField(5);

    public TelaCadastroDisco() {
        super(); // chama o construtor pai sem argumentos
        setTitle(I18n.getString("window.title.discRegistration")); // I18N: Define o título aqui

        // labels traduzidas
        JLabel lblTitulo = new JLabel(I18n.getString("label.title"));
        lblTitulo.setBounds(20, 20, 80, 25);
        txtTitulo.setBounds(100, 20, 200, 25);

        JLabel lblArtista = new JLabel(I18n.getString("label.artist"));
        lblArtista.setBounds(20, 60, 80, 25);
        txtArtista.setBounds(100, 60, 200, 25);

        JLabel lblGenero = new JLabel(I18n.getString("label.genre"));
        lblGenero.setBounds(20, 100, 80, 25);
        txtGenero.setBounds(100, 100, 200, 25);

        JLabel lblPreco = new JLabel(I18n.getString("label.price"));
        lblPreco.setBounds(20, 140, 80, 25);
        txtPreco.setBounds(100, 140, 100, 25);

        JLabel lblEstoque = new JLabel(I18n.getString("label.stock"));
        lblEstoque.setBounds(20, 180, 80, 25);
        txtEstoque.setBounds(100, 180, 100, 25);

        add(lblTitulo);
        add(txtTitulo);
        add(lblArtista);
        add(txtArtista);
        add(lblGenero);
        add(txtGenero);
        add(lblPreco);
        add(txtPreco);
        add(lblEstoque);
        add(txtEstoque);

        getRootPane().setDefaultButton(btnSalvar);
        btnCadastroDiscos.setVisible(false);
        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnExcluir.addActionListener(this::excluir);
        btnEditar.addActionListener(this::editar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    protected void salvar(ActionEvent e) {
        Disco disco = new Disco(
                txtTitulo.getText(),
                txtArtista.getText(),
                txtGenero.getText(),
                Double.parseDouble(txtPreco.getText()),
                Integer.parseInt(txtEstoque.getText())
        );
        new DiscoDAO().salvar(disco);
        // mensagem traduzida
        JOptionPane.showMessageDialog(this, I18n.getString("message.success.discSaved"));
    }

    @Override
    protected void listar(ActionEvent e) {
        DiscoDAO dao = new DiscoDAO();
        List<Disco> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noDiscsRegistered"));
        } else {
            StringBuilder sb = new StringBuilder();
            for (Disco disco : lista) {
                // labels da lista e formato da moeda traduzidos
                sb.append("ID: ").append(disco.getId())
                        .append(" | ").append(I18n.getString("label.title")).append(" ").append(disco.getTitulo())
                        .append(" | ").append(I18n.getString("label.artist")).append(" ").append(disco.getArtista())
                        .append(" | ").append(I18n.getString("label.price")).append(" ").append(I18n.formatCurrency(disco.getPreco()))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    @Override
    protected void excluir(ActionEvent e) {
        DiscoDAO dao = new DiscoDAO();
        List<Disco> discos = dao.listarTodos();

        if (discos.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noDiscsRegistered"));
            return;
        }

        JComboBox<Disco> cbDiscos = new JComboBox<>(discos.toArray(new Disco[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbDiscos,
                // título do diálogo traduzido
                I18n.getString("dialog.title.selectDiscToDelete"),
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Disco selecionado = (Disco) cbDiscos.getSelectedItem();
            if (selecionado != null) {
                boolean sucesso = dao.excluir(selecionado.getId());
                if (sucesso) {
                    // mensagens traduzidas
                    JOptionPane.showMessageDialog(this, I18n.getString("message.success.discDeleted"));
                } else {
                    JOptionPane.showMessageDialog(this, I18n.getString("message.error.deleteDisc"));
                }
            }
        }
    }

    @Override
    protected void editar(ActionEvent e) {
        DiscoDAO dao = new DiscoDAO();
        List<Disco> discos = dao.listarTodos();

        if (discos.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noDiscsRegistered"));
            return;
        }

        JComboBox<Disco> cbDiscos = new JComboBox<>(discos.toArray(new Disco[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbDiscos,
                // título do diálogo traduzido
                I18n.getString("dialog.title.selectDiscToEdit"),
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Disco selecionado = (Disco) cbDiscos.getSelectedItem();
            if (selecionado != null) {
                new TelaEdicaoDisco(this, selecionado).setVisible(true);
            }
        }
    }
}
