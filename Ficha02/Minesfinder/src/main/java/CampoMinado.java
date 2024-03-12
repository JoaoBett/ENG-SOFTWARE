import java.util.Random;

public class CampoMinado {
    private boolean[][] minas;
    public static final int VAZIO = 0;
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;
    private int[][] estado;
    private int nrLinhas; // ou largura
    private int nrColunas; // ou altura
    private int nrMinas;
    private boolean primeiraJogada;
    private boolean jogoTerminado;
    private boolean jogadorDerrotado;
    private long instanteInicioJogo;
    private long duracaoJogo;

    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;
        this.minas = new boolean[nrLinhas][nrColunas];// valores comecam a false
        this.estado = new int[nrLinhas][nrColunas]; // valores comecam a 0
        for (var x = 0; x < nrLinhas; ++x) {
            for (var y = 0; y < nrColunas; ++y) {
                estado[x][y] = TAPADO;
            }
        }
        this.primeiraJogada = true;
        this.jogadorDerrotado = false;
        this.jogoTerminado = false;
    }

    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public boolean isJogadorDerrotado() {
        return jogadorDerrotado;
    }

    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    public int getNrLinhas() {
        return nrLinhas;
    }

    public int getNrColunas() {
        return nrColunas;
    }

    public void revelarQuadricula(int x, int y) {
        if (this.jogoTerminado || estado[x][y] < TAPADO) {
            return;
        }
        if (primeiraJogada) {
            instanteInicioJogo = System.currentTimeMillis();
            primeiraJogada = false;
            colocarMinas(x, y);
        } else {
            if (hasMina(x, y)) {
                estado[x][y] = REBENTADO;
                jogadorDerrotado = true;
                jogoTerminado = true;
                duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
                return;
            }
        }
        if ((estado[x][y] = contarMinasVizinhas(x, y)) == 0) {
            revelarQuadriculasVizinhas(x, y);
        }
        if (isVitoria()) {
            jogoTerminado = true;
            duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
        }
    }

    private void revelarQuadriculasVizinhas(int x, int y) {
        for (var i = Math.max(0, x - 1); i < Math.min(nrLinhas, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(nrColunas, y + 2); ++j) {
                revelarQuadricula(i, j);
            }
        }
    }

    private void colocarMinas(int exceptoX, int exceptoY) {
        var aleatorio = new Random();
        var x = 0;
        var y = 0;
        for (var i = 0; i < nrMinas; ++i) {
            do {
                x = aleatorio.nextInt(nrLinhas);
                y = aleatorio.nextInt(nrColunas);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }

    private int contarMinasVizinhas(int x, int y) {
        var numMinasVizinhas = 0;
        for (var i = Math.max(0, x - 1); i < Math.min(nrLinhas, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(nrColunas, y + 2); ++j) {
                if (minas[i][j]) {
                    ++numMinasVizinhas;
                }
            }
        }
        return numMinasVizinhas;
    }

    private boolean isVitoria() {
        for (var x = 0; x < nrLinhas; ++x) {
            for (var y = 0; y < nrColunas; ++y) {
                if (estado[x][y] == TAPADO && !minas[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void marcarComoTendoMina(int x, int y) {
        if (estado[x][y] == TAPADO || estado[x][y] == DUVIDA) {
            estado[x][y] = MARCADO;
        }
    }

    public void marcarComoSuspeita(int x, int y) {
        if (estado[x][y] == TAPADO || estado[x][y] == MARCADO) {
            estado[x][y] = DUVIDA;
        }
    }

    public void desmarcarQuadricula(int x, int y) {
        if (estado[x][y] == MARCADO || estado[x][y] == DUVIDA) {
            estado[x][y] = TAPADO;
        }
    }

    public long getDuracaoJogo() {
        if (primeiraJogada) {
            return 0;
        }
        if(!jogoTerminado)
            return System.currentTimeMillis() - instanteInicioJogo;
        else
            return duracaoJogo;
    }
}
