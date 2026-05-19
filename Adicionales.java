//Variables globales

private int llamadas = 0;
private int retrocesos = 0;
private long inicio;
private long fin;

//medición: colocar en main

panel.inicio = System.nanoTime();
boolean solucion = panel.resolver(0, 0);
panel.fin = System.nanoTime();


//Resultados
System.out.println("Solución encontrada: " + solucion);
System.out.println("Llamadas recursivas: " + panel.llamadas);
System.out.println("Retrocesos: " + panel.retrocesos);
System.out.println("Tiempo (ms): " +
        (panel.fin - panel.inicio) / 1_000_000.0);

//Dentro de resolver()
llamadas++;

//En el backtracking
retrocesos++;