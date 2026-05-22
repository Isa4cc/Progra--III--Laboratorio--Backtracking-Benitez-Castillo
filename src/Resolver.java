// Archivo: Resolver.java
// Algoritmo de backtracking principal.
// Integra: Reto 1 (contadores), Reto 2 (orden),
//          Reto 4 (profundidad), Reto 5 (heurística)
public class Resolver {

    public static boolean resolver(LaberintoGrafico panel, int fila, int col) {

        panel.repaint();
        panel.dormir();

        // Fuera del tablero
        if (fila < 0 || col < 0 ||
                fila >= panel.laberinto.length ||
                col  >= panel.laberinto[0].length) {
            return false;
        }

        // Pared o ya visitado
        if (panel.laberinto[fila][col] == 1 ||
                panel.laberinto[fila][col] == 9 ||
                panel.laberinto[fila][col] == 5) {
            return false;
        }

        // RETO 1 — contar llamadas válidas
        panel.llamadas++;

        // RETO 4 — actualizar profundidad
        panel.profundidadActual++;
        if (panel.profundidadActual > panel.profundidadMaxima)
            panel.profundidadMaxima = panel.profundidadActual;

        // Salida encontrada
        if (panel.laberinto[fila][col] == 2) {
            panel.profundidadActual--;
            return true;
        }

        // Marcar celda como parte del camino actual
        panel.laberinto[fila][col] = 9;

        // RETO 4 — contar nodos explorados
        panel.nodosExplorados++;

        panel.repaint();
        panel.dormir();

        // RETO 2 + RETO 5 — obtener direcciones
        int[][] dirs = obtenerDirecciones(panel, fila, col);

        for (int[] d : dirs) {
            if (resolver(panel, fila + d[0], col + d[1])) {
                panel.profundidadActual--;
                return true;
            }
        }

        // RETO 1 — contar retrocesos
        panel.retrocesos++;

        // Backtracking visual
        panel.laberinto[fila][col] = 5;

        panel.repaint();
        panel.dormir();

        panel.profundidadActual--;
        return false;
    }

    // RETO 2 — Orden de exploración configurable
    // RETO 5 — Heurística distancia Manhattan
    private static int[][] obtenerDirecciones(LaberintoGrafico panel, int fila, int col) {

        // {deltaFila, deltaCol}
        int[][] original = {{-1,0},{0,1},{1,0},{0,-1}}; // arriba, derecha, abajo, izquierda
        int[][] derecha  = {{0,1},{1,0},{-1,0},{0,-1}}; // derecha primero
        int[][] abajo    = {{1,0},{0,1},{-1,0},{0,-1}}; // abajo primero

        int[][] base;
        switch (Configuracion.ORDEN) {
            case "DERECHA": base = derecha; break;
            case "ABAJO":   base = abajo;   break;
            default:        base = original; break;
        }

        // RETO 5 — reordenar por distancia Manhattan a la salida
        if (Configuracion.HEURISTICA && panel.filaSalida >= 0) {
            int[][] dirs = base.clone();
            java.util.Arrays.sort(dirs, (a, b) -> {
                int dA = Math.abs((fila + a[0]) - panel.filaSalida)
                        + Math.abs((col  + a[1]) - panel.colSalida);
                int dB = Math.abs((fila + b[0]) - panel.filaSalida)
                        + Math.abs((col  + b[1]) - panel.colSalida);
                return Integer.compare(dA, dB);
            });
            return dirs;
        }

        return base;
    }
}