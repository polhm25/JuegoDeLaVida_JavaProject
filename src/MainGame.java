/*
  Clase JuegoDelVida - Punto de entrada para la simulacion del Juego de la Vida
  Gestiona el menu principal y controla el flujo del programa
*/
public class MainGame {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean salir = false;

        while (!salir) {
            int opcion = menu.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    // Ejecutar Simulacion Predefinida
                    menu.ejecutarSimulacion();
                    break;

                case 2:
                    // Crear Configuracion Personalizada
                    menu.crearConfiguracionPersonalizada();
                    if (menu.obtenerTablero() != null) {
                        menu.ejecutarSimulacion();
                    }
                    break;

                case 3:
                    // Generacion Aleatoria
                    menu.generacionAleatoria();
                    if (menu.obtenerTablero() != null) {
                        menu.ejecutarSimulacion();
                    }
                    break;

                case 4:
                    // Patron Predefinido
                    menu.seleccionarPatronPredefinido();
                    if (menu.obtenerTablero() != null) {
                        menu.ejecutarSimulacion();
                    }
                    break;

                case 5:
                    // Configurar Parametros
                    menu.configurarParametros();
                    break;

                case 6:
                    // Salir
                    System.out.println("\n¡Gracias por jugar el Juego de la Vida!");
                    salir = true;
                    break;
            }
        }
    }

    /*
      Crea la configuracion predefinida original
    */
    private static Board crearConfiguracionPredefinida() {
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
}
