package integrador.itson.mx.trackpack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class IntroLogo extends AppCompatActivity {
    Button sub;
    Animation frombottom, fromtop;
    ImageView ballon;

    /*Animación de la intro al ejecutar la aplicación*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_logo);

        sub = (Button) findViewById(R.id.sub);
        ballon = (ImageView) findViewById(R.id.ballon);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        sub.setAnimation(frombottom);
        ballon.setAnimation(fromtop);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sub = new Intent(IntroLogo.this,OnBoarding.class);
                startActivity(sub);
            }
        });

    }
}



