/*
  Clase Tablero - gestiona la cuadricula de 6x6 celdas en el Juego de la Vida
  Implementa la lógoca de mundo cicular donde los bordes se continuan entre ellos
*/
public class Board {
    private Cell[][] cuadricula;
    private static final int TAMAÑO = 6;

    /*
      Constructor - inicializa un tablero de 6x6 con todas las celdas muertas
    */
    public Board() {
        this.cuadricula = new Cell[TAMAÑO][TAMAÑO];
        inicializarTablero();
    }

    /*
      Inicializa todas las celdas como muertas
    */
    private void inicializarTablero() {
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                cuadricula[fila][columna] = new Cell(false);
            }
        }
    }

    /*
      Obtiene una celula en la posicion especificada
      @param fila el indice de la fila
      @param columna el indice de la columna
      @return la Celula en esa posicion
    */
    public Cell obtenerCelula(int fila, int columna) {
        return cuadricula[fila][columna];
    }

    /*
      Establece una celula como viva en la posicion especificada
      @param fila el indice de la fila
      @param columna el indice de la columna
    */
    public void establecerViva(int fila, int columna) {
        cuadricula[fila][columna].setViva(true);
    }

    /*
      Establece una celula como muerta en la posicion especificada
      @param fila el indice de la fila
      @param columna el indice de la columna
    */
    public void establecerMuerta(int fila, int columna) {
        cuadricula[fila][columna].setViva(false);
    }

    /*
      Cuenta el numero de vecinos vivos para una celula en la posicion especificada
      Implementa topologia esferica - los bordes se envuelven
      @param fila el indice de la fila
      @param columna el indice de la columna
      @return el numero de vecinos vivos (0-8)
    */
    public int contarVecinos(int fila, int columna) {
        int cantidad = 0;

        // Verifica los 8 vecinos usando desplazamientos
        // Desplazamientos: (-1,-1), (-1,0), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)
        for (int n = -1; n <= 1; n++) {
            for (int y = -1; y <= 1; y++) {
                // Salta la celula central (la celula misma)
                if (n == 0 && y == 0) {
                    continue;
                }

                // Calcula la posicion del vecino con envoltura (topologia esferica)
                int filaVecino = (fila + n + TAMAÑO) % TAMAÑO;
                int columnaVecino = (columna + y + TAMAÑO) % TAMAÑO;

                // Cuenta si el vecino esta vivo
                if (cuadricula[filaVecino][columnaVecino].estaViva()) {
                    cantidad++;
                }
            }
        }

        return cantidad;
    }

    /*
      Evoluciona el tablero una generacion aplicando todas las reglas del Juego de la Vida
      Reglas:
      - Nacimiento: Una celula muerta con exactamente 3 vecinos cobra vida
      - Supervivencia: Una celula viva con 2, 3 o 4 vecinos sobrevive
      - Muerte: Una celula viva con < 2 o > 4 vecinos muere
      - Espontaneo: 10% de probabilidad para que cualquier celula cambie de estado
    */
    public void proximaGeneracion() {
        // Crea un tablero temporal para almacenar los nuevos estados
        Cell[][] nuevaCuadricula = new Cell[TAMAÑO][TAMAÑO];

        // Primera pasada: Calcula los nuevos estados basados en el estado actual
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                int vecinos = contarVecinos(fila, columna);
                boolean estadoActual = cuadricula[fila][columna].estaViva();
                int edadActual = cuadricula[fila][columna].getEdad();
                boolean nuevoEstado = estadoActual;

                // Aplica la regla de nacimiento
                if (!estadoActual && vecinos == 3) {
                    nuevoEstado = true;
                }
                // Aplica las reglas de supervivencia y muerte
                else if (estadoActual) {
                    if (vecinos < 2 || vecinos > 4) {
                        nuevoEstado = false; // Muerte
                    }
                    // si no: supervivencia (nuevoEstado permanece true)
                }

                // Aplica generacion/muerte espontanea (10% de probabilidad)
                if (Math.random() < 0.1) {
                    nuevoEstado = !nuevoEstado;
                }

                // Crear nueva celula con estado actualizado
                Cell nuevaCelula = new Cell(nuevoEstado);

                // Mantener y actualizar edad
                if (nuevoEstado && estadoActual) {
                    // Supervivencia: incrementa edad
                    nuevaCelula.setViva(true);
                    for (int i = 0; i < edadActual + 1; i++) {
                        nuevaCelula.incrementarEdad();
                    }
                } else if (nuevoEstado && !estadoActual) {
                    // Nacimiento: edad es 1
                    nuevaCelula.setViva(true);
                } else {
                    // Muerta: edad es 0
                    nuevaCelula.setViva(false);
                }

                nuevaCuadricula[fila][columna] = nuevaCelula;
            }
        }

        // Actualiza el tablero con los nuevos estados
        this.cuadricula = nuevaCuadricula;
    }

    /*
      Obtiene el numero total de celdas vivas en el tablero
      @return cantidad de celdas vivas
    */
    public int obtenerCantidadVivas() {
        int cantidad = 0;
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                if (cuadricula[fila][columna].estaViva()) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    /*
      Muestra el tablero en un formato legible sin bordes
      Usa '1' para celdas vivas y '0' para celdas muertas
      Las celdas estan separadas por espacios para mejor legibilidad
    */
    public void mostrar() {
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                System.out.print(cuadricula[fila][columna].aCaracter() + " ");
            }
            System.out.println();
        }
    }

    /*
      Obtiene el tamaño del tablero
      @return el tamaño de la cuadricula cuadrada
    */
    public int obtenerTamaño() {
        return TAMAÑO;
    }

    /*
      Muestra un registro detallado de cada celula en el tablero
      Incluye posicion, estado (viva/muerta), y edad
    */
    public void mostrarRegistroCeldas() {
        System.out.println("\n--- REGISTRO DETALLADO DE CELDAS ---");
        System.out.println("Formato: Posicion(fila,columna) | Estado | Edad");
        System.out.println("-----------------------------------");

        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                Cell celula = cuadricula[fila][columna];
                String estado = celula.estaViva() ? "VIVA" : "MUERTA";
                String posicion = "(" + fila + "," + columna + ")";
                String edad = String.valueOf(celula.getEdad());

                System.out.println(posicion + " | " + estado + " | " + edad);
            }
        }
        System.out.println("-----------------------------------\n");
    }
}
