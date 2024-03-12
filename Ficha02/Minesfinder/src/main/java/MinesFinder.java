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
        pack();
        btnFacil.addActionListener(this::btnJogoFacilActionPerformed);
        btnMedio.addActionListener(this::btnJogoMedioActionPerformed);
        btnDificil.addActionListener(this::btnJogoDificilActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);
    }
    private void btnSairActionPerformed(ActionEvent e){
        System.exit(0);
    }
    private void btnJogoFacilActionPerformed(ActionEvent e){
        var janela = new JanelaDeJogo(new CampoMinado(9, 9, 10));
        janela.setVisible(true);
    }
    private void btnJogoMedioActionPerformed(ActionEvent e){
        var janela = new JanelaDeJogo(new CampoMinado(16,16,40));
        janela.setVisible(true);
    }
    private void btnJogoDificilActionPerformed(ActionEvent e){
        var janela = new JanelaDeJogo(new CampoMinado(16,30,90));
        janela.setVisible(true);
    }
    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}