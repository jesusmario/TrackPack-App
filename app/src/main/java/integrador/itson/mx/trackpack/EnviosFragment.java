package integrador.itson.mx.trackpack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class EnviosFragment extends Fragment {
    TextView fecha, numeroRastreo;
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
        fecha.setText(o.getFecha());
        numeroRastreo.setText(o.getNumeroOrden());

        return view;
    }


}
