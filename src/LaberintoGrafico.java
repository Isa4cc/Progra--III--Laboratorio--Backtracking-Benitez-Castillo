import javax.swing.*;
import java.awt.*;

// ─────────────────────────────────────────────────────
// Archivo: LaberintoGrafico.java
// Clase principal. Extiende JPanel para dibujar.
// Contiene: main, variables de estado, constructor.
// ─────────────────────────────────────────────────────
public class LaberintoGrafico extends JPanel {

    // ── RETO 1 — Contadores y tiempo ──
    int  llamadas       = 0;
    int  retrocesos     = 0;
    long inicio;
    long fin;

    // ── RETO 4 — Métricas de visualización ──
    int profundidadActual = 0;
    int profundidadMaxima = 0;
    int nodosExplorados   = 0;

    // ── RETO 5 — Posición de la salida ──
    int filaSalida = -1;
    int colSalida  = -1;

    // Tamaño de cada celda en píxeles
    final int TAM = 30;

    int[][] laberinto;

    // ─────────────────────────────────────────────────
    // Constructor
    // ─────────────────────────────────────────────────
    public LaberintoGrafico() {
        laberinto = Laberintos.obtener(Configuracion.TAMANIO);
        buscarSalida();
    }

    // Localiza la celda de salida (valor 2) para la heurística
    private void buscarSalida() {
        for (int i = 0; i < laberinto.length; i++)
            for (int j = 0; j < laberinto[0].length; j++)
                if (laberinto[i][j] == 2) {
                    filaSalida = i;
                    colSalida  = j;
                }
    }

    // ─────────────────────────────────────────────────
    // MAIN — punto de entrada
    // ─────────────────────────────────────────────────
    public static void main(String[] args) {

        JFrame ventana = new JFrame("Backtracking - Laberinto");
        LaberintoGrafico panel = new LaberintoGrafico();

        ventana.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        new Thread(() -> {

            panel.inicio  = System.nanoTime();
            boolean sol   = Resolver.resolver(panel, 0, 0);
            panel.fin     = System.nanoTime();

            System.out.println("==========================================");
            System.out.println("  LABERINTO " + Configuracion.TAMANIO + "x" + Configuracion.TAMANIO);
            System.out.println("  Solucion      : " + sol);
            System.out.println("  Llamadas      : " + panel.llamadas);
            System.out.println("  Retrocesos    : " + panel.retrocesos);
            System.out.println("  Nodos         : " + panel.nodosExplorados);
            System.out.println("  Prof. maxima  : " + panel.profundidadMaxima);
            System.out.println("  Tiempo (ms)   : " + (panel.fin - panel.inicio) / 1_000_000.0);
            System.out.println("  Orden         : " + Configuracion.ORDEN);
            System.out.println("  Heuristica    : " + Configuracion.HEURISTICA);
            System.out.println("==========================================");

            panel.repaint();

        }).start();
    }

    // ─────────────────────────────────────────────────
    // RETO 4 — Dibuja el tablero y el panel de métricas
    // ─────────────────────────────────────────────────
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int fila = 0; fila < laberinto.length; fila++) {
            for (int col = 0; col < laberinto[0].length; col++) {

                switch (laberinto[fila][col]) {
                    case 0:  g.setColor(Color.WHITE); break; // libre
                    case 1:  g.setColor(Color.BLACK); break; // pared
                    case 2:  g.setColor(Color.GREEN); break; // salida
                    case 9:  g.setColor(Color.BLUE);  break; // camino actual
                    case 5:  g.setColor(Color.RED);   break; // retroceso
                    default: g.setColor(Color.WHITE); break;
                }

                g.fillRect(col * TAM, fila * TAM, TAM, TAM);
                g.setColor(Color.GRAY);
                g.drawRect(col * TAM, fila * TAM, TAM, TAM);
            }
        }

        // Panel de métricas debajo del tablero
        int baseY = laberinto.length * TAM;

        g.setColor(new Color(30, 30, 30));
        g.fillRect(0, baseY, getWidth(), 90);

        g.setFont(new Font("Monospaced", Font.BOLD, 13));

        g.setColor(new Color(100, 200, 255));
        g.drawString("Llamadas: " + llamadas, 10, baseY + 20);

        g.setColor(new Color(255, 120, 120));
        g.drawString("Retrocesos: " + retrocesos, 220, baseY + 20);

        g.setColor(new Color(120, 255, 120));
        g.drawString("Nodos: " + nodosExplorados, 10, baseY + 42);

        g.setColor(new Color(255, 220, 80));
        g.drawString("Prof. actual: " + profundidadActual, 220, baseY + 42);

        g.setColor(new Color(200, 150, 255));
        g.drawString("Prof. max: " + profundidadMaxima, 10, baseY + 64);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 11));
        g.drawString("Orden: " + Configuracion.ORDEN + "  Heuristica: " + Configuracion.HEURISTICA,
                10, baseY + 82);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                laberinto[0].length * TAM,
                laberinto.length    * TAM + 90
        );
    }

    // Pausa para animación
    public void dormir() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}