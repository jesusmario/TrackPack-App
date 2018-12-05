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
        String [] fechaSplit = o.getFecha().split(" ");
        fecha.setText(fechaSplit[0]);
        numeroRastreo.setText(o.getNumeroOrden());

        List<Historial> listaHistoriales = o.getHistoriales();
        List<Historial> listaHistorial = new ArrayList<>();
        for(int i=0; i<listaHistoriales.size(); i++)
        {
            Historial h = new Historial();
            h.setId(i);
            h.setDescripcion(listaHistoriales.get(i).getDescripcion());
            h.setFecha(listaHistoriales.get(i).getFecha());
            listaHistorial.add(h);
        }

        Adaptador _baseAdapter = new Adaptador(getContext(), listaHistorial);
        lista.setAdapter(_baseAdapter);
        return view;

    }



}
