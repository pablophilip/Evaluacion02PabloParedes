
package modelos;

public class Equipo {
    private int codigo;
    private String nombre;
    private int jugador;
    private Ciudad ciudad;
    private Divicion divicion;

    public Equipo() {
    }

    public Equipo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    
    public Equipo(int codigo, String nombre, int jugador, Ciudad ciudad, Divicion divicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.jugador = jugador;
        this.ciudad = ciudad;
        this.divicion = divicion;
    }

    public Divicion getDivicion() {
        return divicion;
    }

    public void setDivicion(Divicion divicion) {
        this.divicion = divicion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
     @Override
    public String toString(){
        return nombre;
    }
}
