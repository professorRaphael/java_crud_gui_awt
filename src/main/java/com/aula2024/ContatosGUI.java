package com.aula2024;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.List;

public class ContatosGUI extends JFrame {
    private JTable contatosTable;
    private JButton adicionarButton, editarButton, excluirButton;

    public static void iniciarGUI() {
        SwingUtilities.invokeLater(() -> {
            new ContatosGUI().setVisible(true);
        });
    }

    public ContatosGUI() {
        setTitle("Gerenciador de Contatos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Criar a tabela de contatos
        contatosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(contatosTable);

        // Criar os botões
        adicionarButton = new JButton("Adicionar");
        editarButton = new JButton("Editar");
        excluirButton = new JButton("Excluir");

        // Criar o painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(excluirButton);

        //  Adicionar os listeners aos botões
        adicionarButton.addActionListener(e -> adicionarContato());
        editarButton.addActionListener(e -> editarContato());
        excluirButton.addActionListener(e -> excluirContato());

        // Adicionar os componentes ao painel principal
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Carregar os contatos na tabela
        atualizarTabelaContatos();
    }

    private void atualizarTabelaContatos() {
        List<Contato> contatos = ContatoDAO.listarContatos();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Telefone"}, 0);
        for (Contato contato : contatos) {
            tableModel.addRow(new Object[]{contato.getId(), contato.getNome(), contato.getTelefone()});
        }
        contatosTable.setModel(tableModel);
    }

    private void adicionarContato() {
        JTextField nomeField = new JTextField();
        JTextField telefoneField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Contato",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String telefone = telefoneField.getText();

            if (!nome.isEmpty() && !telefone.isEmpty()) {
                Contato novoContato = new Contato();
                novoContato.setNome(nome);
                novoContato.setTelefone(telefone);
                ContatoDAO.adicionarContato(novoContato);
                atualizarTabelaContatos();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        }
    }


    private void editarContato() {
        int selectedRow = contatosTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) contatosTable.getValueAt(selectedRow, 0);
            String nome = JOptionPane.showInputDialog("Digite o novo nome do contato:");
            String telefone = JOptionPane.showInputDialog("Digite o novo telefone do contato:");

            if (nome != null && telefone != null) {
                Contato contato = new Contato();
                contato.setId(id);
                contato.setNome(nome);
                contato.setTelefone(telefone);
                ContatoDAO.atualizarContato(contato);
                atualizarTabelaContatos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para editar.");
        }
    }

    private void excluirContato() {
        int selectedRow = contatosTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) contatosTable.getValueAt(selectedRow, 0);
            ContatoDAO.excluirContato(id);
            atualizarTabelaContatos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para excluir.");
        }
    }


    public static void main(String[] args) {
        ContatoDAO.criarTabela();
        SwingUtilities.invokeLater(() -> {
            new ContatosGUI().setVisible(true);
        });
    }
}
