/*
  Clase Menu - Gestiona la interfaz de menu del Juego de la Vida
  Permite al usuario seleccionar diferentes opciones de juego
*/

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Board tablero;
    private int generaciones;
    private int retardoMs;

    /*
      Constructor - inicializa el menu
    */
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.generaciones = 20;
        this.retardoMs = 500;
        this.tablero = crearConfiguracionPredefinida();
    }

    /*
      Muestra el menu principal y obtiene la opcion del usuario
      @return la opcion seleccionada (1-6)
    */
    public int mostrarMenuPrincipal() {
        limpiarPantalla();
        System.out.println("=====================================");
        System.out.println("      JUEGO DE LA VIDA");
        System.out.println("=====================================\n");
        System.out.println("1. Ejecutar Simulacion Predefinida");
        System.out.println("2. Crear Configuracion Personalizada");
        System.out.println("3. Generacion Aleatoria");
        System.out.println("4. Patron Predefinido");
        System.out.println("5. Configurar Parametros");
        System.out.println("6. Salir");
        System.out.println("\n=====================================");
        System.out.print("Selecciona una opcion (1-6): ");

        return obtenerEntrada(1, 6);
    }

    /*
      Muestra el menu de patrones predefinidos
      @return la opcion seleccionada (1-5)
    */
    public int mostrarMenuPatrones() {
        limpiarPantalla();
        System.out.println("=====================================");
        System.out.println("      PATRONES PREDEFINIDOS");
        System.out.println("=====================================\n");
        System.out.println("1. Bloque (Estable)");
        System.out.println("2. Parpadeador (Oscilante)");
        System.out.println("3. Vela (En Movimiento)");
        System.out.println("4. Cruz (Patron Interesante)");
        System.out.println("5. Volver al Menu Principal");
        System.out.println("\n=====================================");
        System.out.print("Selecciona un patron (1-5): ");

        return obtenerEntrada(1, 5);
    }

    /*
      Ejecuta la simulacion con la configuracion actual
    */
    public void ejecutarSimulacion() {
        limpiarPantalla();
        System.out.println("Iniciando simulacion con " + generaciones + " generaciones...\n");
        ejecutarJuego(tablero);
    }

    /*
      Permite al usuario crear una configuracion personalizada
    */
    public void crearConfiguracionPersonalizada() {
        limpiarPantalla();
        System.out.println("=====================================");
        System.out.println("      CONFIGURACION PERSONALIZADA");
        System.out.println("=====================================\n");

        tablero = new Board();
        boolean agregandoCeldas = true;

        System.out.println("Ingresa las coordenadas de las celdas vivas");
        System.out.println("(Las filas y columnas van de 0 a 5)");
        System.out.println("Escribe 'listo' cuando termines\n");

        while (agregandoCeldas) {
            System.out.print("Fila (0-5) o 'listo': ");
            String inputFila = scanner.nextLine().trim().toLowerCase();

            if (inputFila.equals("listo")) {
                agregandoCeldas = false;
                break;
            }

            try {
                int fila = Integer.parseInt(inputFila);

                if (fila < 0 || fila > 5) {
                    System.out.println("Error: La fila debe estar entre 0 y 5");
                    continue;
                }

                System.out.print("Columna (0-5): ");
                String inputColumna = scanner.nextLine().trim();
                int columna = Integer.parseInt(inputColumna);

                if (columna < 0 || columna > 5) {
                    System.out.println("Error: La columna debe estar entre 0 y 5");
                    continue;
                }

                tablero.establecerViva(fila, columna);
                System.out.println("Celula agregada en (" + fila + ", " + columna + ")");
                System.out.println();

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un numero valido");
            }
        }

        if (tablero.obtenerCantidadVivas() == 0) {
            System.out.println("\nNo has agregado ninguna celula. Usando configuracion predefinida.");
            tablero = crearConfiguracionPredefinida();
        } else {
            System.out.println("\nConfiguracion creada con " + tablero.obtenerCantidadVivas() + " celdas vivas");
            System.out.println("Tablero Inicial:");
            mostrarGeneracion(tablero, 0);
            esperarEnter();
        }
    }

    /*
      Genera un tablero con celdas aleatorias
    */
    public void generacionAleatoria() {
        limpiarPantalla();
        System.out.println("Generando configuracion aleatoria...\n");

        tablero = new Board();

        // Llena aproximadamente el 30% del tablero con celdas aleatorias
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                if (Math.random() < 0.3) {
                    tablero.establecerViva(fila, columna);
                }
            }
        }

        System.out.println("Configuracion aleatoria creada con " + tablero.obtenerCantidadVivas() + " celdas vivas\n");
        System.out.println("Tablero Inicial:");
        mostrarGeneracion(tablero, 0);
        esperarEnter();
    }

    /*
      Permite al usuario seleccionar un patron predefinido
    */
    public void seleccionarPatronPredefinido() {
        int opcion = mostrarMenuPatrones();

        switch (opcion) {
            case 1:
                tablero = crearPatronBloque();
                System.out.println("\nPatron 'Bloque' creado");
                break;
            case 2:
                tablero = crearPatronParpadeador();
                System.out.println("\nPatron 'Parpadeador' creado");
                break;
            case 3:
                tablero = crearPatronVela();
                System.out.println("\nPatron 'Vela' creado");
                break;
            case 4:
                tablero = crearPatronCruz();
                System.out.println("\nPatron 'Cruz' creado");
                break;
            case 5:
                return;
        }

        System.out.println("Tablero Inicial:");
        mostrarGeneracion(tablero, 0);
        esperarEnter();
    }

    /*
      Permite al usuario configurar parametros del juego
    */
    public void configurarParametros() {
        limpiarPantalla();
        System.out.println("=====================================");
        System.out.println("      CONFIGURAR PARAMETROS");
        System.out.println("=====================================\n");

        System.out.println("Configuracion Actual:");
        System.out.println("Generaciones: " + generaciones);
        System.out.println("Retardo (ms): " + retardoMs + "\n");

        System.out.print("¿Cuantas generaciones deseas ejecutar? (1-100): ");
        int nuevasGeneraciones = obtenerEntrada(1, 100);
        this.generaciones = nuevasGeneraciones;

        System.out.print("¿Cual es el retardo entre generaciones en ms? (100-2000): ");
        int nuevoRetardo = obtenerEntrada(100, 2000);
        this.retardoMs = nuevoRetardo;

        System.out.println("\nParametros actualizados correctamente!");
        esperarEnter();
    }

    /*
      Crea la configuracion predefinida original
    */
    private Board crearConfiguracionPredefinida() {
        Board board = new Board();

        // Bloque estable
        board.establecerViva(1, 1);
        board.establecerViva(1, 2);
        board.establecerViva(2, 1);
        board.establecerViva(2, 2);

        // Parpadeador
        board.establecerViva(3, 4);
        board.establecerViva(3, 5);
        board.establecerViva(3, 3);

        // Algunos patrones aleatorios
        board.establecerViva(0, 0);
        board.establecerViva(5, 5);
        board.establecerViva(5, 4);
        board.establecerViva(4, 4);

        return board;
    }

    /*
      Crea un patron "Bloque" (2x2 estable)
    */
    private Board crearPatronBloque() {
        Board board = new Board();

        board.establecerViva(2, 2);
        board.establecerViva(2, 3);
        board.establecerViva(3, 2);
        board.establecerViva(3, 3);

        return board;
    }

    /*
      Crea un patron "Parpadeador" (oscilante 2 generaciones)
    */
    private Board crearPatronParpadeador() {
        Board board = new Board();

        // Parpadeador horizontal
        board.establecerViva(2, 2);
        board.establecerViva(2, 3);
        board.establecerViva(2, 4);

        return board;
    }

    /*
      Crea un patron "Vela" (glider - se mueve diagonalmente)
    */
    private Board crearPatronVela() {
        Board board = new Board();

        // Glider pattern
        board.establecerViva(0, 1);
        board.establecerViva(1, 2);
        board.establecerViva(2, 0);
        board.establecerViva(2, 1);
        board.establecerViva(2, 2);

        return board;
    }

    /*
      Crea un patron "Cruz"
    */
    private Board crearPatronCruz() {
        Board board = new Board();

        // Cruz en el centro
        board.establecerViva(2, 3);
        board.establecerViva(3, 2);
        board.establecerViva(3, 3);
        board.establecerViva(3, 4);
        board.establecerViva(4, 3);

        return board;
    }

    /*
      Ejecuta el ciclo principal del juego
    */
    private void ejecutarJuego(Board board) {
        for (int generacion = 0; generacion <= generaciones; generacion++) {
            if (generacion > 0) {
                board.proximaGeneracion();
            }

            mostrarGeneracion(board, generacion);

            if (generacion < generaciones) {
                try {
                    Thread.sleep(retardoMs);
                } catch (InterruptedException e) {
                    System.out.println("Simulacion interrumpida");
                    break;
                }
            }
        }

        System.out.println("=====================================");
        System.out.println("Simulacion completada!");
        System.out.println("=====================================");
        esperarEnter();
    }

    /*
      Muestra una generacion con informacion y registro detallado de celdas
    */
    private void mostrarGeneracion(Board board, int generacion) {
        System.out.println("\nGeneracion: " + generacion);
        System.out.println("Celdas vivas: " + board.obtenerCantidadVivas() + "/" + (board.obtenerTamaño() * board.obtenerTamaño()));
        board.mostrar();
        board.mostrarRegistroCeldas();
    }

    /*
      Obtiene entrada numerica del usuario dentro de un rango
      @param min el valor minimo permitido
      @param max el valor maximo permitido
      @return la entrada validada del usuario
    */
    private int obtenerEntrada(int min, int max) {
        int entrada = -1;

        while (entrada < min || entrada > max) {
            try {
                entrada = Integer.parseInt(scanner.nextLine().trim());

                if (entrada < min || entrada > max) {
                    System.out.print("Debes ingresar un valor entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Intenta de nuevo: ");
            }
        }

        return entrada;
    }

    /*
      Espera a que el usuario presione Enter
    */
    private void esperarEnter() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    /*
      Limpia la pantalla (funciona mejor en Linux/Mac, en Windows puede no ser completamente limpio)
    */
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*
      Obtiene el tablero actual
      @return el tablero actual
    */
    public Board obtenerTablero() {
        return tablero;
    }
}
