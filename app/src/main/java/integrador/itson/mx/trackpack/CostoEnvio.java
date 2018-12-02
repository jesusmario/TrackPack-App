package integrador.itson.mx.trackpack;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class CostoEnvio extends AppCompatActivity implements View.OnClickListener {

    //Button btn;
    //EditText localA, localB, resultado;
    double latitudeStart, longitudStart, end_lat, end_long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_costo_envio);
        //btn = findViewById(R.id.enviar);
       // btn.setOnClickListener(this);

        //localA = findViewById(R.id.locationA);
        //localB = findViewById(R.id.locationB);
      //  resultado = findViewById(R.id.distancia);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enviar:{

                //resultado.setText("");
                //String a = String.valueOf(localA.getText());
                //String b = String.valueOf(localB.getText());

                Location l1 = new Location("");
                //latitudeStart = getLat(a);
                l1.setLatitude(latitudeStart);
                //longitudStart = getLong(a);
                l1.setLongitude(longitudStart);

                Location l2 = new Location("");
                //end_lat = getLat(b);
                l2.setLatitude(end_lat);
                //end_long = getLong(b);
                l2.setLongitude(end_long);

                float distanceInMeters = l1.distanceTo(l2);
                distanceInMeters = distanceInMeters/1000;
                //resultado.setText(String.valueOf(distanceInMeters+" km"));
                //resultado.setEnabled(false);
                distanceInMeters = 0;

                break;

            }
        }
    }

    public double getLat(String direccion) {
        Geocoder geoCoder = new Geocoder(this);
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
        Geocoder geoCoder = new Geocoder(this);
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

