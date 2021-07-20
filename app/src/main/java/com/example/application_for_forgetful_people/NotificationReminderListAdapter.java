package com.example.application_for_forgetful_people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.util.List;

public class NotificationReminderListAdapter extends RecyclerView.Adapter<NotificationReminderListAdapter.NotificationReminderViewHolder> {

    @NonNull
    LayoutInflater layoutInflater;

    private List<Reminder> listOfReminders;
    private ReminderViewModel reminderViewModel;
    int forRadioPosition;
    private StatisticViewModel statisticViewModel;

    public void setReminderViewModel(ReminderViewModel reminderViewModel) {
        this.reminderViewModel = reminderViewModel;
    }

    public NotificationReminderListAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
        this.listOfReminders = null;
    }

    public void setStatisticViewModel(StatisticViewModel statisticViewModel){
        this.statisticViewModel = statisticViewModel;
    }

    @NonNull
    @Override
    public NotificationReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_row_notification,null);
        return new NotificationReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationReminderViewHolder holder, int position) {
        holder.nameOfReminder.setText(listOfReminders.get(position).getName());
        setRadioPosition(position);
    }

    @Override
    public int getItemCount() {
        if(listOfReminders != null)
            return listOfReminders.size();
        return 0;
    }

    public class NotificationReminderViewHolder extends RecyclerView.ViewHolder {

        RadioGroup radioGroup;
        RadioButton radioButtonYes;
        RadioButton radioButtonNo;
        TextView nameOfReminder;

        public NotificationReminderViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfReminder = itemView.findViewById(R.id.nameOfReminder);
            radioGroup = itemView.findViewById(R.id.radioGroup);

            radioGroup.setOnCheckedChangeListener(((group, checkedId) -> {

                radioButtonYes = radioGroup.findViewById(R.id.radioButton);

                radioButtonNo = radioGroup.findViewById(R.id.radioButton2);

                if(radioButtonYes.isChecked()){
                    statisticViewModel.insert(new Statistics(0,true));
                }
                else if(radioButtonNo.isChecked()){
                    System.out.println("nietest");
                }

            }));
        }
    }

    public void setListOfReminders(List<Reminder> listOfReminders) {
        this.listOfReminders = listOfReminders;
        notifyDataSetChanged();
    }

    public int getForRadioPosiotion(){
        return forRadioPosition;
    }

    public void setRadioPosition(int position){
        this.forRadioPosition = position;
    }
}
