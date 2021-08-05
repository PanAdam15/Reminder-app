package com.example.application_for_forgetful_people;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.databinding.ActivityMainBinding;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ACTIVITY_REQUEST_CODE = 1;
    private static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    Button addNewReminderButton;
    Button settingsButton;
    Button imageButton;
    private ReminderViewModel reminderViewModel;
    private ReminderListAdapter reminderListAdapter;
    private ActivityMainBinding binding;
    private List<Reminder> listOfReminders;
    private StatisticsViewModel statisticsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();

        startService(new Intent(this,BackgroundService.class));

        addNewReminderButton = findViewById(R.id.AddNewReminder);
        settingsButton = findViewById(R.id.settingsButton);
        imageButton = findViewById(R.id.imageButton);

        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        reminderListAdapter = new ReminderListAdapter(this,this::onItemClick);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(reminderListAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        reminderListAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(reminderListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

        reminderListAdapter.setReminderViewModel(reminderViewModel);
        statisticsViewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);

        statisticsViewModel.insert(new Statistics(80,true,"dsf","fds","das"));
        statisticsViewModel.insert(new Statistics(60,true,"dsf","fds","das"));
        statisticsViewModel.insert(new Statistics(70,false,"dsf","fds","das"));
        statisticsViewModel.insert(new Statistics(40,true,"dsf","fds","das"));
        statisticsViewModel.insert(new Statistics(50,true,"dsf","fds","das"));


        reminderViewModel.getAllReminders().observe(this, elements ->{
            reminderListAdapter.setListOfReminders(elements);
            listOfReminders = elements;

        });

        addNewReminderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewReminder.class);
            startActivityForResult(intent,ADD_ACTIVITY_REQUEST_CODE);
        });

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });


        final PopupMenu dropDownMenu = new PopupMenu(new ContextThemeWrapper(this, R.style.MenuTheme), imageButton);
        final Menu menu = dropDownMenu.getMenu();

        dropDownMenu.getMenuInflater().inflate(R.menu.menu, menu);
        try {
            Field field = dropDownMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menuPopupHelper = field.get(dropDownMenu);
            Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
            Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class});
            method.setAccessible(true);
            method.invoke(menuPopupHelper, new Object[]{true});
        } catch (Exception e) {
            e.printStackTrace();
        }

        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opt1:
                        Intent bluetoothSettingsActivity = new Intent();
                        bluetoothSettingsActivity.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                        startActivity(bluetoothSettingsActivity);
                        break;
                    case R.id.opt2:
                        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropDownMenu.show();
            }
        });
    }

    private void createNotificationChannel(){
        CharSequence name = "foxandroidReminderChannel";
        String description = "channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel("foxandroid",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent pack){
        super.onActivityResult(requestCode,resultCode,pack);

        if (requestCode == ADD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            if(pack!=null)
            {

                reminderViewModel.insert(new Reminder(  pack.getStringExtra("name"),
                                                        pack.getBooleanExtra("mon",false),
                                                        pack.getBooleanExtra("tue",false),
                                                        pack.getBooleanExtra("wed",false),
                                                        pack.getBooleanExtra("thu",false),
                                                        pack.getBooleanExtra("fri",false),
                                                        pack.getBooleanExtra("sat",false),
                                                        pack.getBooleanExtra("sun",false),
                                                        pack.getBooleanExtra("bt",false),
                                                        pack.getBooleanExtra("speaker",false),
                                                        pack.getBooleanExtra("active", true)));
                showToast("Dodano przypomnienie");
            }
        }else if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){

            if(pack!=null){

                Reminder reminder = new Reminder(   pack.getLongExtra("id",0),
                                                    pack.getStringExtra("name"),
                                                    pack.getBooleanExtra("mon",false),
                                                    pack.getBooleanExtra("tue",false),
                                                    pack.getBooleanExtra("wed",false),
                                                    pack.getBooleanExtra("thu",false),
                                                    pack.getBooleanExtra("fri",false),
                                                    pack.getBooleanExtra("sat",false),
                                                    pack.getBooleanExtra("sun",false),
                                                    pack.getBooleanExtra("bt",false),
                                                    pack.getBooleanExtra("speaker",false),
                                                    pack.getBooleanExtra("active",true));

                showToast("Zaktualizowano");
                reminderViewModel.update(reminder);
            }
            else showToast("UPDATE PACK IS NULL");
        }else
            showToast("Anulowano");
    }



    public void onItemClick(int position){
        Intent intent = new Intent(MainActivity.this, NewReminder.class);
        intent.putExtra("id",listOfReminders.get(position).getId());
        intent.putExtra("name",listOfReminders.get(position).getName());
        intent.putExtra("bt",listOfReminders.get(position).isIfBluetooth());
        intent.putExtra("speaker",listOfReminders.get(position).isIfRing());
        intent.putExtra("mon",listOfReminders.get(position).isIfMonday());
        intent.putExtra("tue",listOfReminders.get(position).isIfTuesday());
        intent.putExtra("wed",listOfReminders.get(position).isIfWednesday());
        intent.putExtra("thu",listOfReminders.get(position).isIfThursday());
        intent.putExtra("fri",listOfReminders.get(position).isIfFriday());
        intent.putExtra("sat",listOfReminders.get(position).isIfSaturday());
        intent.putExtra("sun",listOfReminders.get(position).isIfSunday());
        intent.putExtra("active",listOfReminders.get(position).isActive());
        startActivityForResult(intent,UPDATE_ACTIVITY_REQUEST_CODE);
    }


    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }


}
