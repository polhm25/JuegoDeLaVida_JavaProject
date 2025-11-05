/*
  Clase Celula - representa una celula individual en el Juego de la Vida
  Cada celula puede estar viva o muerta y registra su edad
*/
public class Cell {
    private boolean viva;
    private int edad;

    /*
      Constructor - inicializa una celula como muerta con edad 0
    */
    public Cell() {
        this.viva = false;
        this.edad = 0;
    }

    /*
      Constructor - inicializa una celula con un estado especifico
      @param viva el estado inicial de la celula
    */
    public Cell(boolean viva) {
        this.viva = viva;
        this.edad = viva ? 1 : 0;
    }

    /*
      Verifica si la celula esta viva
      @return true si esta viva, false si esta muerta
    */
    public boolean estaViva() {
        return viva;
    }

    /*
      Obtiene la edad de la celula
      @return la edad en generaciones
    */
    public int getEdad() {
        return edad;
    }

    /*
      Establece el estado de la celula
      @param viva el nuevo estado
    */
    public void setViva(boolean viva) {
        this.viva = viva;
    }

    /*
      Incrementa la edad de la celula
      Se usa cuando una celula sobrevive a la siguiente generacion
    */
    public void incrementarEdad() {
        if (viva) {
            edad++;
        }
    }

    /*
      Reinicia la celula al estado muerto con edad 0
    */
    public void reiniciar() {
        this.viva = false;
        this.edad = 0;
    }

    /*
      Reinicia la edad a 1 cuando la celula nace
      Se usa cuando una celula cobra vida
    */
    public void reiniciarEdad() {
        this.edad = viva ? 1 : 0;
    }

    /*
      Obtiene una representacion de caracter de la celula
      @return '1' si esta viva, '0' si esta muerta
    */
    public char aCaracter() {
        return viva ? '1' : '0';
    }
}
