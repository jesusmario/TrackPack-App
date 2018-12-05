package integrador.itson.mx.trackpack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding2 extends AppCompatActivity {
    Animation smltobig, nothingtocome, btnanim;
    ImageView onBoardImg2, skipOnBoarding;
    TextView subtituloOnboard2, tituloOnboard2;
    Button buttonob2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_onboarding2);

        smltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig); //Animacion transicion de texto.
        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome); //Animacion de texto
        btnanim = AnimationUtils.loadAnimation(this, R.anim.btnanim); //Animacion del botón

        onBoardImg2 = (ImageView) findViewById(R.id.onboardImg2);
        subtituloOnboard2 = (TextView) findViewById(R.id.subtituloOnboard2);
        tituloOnboard2 = (TextView) findViewById(R.id.tituloOnboard2);
        buttonob2 = (Button) findViewById(R.id.buttonob2);
        skipOnBoarding = (ImageView) findViewById(R.id.skipOnBoarding);

        skipOnBoarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipOnBoarding = new Intent(OnBoarding2.this, MenuPrincipal.class);
                startActivity(skipOnBoarding);
                finish();
            }
        });

        buttonob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttonob2 = new Intent(OnBoarding2.this, OnBoarding3.class);
                startActivity(buttonob2);
                finish();

            }
        });
    }
}
