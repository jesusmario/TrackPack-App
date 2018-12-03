package integrador.itson.mx.trackpack;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Double latitud, longitud;
    public MapsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Orden o = (Orden) getArguments().getSerializable("o");
        List<Historial>listaHistoriales = o.getHistoriales();

        for(int i=0; i<listaHistoriales.size(); i++) {
            String direcccion = listaHistoriales.get(i).getCiudad() + ", " + listaHistoriales.get(i).getEstado();
            latitud = getLat(direcccion);
            longitud = getLong(direcccion);
            LatLng prueba = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(prueba));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(prueba));

        }
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public double getLat(String direccion) {
        Geocoder geoCoder = new Geocoder(getActivity());
        double result = 0 ;
        try {
            List<Address> addressList = geoCoder.getFromLocationName(direccion, 1);
            if (addressList != null && addressList.size() > 0) {
                double lat = addressList.get(0).getLatitude();
                result = lat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return result;
    } // end convertAddress

    public double getLong(String direccion) {
        Geocoder geoCoder = new Geocoder(getActivity());
        double result = 0 ;
        try {
            List<Address> addressList = geoCoder.getFromLocationName(direccion, 1);
            if (addressList != null && addressList.size() > 0) {
                double lng = addressList.get(0).getLongitude();
                result = lng;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return result;
    }
}
