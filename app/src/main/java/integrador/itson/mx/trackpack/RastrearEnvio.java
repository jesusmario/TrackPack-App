package integrador.itson.mx.trackpack;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                Intent btnRastrear = new Intent(RastrearEnvio.this, tbdInfoEnvio.class);
                startActivity(btnRastrear);
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
            String URL = "https://www.itrackpack.com/WSTrackPack.asmx";

            try{
                // Peticion o llamada al servicio web
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                request.addProperty("Celsius", "14");

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                //Comentario

                soapEnvelope.setOutputSoapObject(request);

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call(SOAP_ACTION, soapEnvelope);

                //Respuesta
                Object _objeto = (Object) soapEnvelope.getResponse();

                _mensajeRespuesta = String.valueOf("Celsius: 14c - > Fahrenheit: " + _objeto.toString() + "f");

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getApplicationContext(), _mensajeRespuesta, Toast.LENGTH_LONG).show();

        }
    }
}
