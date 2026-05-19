    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int fila = 0; fila < laberinto.length; fila++) {

            for (int col = 0; col < laberinto[0].length; col++) {

                switch (laberinto[fila][col]) {

                    case 0:
                        g.setColor(Color.WHITE);
                        break;

                    case 1:
                        g.setColor(Color.BLACK);
                        break;

                    case 2:
                        g.setColor(Color.BLUE);
                        break;

                    case 9:
                        g.setColor(Color.GREEN);
                        break;

                    case 5:
                        g.setColor(Color.RED);
                        break;
                }

                g.fillRect(col * TAM, fila * TAM, TAM, TAM);

                g.setColor(Color.GRAY);
                g.drawRect(col * TAM, fila * TAM, TAM, TAM);
            }
        }
    }