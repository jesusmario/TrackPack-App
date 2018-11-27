package integrador.itson.mx.trackpack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding3 extends AppCompatActivity {
    Animation smltobig, nothingtocome, btnanim;
    ImageView onBoardImg3;
    TextView subtituloOnboard3, tituloOnboard3;
    Button buttonob3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_onboarding3);

        smltobig = AnimationUtils.loadAnimation(this, R.anim.smltobig); //Animacion transicion de texto.
        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome); //Animacion de texto
        btnanim = AnimationUtils.loadAnimation(this, R.anim.btnanim); //Animacion del botón

        onBoardImg3 = (ImageView) findViewById(R.id.onboardImg3);
        subtituloOnboard3 = (TextView) findViewById(R.id.subtituloOnboard3);
        tituloOnboard3 = (TextView) findViewById(R.id.tituloOnboard3);
        buttonob3 = (Button) findViewById(R.id.buttonob3);


    }
    }


