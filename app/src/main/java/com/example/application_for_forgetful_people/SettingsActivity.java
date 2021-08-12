package com.example.application_for_forgetful_people;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
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
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SettingsActivity extends AppCompatActivity {

    private TextView mTextView;
    private Switch colorSwitch;
    private Button advancedStatistics;
    private StatisticsViewModel statisticsViewModel;
    private static List<Statistics> listOfStatistics;
    LinkedHashMap<Integer, Integer> sevenLastDaysWithAmountOfForgottenActivities;
    private int countOfForgotten;
    private TextView btNameTextView;
    private TextView show1;
    private TextView show2;
    Statistics s;
    ArrayList<Calendar> mainDaysOfWeek;
    Button btRefreshButton;
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
        checkConnected();
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Opcje i statystyki");

        mTextView = findViewById(R.id.text);
        colorSwitch = findViewById(R.id.colorSwitch);

        btNameTextView = findViewById(R.id.btNameTextView);
        advancedStatistics = findViewById(R.id.statsButton);
        btRefreshButton = findViewById(R.id.btRefreshButton);

        mainDaysOfWeek = calculatePastSevenDaysOfWeek();
        try {
            sevenLastDaysWithAmountOfForgottenActivities = getSevenDaysOfStatistics(listOfStatistics, mainDaysOfWeek); // rozwiąza problem NPE - prawdopodobnie bład w metodzie
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date d1 = mainDaysOfWeek.get(4).getTime();
        Date d2 = mainDaysOfWeek.get(0).getTime();
        int maxValueOfForgottenActions = getMaxValueFromLastFiveDays(sevenLastDaysWithAmountOfForgottenActivities);

        final GraphView graph = findViewById(R.id.graph);
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        graph.setVisibility(View.VISIBLE);
        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]
                    {
                            new DataPoint(mainDaysOfWeek.get(4).getTime(),sevenLastDaysWithAmountOfForgottenActivities.get(4)),
                            new DataPoint(mainDaysOfWeek.get(3).getTime(),sevenLastDaysWithAmountOfForgottenActivities.get(3)),
                            new DataPoint(mainDaysOfWeek.get(2).getTime(),sevenLastDaysWithAmountOfForgottenActivities.get(2)),
                            new DataPoint(mainDaysOfWeek.get(1).getTime(),sevenLastDaysWithAmountOfForgottenActivities.get(1)),
                            new DataPoint(mainDaysOfWeek.get(0).getTime(),sevenLastDaysWithAmountOfForgottenActivities.get(0)),

                    });

            graph.addSeries(series);


            series.setAnimated(true);
            graph.getViewport().setMinX(d1.getTime());
            graph.getViewport().setMaxX(d2.getTime());
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(SettingsActivity.this));
            graph.getGridLabelRenderer().setNumHorizontalLabels(5);
            graph.getGridLabelRenderer().setHumanRounding(false);
            series.setDrawDataPoints(true);
            series.setDrawBackground(true);
            graph.setTitle("Wykres zapomnianych czynności");
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Daty");


            int maxValue = maxValueOfForgottenActions;
            int interval;
            if(maxValue <=10)
                interval = 1;
            else if (maxValue <= 55) {
                interval = 5; // increment of 5 between each label
            } else if (maxValue <= 110) {
                interval = 10; // increment of 10 between each label
            } else {
                interval = 20; // increment of 20 between each label
            }
            // search the top value of your graph, it must be a multiplier of your interval
            int maxLabel = maxValue;
            while (maxLabel % interval != 0) {
                maxLabel++;
            }
            graph.getGridLabelRenderer().setNumVerticalLabels(maxLabel / interval + 1);


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
        btRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnected();
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

    private LinkedHashMap<Integer, Integer> getSevenDaysOfStatistics(List<Statistics> listOfStatistics, ArrayList<Calendar> daysOfWeek) throws ParseException {
        ArrayList<Calendar> datesOfStatistics = new ArrayList<>();
        LinkedHashMap<Integer, Integer> sevenLastDaysWithAmountOfForgottenActivities = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyy", Locale.ENGLISH);

        Calendar calOfToday = Calendar.getInstance();
        Calendar calOfSevenDaysBefore = Calendar.getInstance();
        int amountOfForgot;
        listOfStatistics.forEach(s -> {
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(Objects.requireNonNull(sdf.parse(s.getDayOfForgettingActivity() + " " + s.getMonthOfForgettingActivity() + " " + s.getYearOfForgettingActivity())));
                datesOfStatistics.add(cal);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        calOfToday.add(Calendar.DATE,-1);
        calOfSevenDaysBefore.add(Calendar.DATE, -7);

        daysOfWeek=calculatePastSevenDaysOfWeek();

        for (int i = 0; i < daysOfWeek.size(); i++) {
            amountOfForgot = 0;
            for (int j = 0; j < datesOfStatistics.size(); j++) {
                Date date1 = sdf.parse(sdf.format(daysOfWeek.get(i).getTime()));
                Date date2 = sdf.parse(sdf.format(datesOfStatistics.get(j).getTime()));
                boolean b = listOfStatistics.get(j).isWasForgotten();
                if((date1.equals(date2) ) && b){
                    amountOfForgot++;
                }
            }
            sevenLastDaysWithAmountOfForgottenActivities.put(i,amountOfForgot);
        }

        return sevenLastDaysWithAmountOfForgottenActivities;

    }

    private ArrayList<Calendar> calculatePastSevenDaysOfWeek(){
        ArrayList<Calendar> days = new ArrayList<>();

        for (int i = 0; i >= -6; i--) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i);
            days.add(cal);
        }
        return days;
    }

    private int getMaxValueFromLastFiveDays(LinkedHashMap<Integer, Integer> sevenLastDaysWithAmountOfForgottenActivities){
        int max = 0;
        for (int i = 0; i < 5; i++) {
            if(max < sevenLastDaysWithAmountOfForgottenActivities.get(i))
                max = sevenLastDaysWithAmountOfForgottenActivities.get(i);
        }
        return max;
    }
    String name;
    public void checkConnected()
    {
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(this, serviceListener, BluetoothProfile.HEADSET);
    }
    private BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener()
    {
        @Override
        public void onServiceDisconnected(int profile)
        {

        }
        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy)
        {
            for (BluetoothDevice device : proxy.getConnectedDevices())
            {
                name = device.getName();
                btNameTextView.setText(name);
            }
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(profile, proxy);
        }
    };
}