import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JanelaDeJogo extends JFrame {
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo

    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;


    //Design, em "field name"
    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;
        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();
        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];
        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));
        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                botoes[linha][coluna] = new BotaoCampoMinado();
                painelJogo.add(botoes[linha][coluna]);
            }
        }
        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts
        // of its subcomponents.
        pack();
        setVisible(true);
    }


}