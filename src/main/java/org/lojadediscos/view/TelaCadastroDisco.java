package org.lojadediscos.view;

import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Disco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaCadastroDisco extends TelaCadastroBase {

    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtArtista = new JTextField(20);
    private JTextField txtGenero = new JTextField(20);
    private JTextField txtPreco = new JTextField(10);
    private JTextField txtEstoque = new JTextField(5);

    public TelaCadastroDisco() {
        super("Cadastro de Disco");

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 80, 25);
        txtTitulo.setBounds(100, 20, 200, 25);

        JLabel lblArtista = new JLabel("Artista:");
        lblArtista.setBounds(20, 60, 80, 25);
        txtArtista.setBounds(100, 60, 200, 25);

        JLabel lblGenero = new JLabel("Gênero:");
        lblGenero.setBounds(20, 100, 80, 25);
        txtGenero.setBounds(100, 100, 200, 25);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 140, 80, 25);
        txtPreco.setBounds(100, 140, 100, 25);

        JLabel lblEstoque = new JLabel("Estoque:");
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

        getRootPane().setDefaultButton(btnSalvar); // permite que seja acionado quando apertarmos "ENTER"

        btnCadastroDiscos.setVisible(false);
        // não permitir o botão que volta para esta mesma tela aparecer
        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnExcluir.addActionListener(this::excluir);

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
        DiscoDAO dao = new DiscoDAO();
        dao.salvar(disco);
        JOptionPane.showMessageDialog(this, "Disco salvo com sucesso!");
    }

    @Override
    protected void listar(ActionEvent e) {
        DiscoDAO dao = new DiscoDAO();
        List<Disco> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum disco cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Disco disco : lista) {
                sb.append("ID: ").append(disco.getId())
                        .append(" | Título: ").append(disco.getTitulo())
                        .append(" | Artista: ").append(disco.getArtista())
                        .append(" | Gênero: ").append(disco.getGenero())
                        .append(" | Preço: ").append(disco.getPreco())
                        .append(" | Estoque: ").append(disco.getEstoque())
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
            JOptionPane.showMessageDialog(this, "Nenhum disco cadastrado.");
            return;
        }

        JComboBox<Disco> cbDiscos = new JComboBox<>(discos.toArray(new Disco[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbDiscos,
                "Selecione o disco para excluir",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Disco selecionado = (Disco) cbDiscos.getSelectedItem();
            if (selecionado != null) {
                boolean sucesso = dao.excluir(selecionado.getId());
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Disco excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o disco.");
                }
            }
        }
    }

}
