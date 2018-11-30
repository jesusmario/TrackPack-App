package integrador.itson.mx.trackpack;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RastrearEnvio extends AppCompatActivity {
    Button btnRegresar, btnRastrear;
    EditText  txtnumeroRastreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rastrear_envio);

        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnRastrear = (Button) findViewById(R.id.btnRastrear);
        txtnumeroRastreo = (EditText) findViewById(R.id.txtNumGuia);


        btnRastrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioWeb _servicioWeb = new servicioWeb();
                _servicioWeb.execute();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnRegresar = new Intent (RastrearEnvio.this, MenuPrincipal.class);
                startActivity(btnRegresar);
            }
        });



    }
    private class servicioWeb  extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            String NAMESPACE = "http://www.itrackpack.com/";
            String METHOD_NAME = "ObtenerHistorialOrden";
            String SOAP_ACTION = "http://www.itrackpack.com/ObtenerHistorialOrden";
            String URL = "https://www.itrackpack.com/WSTrackPack.asmx?WSDL";

            try{
                // Peticion o llamada al servicio web
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("numRastreo", "2018000001");
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(request);

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call(SOAP_ACTION, soapEnvelope);

                //Respuesta
                SoapObject response = (SoapObject) soapEnvelope.bodyIn;
                SoapObject body = (SoapObject) response.getProperty(0);

                //Datos destinatario.
                SoapObject destinatario = (SoapObject) body.getProperty("Destinatario");
                Destinatario d = new Destinatario();
                d.setNombre(destinatario.getProperty("Nombre").toString());
                d.setColonia(destinatario.getProperty("Colonia").toString());
                d.setAvenida(destinatario.getProperty("Avenida").toString());
                d.setCalle(destinatario.getProperty("Calle").toString());

                //Datos Historial.
                List<Historial> listaHistorial = new ArrayList<>();
                SoapObject ListaHistorial = (SoapObject) body.getProperty("Historiales");
                int historia = ListaHistorial.getPropertyCount();
                for(int i=0; i<historia; i++)
                {
                    SoapObject historial = (SoapObject) ListaHistorial.getProperty(i);
                    Historial h = new Historial();
                    h.setFecha(historial.getProperty("Fecha").toString().replace("T", " "));
                    h.setDescripcion(historial.getProperty("Descripcion").toString());
                    h.setCiudad(historial.getProperty("Ciudad").toString());
                    h.setEstado(historial.getProperty("Estado").toString());
                    h.setLatitud(historial.getProperty("Latitud").toString());
                    h.setLongitud(historial.getProperty("Longitud").toString());
                    listaHistorial.add(h);
                }


                SoapObject paquete = (SoapObject) body.getProperty("Paquete");
                Paquete p = new Paquete();
                p.setTamanio(paquete.getProperty("Tamanio").toString());

                Orden o = new Orden();
                o.setFecha(body.getProperty("Fecha").toString().replace("T", " "));
                o.setDestinatario(d);
                o.setHistoriales(listaHistorial);
                o.setPaquete(p);
                o.setNumeroOrden(body.getProperty("NumeroRastreo").toString());


                /*Bundle b = new Bundle();
                b.putSerializable("o", o);
                EnviosFragment e = new EnviosFragment();
                e.setArguments(b);*/
                Intent i = new Intent(RastrearEnvio.this, tbdInfoEnvio.class);
                i.putExtra("Orden", (Serializable) o);
                startActivity(i);

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
