// ─────────────────────────────────────────────────────
// Archivo: Laberintos.java
// Contiene todos los laberintos del laboratorio.
// 0 = libre | 1 = pared | 2 = salida
// ─────────────────────────────────────────────────────
public class Laberintos {

    public static int[][] obtener(int size) {

        // ── RETO 1 ── Laberinto 5x5
        if (size == 5) {
            return new int[][] {
                    {0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 0},
                    {0, 0, 0, 1, 0},
                    {1, 1, 0, 1, 0},
                    {0, 0, 0, 0, 2}
            };
        }

        // ── RETO 1 ── Laberinto 10x10
        if (size == 10) {
            return new int[][] {
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                    {0, 1, 1, 1, 1, 0, 0, 0, 0, 2}
            };
        }

        // ── RETO 1 ── Laberinto 20x20
        if (size == 20) {
            int[][] m = new int[20][20];
            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++)
                    m[i][j] = (i % 2 == 1 && j % 3 != 2) ? 1 : 0;
            m[0][0]   = 0;
            m[19][19] = 2;
            return m;
        }

        // ── RETO 3 ── Laberinto imposible (salida bloqueada)
        if (size == -1) {
            return new int[][] {
                    {0, 1, 0, 0, 0, 0},
                    {0, 1, 0, 1, 1, 0},
                    {0, 0, 0, 0, 1, 0},
                    {1, 1, 1, 0, 1, 1},
                    {0, 0, 0, 0, 1, 1},
                    {0, 1, 1, 1, 1, 2}
            };
        }

        // ── DEFAULT ── Laberinto 6x6 (original del profe)
        return new int[][] {
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 2}
        };
    }
}