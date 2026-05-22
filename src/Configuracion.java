// Archivo: Configuracion.java
// Centraliza los parámetros de todos los retos.
// Solo cambia aquí para cambiar el comportamiento.
public class Configuracion {

    // RETO 1 — Tamaño del laberinto
    // Valores válidos: 5 | 6 | 10 | 20 | -1 (imposible)
    public static int TAMANIO = 20;

    // RETO 2 — Orden de exploración
    // Valores válidos: "ORIGINAL" | "DERECHA" | "ABAJO"
    public static String ORDEN = "ORIGINAL";

    // RETO 5 — Heurística de distancia Manhattan
    // Valores válidos: false | true
    public static boolean HEURISTICA = false;
}