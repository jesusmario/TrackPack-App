package integrador.itson.mx.trackpack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends BaseAdapter {


    Context contexto;
    List<Historial> listaHistoriales;

    public Adaptador(Context contexto, List<Historial> listaHistoriales) {
        this.contexto = contexto;
        this.listaHistoriales = listaHistoriales;
    }

    @Override
    public int getCount() {
        return this.listaHistoriales.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaHistoriales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaHistoriales.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;

        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.itemlist, null);

        TextView titulo = (TextView) vista.findViewById(R.id.titulo);
        TextView descripcion = (TextView) vista.findViewById(R.id.descripcion);

        titulo.setText(this.listaHistoriales.get(position).getDescripcion().toString());
        descripcion .setText(this.listaHistoriales.get(position).getFecha().toString());
        return vista;


    }
}
