import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton btnFacil;
    private JButton btnMedio;
    private JButton btnDificil;
    private JButton btnSair;
    private JLabel lblDificil;
    private JLabel lblFacil;
    private JLabel lblMedio;
    private JLabel lblNome;

    private  JLabel lblTempoFacil;
    private  JLabel lblTempoMedio;
    private  JLabel lblTempoDificil;


    TabelaRecordes recordesFacil = new TabelaRecordes();
    TabelaRecordes recordesMedio = new TabelaRecordes();
    TabelaRecordes recordesDificil = new TabelaRecordes();

    private void recordesFacilActualizado(TabelaRecordes recordes) {
        lblNome.setText(recordes.getNome());
        lblFacil.setText(Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesMedioActualizado(TabelaRecordes recordes) {
        lblNome.setText(recordes.getNome());
        lblMedio.setText(Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesDificilActualizado(TabelaRecordes recordes) {
        lblNome.setText(recordes.getNome());
        lblDificil.setText(Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }


    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        btnFacil.addActionListener(this::btnJogoFacilActionPerformed);
        btnMedio.addActionListener(this::btnJogoMedioActionPerformed);
        btnDificil.addActionListener(this::btnJogoDificilActionPerformed);
        btnSair.addActionListener(this::btnSairActionPerformed);
        lerRecordesDoDisco();

        lblFacil.setText(recordesFacil.getNome());
        lblTempoFacil.setText(Long.toString(recordesFacil.getTempoJogo()/1000));
        lblMedio.setText(recordesMedio.getNome());
        lblTempoMedio.setText(Long.toString(recordesMedio.getTempoJogo()/1000));
        lblDificil.setText(recordesDificil.getNome());
        lblTempoDificil.setText(Long.toString(recordesDificil.getTempoJogo()/1000));


        recordesFacil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesFacilActualizado(recordes);
            }
        });
        recordesMedio.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesMedioActualizado(recordes);
            }
        });
        recordesDificil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesDificilActualizado(recordes);
            }
        });
    }


    private void btnSairActionPerformed(ActionEvent e){
        System.exit(0);
    }
    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9, 9, 10), recordesFacil);
        janela.setVisible(true);
    }

    private void btnJogoMedioActionPerformed(ActionEvent e){
        var janela = new JanelaDeJogo(new CampoMinado(16,16,40), recordesMedio);
        janela.setVisible(true);
    }
    private void btnJogoDificilActionPerformed(ActionEvent e){
        var janela = new JanelaDeJogo(new CampoMinado(16,30,90), recordesDificil);
        janela.setVisible(true);
    }


    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }


    private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordesFacil=(TabelaRecordes) ois.readObject();
                recordesMedio=(TabelaRecordes) ois.readObject();
                recordesDificil=(TabelaRecordes) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new
                    File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
