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

public class CostoEnvio extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener, View.OnClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    double latitudeStart, longitudStart;
    double end_lat, end_long;
    EditText localA, localB;
    Button btn;
    MarkerOptions mo, mo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn = findViewById(R.id.btnCalcular);
        localA = findViewById(R.id.txtLocationA);
        localB = findViewById(R.id.txtLocationB);
        btn.setOnClickListener(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng b = new LatLng(0, 0);
        mo = new MarkerOptions();
        mo.position(b);
        mo.title("Destination2");
        mo.draggable(false);
        mMap.addMarker(mo);

        LatLng a = new LatLng(0, 0);
        mo2 = new MarkerOptions();
        mo2.position(b);
        mo2.title("Destination1");
        mo2.draggable(false);
        mMap.addMarker(mo2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textGrid:
                mMap.clear();

                String a = String.valueOf(localA.getText());
                String b = String.valueOf(localB.getText());

                Location l1 = new Location("");
                latitudeStart = getLat(a);
                l1.setLatitude(latitudeStart);
                longitudStart = getLong(a);
                l1.setLongitude(longitudStart);

                Location l2 = new Location("");
                end_lat = getLat(b);
                l2.setLatitude(end_lat);
                end_long = getLong(b);
                l2.setLongitude(end_long);


                LatLng aa = new LatLng(latitudeStart, longitudStart);
                mo = new MarkerOptions();
                mo.position(aa);
                mo.title("Destination A");
                mo.draggable(false);
                mMap.addMarker(mo);

                LatLng bb = new LatLng(end_lat, end_long);
                mo2 = new MarkerOptions();
                mo2.position(bb);
                mo2.title("Destination B");
                mo2.draggable(false);
                mMap.addMarker(mo2);

                float distanceInMeters = l1.distanceTo(l2);
                Toast.makeText(CostoEnvio.this, "Distancia= " + distanceInMeters, Toast.LENGTH_LONG).show();

                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //asd
    }

    @Override
    public void onMapLongClick(LatLng point) {
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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


