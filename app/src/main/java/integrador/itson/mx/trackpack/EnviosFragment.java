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
        // Inflate the layout for this fragment
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

            String []arrayFecha = listaHistorial.get(i).getFecha().replace("T", " ").split(" ");
            String fechaHistorial = obtenerFecha(arrayFecha[0]);
            String resultado = "Fecha: "+fechaHistorial+"\nLugar: "+ listaHistorial.get(i).getCiudad()+", "+listaHistorial.get(i).getEstado()+
                    "\nDescripción: "+listaHistorial.get(i).getDescripcion();
            listaHistoriales.add(resultado);
        }
        return listaHistoriales;
    }

    public String obtenerFecha(String fechaArray)
    {
        String[]arreglo = fechaArray.split("-");
        String mes="";
        String fecha ="";
        switch (arreglo[1])
        {
            case "1":
                mes="Enero";
                break;
            case "2":
                mes="Febrero";
                break;
            case "3":
                mes ="Marzo";
                break;
            case "4":
                mes="Abril";
                break;
            case "5":
                mes="Mayo";
                break;
            case "6":
                mes ="Junio";
                break;
            case "7":
                mes="Julio";
                break;
            case "8":
                mes="Agosto";
                break;
            case "9":
                mes ="Septiembre";
                break;
            case "10":
                mes="Octubre";
                break;
            case "11":
                mes="Noviembre";
                break;
            case "12":
                mes ="Diciembre";
                break;
        }

        fecha = arreglo[2]+" de "+mes+" de "+arreglo[0];
        return fecha;
    }


}
