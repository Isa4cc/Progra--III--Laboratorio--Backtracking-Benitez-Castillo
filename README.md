# Laboratorio de Backtracking - Resolución de Laberintos

## Descripción General

Este proyecto implementa un algoritmo de Backtracking para encontrar la salida de un laberinto. Es un laboratorio educativo diseñado para comprender cómo funciona el mecanismo de búsqueda con retroceso, la exploración exhaustiva de soluciones y el impacto de diferentes estrategias de búsqueda en el rendimiento.

El programa incluye visualización gráfica en tiempo real, métricas de rendimiento y múltiples configuraciones para experimentar con diferentes enfoques de resolución.

---

## Estructura del Proyecto

| Archivo | Descripción |
|---------|-------------|
| LaberintoGrafico.java | Interfaz gráfica y panel principal (JPanel) |
| Resolver.java | Algoritmo de backtracking recursivo |
| Configuracion.java | Parámetros centralizados del proyecto |
| Laberintos.java | Conjunto de laberintos predefinidos |

---

## Como Funciona el Repositorio

El proyecto implementa un sistema de resolución de laberintos mediante backtracking recursivo. Cuando se ejecuta la aplicación, el programa carga un laberinto según la configuración especificada y comienza a explorar desde la posición inicial (arriba a la izquierda) en busca de la salida (ubicada en la esquina inferior derecha).

El algoritmo trabaja de la siguiente manera: en cada paso, intenta moverse en una dirección específica (según el orden configurado). Si el movimiento es válido, marca esa celda como parte del camino actual y continúa recursivamente. Si llega a un callejón sin salida o encuentra paredes, retrocede y marca la celda como explorada sin éxito. El proceso continúa hasta encontrar la salida o agotar todas las posibilidades.

La interfaz gráfica muestra en tiempo real cómo el algoritmo explora el laberinto con diferentes colores: blanco para celdas libres, negro para paredes, azul para el camino actual, rojo para retrocesos y verde para la salida. Además, mantiene un panel de información que muestra métricas como el número total de llamadas al algoritmo, la cantidad de retrocesos, los nodos explorados y la profundidad actual de recursión.

El sistema es completamente configurable. Puedes cambiar el tamaño del laberinto, el orden en que se exploran las direcciones (original, derecha primero o abajo primero) e incluso activar una heurística basada en distancia Manhattan que guía la búsqueda hacia la salida de forma más inteligente.

---

## Como Usar el Proyecto

Cambiar configuración editando los valores en Configuracion.java:

TAMANIO: Tamaño del laberinto (5 para 5x5, 6 para 6x6, 10 para 10x10, 20 para 20x20, -1 para laberinto imposible)

ORDEN: Estrategia de búsqueda ("ORIGINAL" para arriba-derecha-abajo-izquierda, "DERECHA" para derecha primero, "ABAJO" para abajo primero)

HEURISTICA: Activar optimización con distancia Manhattan (false o true)

---

## Respuestas a Preguntas de Reflexion

### 1. Por que algunos laberintos son mucho mas costosos?

El costo depende de la estructura del laberinto y la estrategia de búsqueda. Los laberintos más pequeños como 5x5 tienen menos celdas y típicamente necesitan 15-25 llamadas. Los laberintos más grandes como 20x20 tienen mucha más complejidad y pueden necesitar 800-2000 llamadas o más. 

La estructura del laberinto es crucial: laberintos con muchas ramificaciones y callejones ciegos requieren más retrocesos. Si la salida está lejos del inicio, el algoritmo debe explorar más celdas antes de encontrarla. La densidad de paredes también influye: más paredes crean más restricciones, lo que puede reducir el espacio de búsqueda pero también puede bloquear rutas y forzar más exploración.

El número de celdas y posibles combinaciones crece cuadráticamente con el tamaño, por lo que un laberinto 20x20 requiere exponencialmente más exploraciones que uno 5x5.

---

### 2. Que provoca explosion combinatoria?

La explosión combinatoria ocurre cuando el algoritmo debe explorar múltiples caminos en cada paso. Cada celda libre puede tener hasta 4 opciones de movimiento: arriba, derecha, abajo e izquierda. Esto crea un árbol de decisión donde cada nivel tiene potencialmente 4 ramas.

La complejidad es exponencial: O(4^(n²)) donde n es la dimensión del laberinto. En laberintos sin estructura clara, hay muchas rutas posibles y el backtracking debe probarlas todas. Sin heurísticas que guíen la búsqueda, el algoritmo explora uniformemente en todas direcciones sin información sobre cuál es la correcta.

Matemáticamente, un laberinto 5x5 con 25 celdas podría generar hasta 100 exploraciones, un 10x10 con 100 celdas hasta 10,000 exploraciones, y un 20x20 con 400 celdas hasta 1,600,000 exploraciones teóricas. Por eso los laberintos grandes se vuelven exponencialmente más costosos.

---

### 3. Como influye el orden de busqueda?

El orden de exploración afecta significativamente el rendimiento. Con el orden ORIGINAL (arriba-derecha-abajo-izquierda), el algoritmo busca primero hacia arriba lo que causa muchos retrocesos iniciales. Esto es típicamente lento en laberintos con salida a la derecha o abajo.

Con el orden DERECHA, el algoritmo explora hacia la derecha primero. Esto es excelente si la salida está a la derecha y reduce significativamente las llamadas en laberintos con salida derecha o abajo, siendo aproximadamente 30-40 por ciento más rápido.

Con el orden ABAJO, se explora hacia abajo primero. Esto es excelente si la salida está en la parte inferior y más rápido en laberintos donde la salida está en la esquina inferior derecha.

Elegir el orden correcto puede reducir el tiempo de ejecución de O(4^n) a aproximadamente O(2^n), lo que demuestra el impacto crítico de esta decisión.

---

### 4. Por que backtracking puede ser exponencial?

Backtracking es inherentemente exponencial porque funciona con un árbol de decisión donde en cada paso el algoritmo puede tener hasta 4 ramas (4 direcciones posibles). La profundidad recursiva máxima es n por m, el número total de celdas. En el peor caso, el algoritmo debe explorar todas las combinaciones posibles de movimientos.

La complejidad temporal es O(4^(n×m)) en el peor caso. Teóricamente, un laberinto 5x5 tendría complejidad 4^25 aproximadamente 10^15, un 10x10 tendría 4^100 aproximadamente 10^60 que es extremadamente costoso, y así sucesivamente.

En la práctica, las paredes reducen el árbol de búsqueda haciéndolo más manejable, pero el comportamiento sigue siendo exponencial. A medida que aumenta el tamaño del laberinto, el costo crece de manera desproporcionada. Por eso se necesitan optimizaciones como heurísticas y poda más agresiva del árbol de búsqueda.

---

### 5. Que ocurriria si no marcamos posiciones visitadas?

Sin marcar posiciones visitadas, ocurriría un ciclo infinito. El algoritmo revisitaría la misma celda infinitamente. Por ejemplo, las celdas A y B se visitarían mutuamente en un bucle A→B→A→B sin fin. El programa nunca terminaría y eventualmente causaría un error de stack overflow.

El algoritmo no sabría que ya visitó esa posición y continuaría explorándola repetidamente. En el código actual, se previene esto verificando si la celda fue visitada antes: si el valor es 1 (pared), 9 (ya en camino actual) o 5 (ya retrocedida), se rechaza el movimiento.

Sin este control, las llamadas serían 10,000 o más infinitamente, el tiempo sería infinito y el resultado sería un error de stack overflow. Marcar visitadas es crítico en cualquier algoritmo de búsqueda como DFS, BFS o Backtracking.

---

### 6. Que pasaria si el laberinto no tiene solucion?

Si no hay solución, el algoritmo explora TODO el laberinto. Intenta cada celda posible antes de concluir que no existe camino hacia la salida. El resultado es que retorna falso después de agotar todas las opciones. El costo es máximo: las llamadas serán aproximadamente igual al número total de celdas del laberinto.

En un laberinto imposible donde la salida está rodeada de paredes bloqueadas, el algoritmo marca todas las celdas como retroceso (rojo en la visualización). Las métricas esperadas incluyen 10,000 o más llamadas, 5,000 o más retrocesos, tiempo muy lento de segundos y una pantalla casi completamente roja por los retrocesos.

El algoritmo explora cuidadosamente todas las posibilidades, eventualmente se queda sin opciones y retorna falso. Este es el ejemplo más claro de explosión combinatoria, demostrando por qué es importante tener heurísticas para detectar callejones sin salida temprano y optimizar la búsqueda.

---

## Optimizaciones Implementadas

El proyecto implementa una heurística de distancia Manhattan para optimizar la búsqueda. La distancia Manhattan se calcula como el valor absoluto de la diferencia en filas más el valor absoluto de la diferencia en columnas hacia la salida.

Esta heurística prioriza movimientos que acercan al algoritmo hacia la salida, reduce la exploración innecesaria y puede disminuir las llamadas entre 30 y 50 por ciento. Mantiene exactitud garantizando que siempre encuentra la solución si existe.

Se puede activar en Configuracion.java estableciendo HEURISTICA en true. Por ejemplo, sin heurística podrían necesitarse 150 llamadas en 80 milisegundos, pero con heurística se reduciría a 100 llamadas en 50 milisegundos, una mejora aproximada del 33 por ciento.

---

## Conceptos Educativos

Este laboratorio ilustra conceptos fundamentales usados en búsqueda en grafos como DFS, BFS y A*. También enseña sobre problemas de satisfacción de restricciones, exploración del espacio de soluciones y path finding.

El algoritmo demuestra trade-offs importantes: es completo (siempre encuentra la solución si existe), pero no es óptimo (solo encuentra una solución, no la mejor). Su complejidad es exponencial en general y utiliza memoria O(h) donde h es la profundidad máxima de recursión.

---

## Autores

Benitez y Castillo
Universidad UDLA - Semestre IV
Materia: Programación III - Laboratorio de Backtracking
