
package modelos;


public class Posicion {
    private int codigo;
    private String nombre;

    public Posicion() {
    }

    public Posicion(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
