    public boolean resolver(int fila, int col) {

        repaint();
        dormir();

        // Fuera del tablero
        if (fila < 0 || col < 0 ||
                fila >= laberinto.length ||
                col >= laberinto[0].length) {

            return false;
        }

        // Pared o visitado
        if (laberinto[fila][col] == 1 ||
                laberinto[fila][col] == 9 ||
                laberinto[fila][col] == 5) {

            return false;
        }

        // Salida encontrada
        if (laberinto[fila][col] == 2) {

            return true;
        }

        // Marcar camino actual
        laberinto[fila][col] = 9;

        repaint();
        dormir();

        // Arriba
        if (resolver(fila - 1, col)) return true;

        // Derecha
        if (resolver(fila, col + 1)) return true;

        // Abajo
        if (resolver(fila + 1, col)) return true;

        // Izquierda
        if (resolver(fila, col - 1)) return true;

        // BACKTRACKING VISUAL
        laberinto[fila][col] = 5;

        repaint();
        dormir();

        return false;
    }