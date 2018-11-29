package integrador.itson.mx.trackpack;

import android.text.method.DateTimeKeyListener;

import java.util.Date;

public class Historial {
    String fecha;
    String descripcion;
    String ciudad;
    String estado;
    String latitud;
    String longitud;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha= fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion= descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}
