package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.dao.VendaDAO;
import org.lojadediscos.model.Cliente;
import org.lojadediscos.model.Disco;
import org.lojadediscos.model.ItemVenda;
import org.lojadediscos.model.Venda;
import org.lojadediscos.util.I18n;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * Classe para a tela quando apertarmos o botão "Realizar Venda", contendo a lógica para esta ser realizada corretamente,
 * como a atualização dos estoques na nossa tabela do Disco após a compra.
 */


public class TelaVenda extends JFrame {

    private JComboBox<Cliente> cbClientes = new JComboBox<>();
    private JComboBox<Disco> cbDiscos = new JComboBox<>();
    private JTextField txtQuantidade = new JTextField(5);
    private JTextArea txtCarrinho = new JTextArea(10, 30);
    // botões traduzidos
    private JButton btnAdicionar = new JButton(I18n.getString("button.add"));
    private JButton btnFinalizar = new JButton(I18n.getString("button.finalize"));
    private JButton btnVoltar = new JButton(I18n.getString("button.back"));

    private List<ItemVenda> carrinho = new ArrayList<>();

    public TelaVenda() {
        // título traduzido
        setTitle(I18n.getString("window.title.sell"));
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // labels traduzidas
        JLabel lblCliente = new JLabel(I18n.getString("label.client"));
        lblCliente.setBounds(20, 20, 80, 25);
        cbClientes.setBounds(100, 20, 250, 25);

        JLabel lblDisco = new JLabel(I18n.getString("label.disc"));
        lblDisco.setBounds(20, 60, 80, 25);
        cbDiscos.setBounds(100, 60, 250, 25);

        JLabel lblQtd = new JLabel(I18n.getString("label.quantity"));
        lblQtd.setBounds(20, 100, 80, 25);
        txtQuantidade.setBounds(100, 100, 50, 25);

        btnAdicionar.setBounds(170, 100, 120, 25);
        JScrollPane scroll = new JScrollPane(txtCarrinho);
        scroll.setBounds(20, 140, 440, 200);

        btnFinalizar.setBounds(150, 360, 180, 40);
        btnVoltar.setBounds(150, 410, 180, 40);

        add(lblCliente);
        add(cbClientes);
        add(lblDisco);
        add(cbDiscos);
        add(lblQtd);
        add(txtQuantidade);
        add(btnAdicionar);
        add(scroll);
        add(btnFinalizar);
        add(btnVoltar);

        carregarClientes();
        carregarDiscos();

        btnAdicionar.addActionListener(this::adicionarAoCarrinho);
        btnFinalizar.addActionListener(this::finalizarVenda);
        btnVoltar.addActionListener(this::voltar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void carregarClientes() {
        List<Cliente> clientes = new ClienteDAO().listarTodos();
        for (Cliente cliente : clientes) {
            cbClientes.addItem(cliente);
        }
    }

    private void carregarDiscos() {
        List<Disco> discos = new DiscoDAO().listarTodos();
        for (Disco disco : discos) {
            cbDiscos.addItem(disco);
        }
    }

    private void adicionarAoCarrinho(ActionEvent e) {
        Disco disco = (Disco) cbDiscos.getSelectedItem();
        int quantidade = Integer.parseInt(txtQuantidade.getText());

        if (disco.getEstoque() < quantidade) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.error.insufficientStock"));
            return;
        }

        ItemVenda item = new ItemVenda();
        item.setDisco(disco);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(disco.getPreco());
        carrinho.add(item);

        txtCarrinho.append(disco.getTitulo() + " x" + quantidade + "\n");
    }

    private void finalizarVenda(ActionEvent e) {
        if (carrinho.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.emptyCart"));
            return;
        }

        Venda venda = new Venda();
        venda.setCliente((Cliente) cbClientes.getSelectedItem());
        venda.setData(new Date());

        for (ItemVenda item : carrinho) {
            item.setVenda(venda);
            Disco disco = item.getDisco();
            disco.setEstoque(disco.getEstoque() - item.getQuantidade());
            new DiscoDAO().atualizar(disco);
        }

        venda.setItens(carrinho);
        new VendaDAO().salvar(venda);

        // mensagem traduzida
        JOptionPane.showMessageDialog(this, I18n.getString("message.success.saleCompleted"));
        new TelaCadastroDisco();
        dispose();
    }

    private void voltar(ActionEvent e){
        new TelaCadastroDisco();
        dispose();
    }
}
