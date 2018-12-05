package integrador.itson.mx.trackpack;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CostoEnvio extends AppCompatActivity{
    Button cotizar;
    EditText localizacionDesde, localizacionHasta, peso;
    TextView totaltxt, resultado, cotizacionTotal;
    double latitudeStart, longitudStart, end_lat, end_long, resultadoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_costo_envio);
        totaltxt = findViewById(R.id.txtTextoCotizacion);
        resultado = findViewById(R.id.textototal);
        cotizacionTotal = findViewById(R.id.txtCotizacion);
        localizacionDesde = findViewById(R.id.locationA);
        localizacionHasta = findViewById(R.id.locationB);
        peso = findViewById(R.id.peso);
        totaltxt.setEnabled(false);
        resultado.setEnabled(false);
        cotizacionTotal.setEnabled(false);
        cotizar = findViewById(R.id.btnCalcular);


        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               servicioWeb _servicioWeb = new servicioWeb();
                _servicioWeb.execute();
            }
        });
    }

    private class servicioWeb  extends AsyncTask<String, String, String> {
        ProgressDialog p = new ProgressDialog(CostoEnvio.this);

        @Override
        protected String doInBackground(String... strings) {

            String NAMESPACE = "http://www.trackpack-ws.com/";
            String METHOD_NAME = "ObtenerPrecioEnvio";
            String SOAP_ACTION = "http://www.trackpack-ws.com/ObtenerPrecioEnvio";
            String URL = "https://www.trackpack-ws.com/WSTrackPack.asmx?WSDL";

            try{
                String localizacionA = localizacionDesde.getText().toString();
                String localizacionB =localizacionHasta.getText().toString();

                Location l1 = new Location("");
                latitudeStart = getLat(localizacionA);
                l1.setLatitude(latitudeStart);
                longitudStart = getLong(localizacionA);
                l1.setLongitude(longitudStart);

                Location l2 = new Location("");
                end_lat = getLat(localizacionB);
                l2.setLatitude(end_lat);
                end_long = getLong(localizacionB);
                l2.setLongitude(end_long);

                double distancia = l1.distanceTo(l2);
                String distanciaTotal =  String.valueOf(distancia/1000);
                String  pesoTotal = peso.getText().toString();


                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("distancia", distanciaTotal);
                request.addProperty("pesoPaquete", pesoTotal);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(request);

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call(SOAP_ACTION, soapEnvelope);

                //Respuesta
                SoapObject response = (SoapObject) soapEnvelope.bodyIn;
                String propiedad = response.getProperty(0).toString();
                resultadoTotal = Math.rint(Double.parseDouble(propiedad)*100)/100;
            }catch(Exception e){

                return  "error";
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setMessage("Cotizando tu envío");
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected void onPostExecute(String aString) {
            super.onPostExecute(aString);
            if(p.isShowing())
            {
                if(aString == null && resultadoTotal != 0)
                {
                    String respuesta = String.valueOf("$"+resultadoTotal);
                    cotizacionTotal.setText(respuesta);
                    cotizacionTotal.setEnabled(true);
                    totaltxt.setEnabled(false);
                    resultado.setEnabled(false);
                    p.dismiss();
                }else
                {
                    p.dismiss();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CostoEnvio.this);
                    alertDialogBuilder.setTitle("Datos no válidos");
                    alertDialogBuilder
                            .setMessage("Rellena todos los campos.")
                            .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    localizacionDesde.setText("");
                    localizacionHasta.setText("");
                    peso.setText("");


                }

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

