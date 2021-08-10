package com.example.application_for_forgetful_people;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private TextView mTextView;
    private Switch colorSwitch;
    private Button advancedStatistics;
    private StatisticsViewModel statisticsViewModel;
    private static List<Statistics> listOfStatistics;
    private int countOfForgotten;
    private TextView nameEditText;
    private TextView show1;
    private TextView show2;
    Statistics s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                statisticsViewModel = new ViewModelProvider(SettingsActivity.this).get(StatisticsViewModel.class);
                countOfForgotten = statisticsViewModel.getForgottenCount();
            }
        });
        t.start();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Opcje i statystyki");

        mTextView = findViewById(R.id.text);
        colorSwitch = findViewById(R.id.colorSwitch);

        nameEditText = findViewById(R.id.nameEditText);
        advancedStatistics = findViewById(R.id.statsButton);

        nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    System.out.println("dsa"); // tu edycja db
            }
        });



        final GraphView graph = findViewById(R.id.graph);
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        graph.setVisibility(View.VISIBLE);
        try {
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]
                    {

                            new DataPoint(listOfStatistics.get(1).getId(),listOfStatistics.get(1).getId()),
                            new DataPoint(2,1),
                            new DataPoint(3,5),
                            new DataPoint(4,3),
                            new DataPoint(5,4),
                            new DataPoint(6,1),
                            new DataPoint(7,5)
                    });

            series.setSpacing(60);
            series.setAnimated(true);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(8);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(8);
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    int x,y,z;
                    if(data.getX() % 2 ==0) {
                        x=0;
                        y=0;
                        z=255;
                    }
                    else {
                        x=255;
                        y=0;
                        z=0;
                    }
                    return Color.rgb(x, y,z );
                }
            });
            graph.addSeries(series);

        } catch (IllegalArgumentException e) {

            Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            colorSwitch.setChecked(true);

        }
        else if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO){
            colorSwitch.setChecked(false);

        }

        colorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(colorSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }

        });


        advancedStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                show1 = popupWindow.getContentView().findViewById(R.id.allShow);
                show2 = popupWindow.getContentView().findViewById(R.id.forgottenShow);
                show1.setText(String.valueOf(countOfForgotten));
                show2.setText("pienć");

                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
    });
    }
    @Override // back button in nav bar
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
    private void showToast(String text) {
        Toast.makeText(SettingsActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public static void setListOfStatistics(List<Statistics> listOfStatistics) {
        SettingsActivity.listOfStatistics = listOfStatistics;
    }
}