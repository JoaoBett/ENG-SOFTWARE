import javax.swing.*;
import java.awt.*;

public class BotaoCampoMinado extends JButton {
    private int estado;

    public BotaoCampoMinado() {
        this.estado = CampoMinado.MARCADO;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        switch (estado) {
            case CampoMinado.VAZIO:
                setText("");
                setBackground(Color.DARK_GRAY);
                break;
            case CampoMinado.TAPADO:
                setText("");
                setBackground(null);
                break;
            case CampoMinado.REBENTADO:
                setText("!");
                setBackground(Color.RED);
                break;
            case CampoMinado.MARCADO:
                setText("1");
                setBackground(Color.DARK_GRAY);
                break;
            case CampoMinado.DUVIDA:
                setText("?");
                setBackground(Color.YELLOW);
                break;
            default:
                setText(String.valueOf(estado));
                setBackground(Color.LIGHT_GRAY);
        }
    }
}
