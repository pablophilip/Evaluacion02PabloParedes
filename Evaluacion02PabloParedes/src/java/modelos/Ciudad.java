
package modelos;

public class Ciudad {
    private int codigo;
    private String nombre;

    public Ciudad() {
    }

    public Ciudad(int codigo, String nombre) {
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
