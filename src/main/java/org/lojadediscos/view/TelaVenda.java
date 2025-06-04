package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.dao.VendaDAO;
import org.lojadediscos.model.Cliente;
import org.lojadediscos.model.Disco;
import org.lojadediscos.model.ItemVenda;
import org.lojadediscos.model.Venda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class TelaVenda extends JFrame {

    private JComboBox<Cliente> cbClientes = new JComboBox<>();
    private JComboBox<Disco> cbDiscos = new JComboBox<>();
    private JTextField txtQuantidade = new JTextField(5);
    private JTextArea txtCarrinho = new JTextArea(10, 30);
    private JButton btnAdicionar = new JButton("Adicionar");
    private JButton btnFinalizar = new JButton("Finalizar Venda");
    private JButton btnVoltar = new JButton("Voltar");

    private List<ItemVenda> carrinho = new ArrayList<>();

    public TelaVenda() {
        setTitle("Realizar Venda");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 20, 80, 25);
        cbClientes.setBounds(100, 20, 250, 25);

        JLabel lblDisco = new JLabel("Disco:");
        lblDisco.setBounds(20, 60, 80, 25);
        cbDiscos.setBounds(100, 60, 250, 25);

        JLabel lblQtd = new JLabel("Qtd:");
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
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarTodos();
        for (Cliente cliente : clientes) {
            cbClientes.addItem(cliente);
        }
    }

    private void carregarDiscos() {
        DiscoDAO discoDAO = new DiscoDAO();
        List<Disco> discos = discoDAO.listarTodos();
        for (Disco disco : discos) {
            cbDiscos.addItem(disco);
        }
    }

    private void adicionarAoCarrinho(ActionEvent e) {
        Disco disco = (Disco) cbDiscos.getSelectedItem();
        int quantidade = Integer.parseInt(txtQuantidade.getText());

        if (disco.getEstoque() < quantidade) {
            JOptionPane.showMessageDialog(this, "Estoque insuficiente.");
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
            JOptionPane.showMessageDialog(this, "Carrinho vazio.");
            return;
        }

        Cliente cliente = (Cliente) cbClientes.getSelectedItem();
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setData(new Date());

        for (ItemVenda item : carrinho) {
            item.setVenda(venda);

            Disco disco = item.getDisco();
            disco.setEstoque(disco.getEstoque() - item.getQuantidade());

            DiscoDAO discoDAO = new DiscoDAO();
            discoDAO.atualizar(disco);
        }

        venda.setItens(carrinho);

        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.salvar(venda);

        JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!");
        dispose();
    }

    private void voltar(ActionEvent e){
        new TelaCadastroDisco();
        dispose();
    }
}
