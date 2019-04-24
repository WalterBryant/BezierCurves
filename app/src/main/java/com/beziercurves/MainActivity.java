package com.beziercurves;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beziercurves.basic.CurveView;
import com.beziercurves.basic.PathFillView;
import com.beziercurves.basic.PathOpView;
import com.beziercurves.dragbubble.DragBubbleView;
import com.beziercurves.loading.LoadingView;
import com.beziercurves.loading.LoadingView1;
import com.beziercurves.measure.MyView;
import com.beziercurves.search.SearchView;
import com.beziercurves.wave.WaveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setContentView(new PathFillView(this));
//        setContentView(new PathOpView(this));
//        setContentView(new CurveView(this));

        /*final DragBubbleView dragBubbleView = findViewById(R.id.drag_bubble_view);
        findViewById(R.id.reset_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragBubbleView.reset();
            }
        });*/

//        setContentView(new MyView(this));

//        setContentView(new LoadingView1(this));
//        setContentView(new LoadingView(this));


        /*WaveView waveView = new WaveView(this);
        setContentView(waveView);
        waveView.startAnimation();*/

        SearchView searchView = new SearchView(this);
        setContentView(searchView);
        searchView.setState(SearchView.SearchState.START);

    }
}
