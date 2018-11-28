package integrador.itson.mx.trackpack;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RastrearEnvio extends AppCompatActivity {
    Button btnRegresar, btnRastrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rastrear_envio);

        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnRastrear = (Button) findViewById(R.id.btnRastrear);

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
}
