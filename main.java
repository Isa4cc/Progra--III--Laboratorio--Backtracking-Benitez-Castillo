import javax.swing.*;
import java.awt.*;

public class LaberintoGrafico extends JPanel {

    private int[][] laberinto = {

            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 2}

    };

    private final int TAM = 80;

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Backtracking - Laberinto");

        LaberintoGrafico panel = new LaberintoGrafico();

        ventana.add(panel);
        ventana.setSize(520, 540);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        new Thread(() -> {

            panel.resolver(0, 0);

        }).start();
    }