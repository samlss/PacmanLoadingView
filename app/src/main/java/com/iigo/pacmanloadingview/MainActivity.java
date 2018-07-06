package com.iigo.pacmanloadingview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iigo.library.PacmanLoadingView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private PacmanLoadingView pacmanLoadingView1;
    private PacmanLoadingView pacmanLoadingView2;
    private PacmanLoadingView pacmanLoadingView3;

    private int[] RANDOM_COLOR_ARR = new int[]{
            Color.BLUE,
            Color.BLACK,
            Color.RED,
            Color.GREEN,
            Color.GRAY,
            Color.MAGENTA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pacmanLoadingView1 = findViewById(R.id.plv_loading_1);
        pacmanLoadingView2 = findViewById(R.id.plv_loading_2);
        pacmanLoadingView3 = findViewById(R.id.plv_loading_3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        pacmanLoadingView1.start();
        pacmanLoadingView2.start();
        pacmanLoadingView3.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()){
            pacmanLoadingView1.destroy();
            pacmanLoadingView2.destroy();
            pacmanLoadingView3.destroy();
        }else{
            pacmanLoadingView1.stop();
            pacmanLoadingView2.stop();
            pacmanLoadingView3.stop();
        }
    }

    public void onStopLoading(View view) {
        pacmanLoadingView1.stop();
        pacmanLoadingView2.stop();
        pacmanLoadingView3.stop();
    }

    public void onStartLoading(View view) {
        pacmanLoadingView1.start();
        pacmanLoadingView2.start();
        pacmanLoadingView3.start();
    }

    public void onChangeEaterColor(View view) {
        int color = getRandomColor();

        pacmanLoadingView1.setEaterColor(color);
        pacmanLoadingView2.setEaterColor(color);
        pacmanLoadingView3.setEaterColor(color);
    }


    public void onChangePeasColor(View view) {
        int color = getRandomColor();

        pacmanLoadingView1.setPeasColor(color);
        pacmanLoadingView2.setPeasColor(color);
        pacmanLoadingView3.setPeasColor(color);
    }

    private int getRandomColor(){
        return RANDOM_COLOR_ARR[new Random().nextInt(RANDOM_COLOR_ARR.length)];
    }
}
