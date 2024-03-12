import java.util.ArrayList;

public class TabelaRecordes {
    private String nome;
    private long tempoJogo;
    private ArrayList<TabelaRecordesListener> listeners;

    public TabelaRecordes() {
        this.nome = "anonimo";
        this.tempoJogo = 9999999;
        listeners=new ArrayList<>();
    }

    public long getTempoJogo() {
        return tempoJogo;
    }

    public String getNome() {
        return nome;
    }

    public void setRecorde(String nome, long tempoJogo) {
        if (nome == null || tempoJogo < this.tempoJogo) {
            this.nome = nome;
            this.tempoJogo = tempoJogo;
            notifyRecordesActualizados();
        }
    }
    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.remove(list);
    }
    private void notifyRecordesActualizados() {
        for (TabelaRecordesListener list:listeners)
            list.recordesActualizados(this);
    }
}
