package integrador.itson.mx.trackpack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {
Animation smltobig, nothingtocome, btnanim;
ImageView onboardImg1, skipOnBoarding;
TextView subtituloOnboard1, tituloOnboard1;
Button buttonob1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_onboarding);

        smltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig); //Animacion transicion de texto.
        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome); //Animacion de texto
        btnanim = AnimationUtils.loadAnimation(this, R.anim.btnanim); //Animacion del botón

        onboardImg1 = (ImageView) findViewById(R.id.onboardImg1);
        subtituloOnboard1 = (TextView) findViewById(R.id.subtituloOnboard1);
       tituloOnboard1 = (TextView) findViewById(R.id.tituloOnboard1);
       buttonob1 = (Button) findViewById(R.id.buttonob1);
       skipOnBoarding = (ImageView) findViewById(R.id.skipOnBoarding);

        onboardImg1.startAnimation(smltobig);
        subtituloOnboard1.startAnimation(nothingtocome);
        tituloOnboard1.startAnimation(nothingtocome);
        buttonob1.startAnimation(btnanim);

        skipOnBoarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipOnBoarding = new Intent(OnBoarding.this, MenuPrincipal.class);
                startActivity(skipOnBoarding);
            }
        });

        buttonob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttonobl = new Intent(OnBoarding.this, OnBoarding2.class);
                startActivity(buttonobl);
            }
        });





    }
}
