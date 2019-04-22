package com.beziercurves;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beziercurves.dragbubble.DragBubbleView;
import com.beziercurves.measure.MyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setContentView(new PathFillView(this));
//        setContentView(new PathOpView(this));
//          setContentView(new CurveView(this));

        /*final DragBubbleView dragBubbleView = findViewById(R.id.drag_bubble_view);
        findViewById(R.id.reset_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                dragBubbleView.reset();
            }
        });*/

        setContentView(new MyView(this));
    }
}
