import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton btnFacil;
    private JButton btnMedio;
    private JButton btnDificil;
    private JButton btnSair;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);

        // Causes this Window to be sized to fit the preferred size and layouts
        //of its subcomponents.
        pack();

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnFacil.addActionListener(this::btnFacilActionPerformed);
        btnMedio.addActionListener(this::btnMedioActionPerformed);
        btnDificil.addActionListener(this::btnDificilActionPerformed);

    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnFacilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9, 10));
        janela.setVisible(true);
    }

    private void btnMedioActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16, 40));
        janela.setVisible(true);
    }


    private void btnDificilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,30, 90));
        janela.setVisible(true);
    }


    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
