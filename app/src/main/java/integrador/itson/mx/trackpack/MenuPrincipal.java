package integrador.itson.mx.trackpack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuPrincipal extends AppCompatActivity {
ImageView deli, estimar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        deli = (ImageView) findViewById(R.id.botDeli);
        estimar = (ImageView) findViewById(R.id.botEstimar);

        estimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostoEnvio costoEnvio = new CostoEnvio();

            }
        });

        deli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuPrincipal.this, RastrearEnvio.class));
            }
        });



    }
}
