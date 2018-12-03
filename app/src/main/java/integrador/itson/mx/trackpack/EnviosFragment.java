package integrador.itson.mx.trackpack;


import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EnviosFragment extends Fragment {
    TextView fecha, numeroRastreo;
    ListView lista;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            Orden o = (Orden) getArguments().getSerializable("o");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Orden o = (Orden) getArguments().getSerializable("o");
        View view=inflater.inflate(R.layout.fragment_envios,container,false);
        fecha=view.findViewById(R.id.fechas);
        numeroRastreo = view.findViewById(R.id.numRastreo);
        lista = view.findViewById(R.id.listEnvio);
        fecha.setText(o.getFecha());
        numeroRastreo.setText(o.getNumeroOrden());
        List<String> listaHistoriales = llenarList(o.getHistoriales());
        ListAdapter _baseAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, listaHistoriales);
        lista.setAdapter(_baseAdapter);
        return view;

    }

    public List<String> llenarList(List<Historial>listaHistorial)
    {
        List<String> listaHistoriales = new ArrayList<>();
        for(int i=0; i<listaHistorial.size(); i++)
        {
            String resultado = "Fecha: "+listaHistorial.get(i).getFecha()+
                    "\nLugar: "+ listaHistorial.get(i).getCiudad()+", "+listaHistorial.get(i).getEstado()+
                    "\nDescripción: "+listaHistorial.get(i).getDescripcion();
            listaHistoriales.add(resultado);
        }
        return listaHistoriales;
    }


}
