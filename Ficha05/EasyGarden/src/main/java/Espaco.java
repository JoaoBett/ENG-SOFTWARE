import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Espaco {
    private final JFrame mainFrame;
    private JDialog addSpaceDialog;
    private JTextField addressField;
    private JTextField areaField;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> responsibleComboBox;
    private final List<EspacoData> espacoList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Espaco::new);
    }

    public Espaco() {
        mainFrame = new JFrame("Espaço");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);

        JButton addButton = new JButton("Adicionar Espaço");
        addButton.addActionListener(e -> showAddSpaceDialog());

        mainFrame.add(addButton, BorderLayout.CENTER);
        mainFrame.setVisible(true);

        espacoList = new ArrayList<>();
    }

    private void showAddSpaceDialog() {
        addSpaceDialog = new JDialog(mainFrame, "Registar Espaço", true);
        addSpaceDialog.setSize(300, 250);
        addSpaceDialog.setLayout(new GridLayout(6, 2));

        addSpaceDialog.add(new JLabel("Endereço:"));
        addressField = new JTextField();
        addSpaceDialog.add(addressField);

        addSpaceDialog.add(new JLabel("Área:"));
        areaField = new JTextField();
        addSpaceDialog.add(areaField);

        addSpaceDialog.add(new JLabel("Tipo:"));
        String[] types = {"Relvado", "Canteiros", "Misto"};
        typeComboBox = new JComboBox<>(types);
        addSpaceDialog.add(typeComboBox);

        addSpaceDialog.add(new JLabel("Responsável:"));
        String[] responsibles = {"Responsável 1", "Responsável 2", "Responsável 3"};
        responsibleComboBox = new JComboBox<>(responsibles);
        addSpaceDialog.add(responsibleComboBox);

        JButton acceptButton = new JButton("Aceitar");
        acceptButton.addActionListener(new AcceptButtonListener());
        addSpaceDialog.add(acceptButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> addSpaceDialog.dispose());
        addSpaceDialog.add(cancelButton);

        addSpaceDialog.setVisible(true);
    }

    private class AcceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String address = addressField.getText();
            String area = areaField.getText();
            String type = (String) typeComboBox.getSelectedItem();
            String responsible = (String) responsibleComboBox.getSelectedItem();

            if (address.isEmpty() || area.isEmpty() || type == null || responsible == null) {
                JOptionPane.showMessageDialog(addSpaceDialog, "Dados incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Adiciona os dados a uma lista de espaços
                EspacoData espacoData = new EspacoData(address, area, type, responsible);
                espacoList.add(espacoData);

                // Aqui poderias adicionar o código para guardar os dados numa lista ou base de dados
                JOptionPane.showMessageDialog(addSpaceDialog, "Espaço adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                addSpaceDialog.dispose();
            }
        }
    }

    // Classe para representar os dados de um espaço
    private static class EspacoData {
        private final String address;
        private final String area;
        private final String type;
        private final String responsible;

        public EspacoData(String address, String area, String type, String responsible) {
            this.address = address;
            this.area = area;
            this.type = type;
            this.responsible = responsible;
        }

        // Métodos para acessar os dados
        public String getAddress() {
            return address;
        }

        public String getArea() {
            return area;
        }

        public String getType() {
            return type;
        }

        public String getResponsible() {
            return responsible;
        }
    }
}
