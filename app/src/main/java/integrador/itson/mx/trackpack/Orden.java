package integrador.itson.mx.trackpack;

import java.util.Date;
import java.util.List;

public class Orden {
    String numeroOrden;
    Date fecha;
    Destinatario destinatario;
    Paquete paquete;
    List<Historial> historiales;

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public Paquete getPaquete() {
        return paquete;
    }
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    public List<Historial> historiales() {
        return historiales;
    }
    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
    }

}
