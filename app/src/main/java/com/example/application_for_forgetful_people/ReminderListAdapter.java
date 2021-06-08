package com.example.application_for_forgetful_people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

public class ReminderListAdapter  extends RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder> {

    @NonNull
    LayoutInflater layoutInflater;

    private List<Reminder> listOfReminders;

    public ReminderListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.listOfReminders = null;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_row,null);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        holder.nameOfRemidner.setText(listOfReminders.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(listOfReminders != null)
            return listOfReminders.size();
        return 0;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfRemidner;

        public ReminderViewHolder(View view) {
            super(view);

            nameOfRemidner = view.findViewById(R.id.nameOfReminder);
        }
    }
    public void setListOfReminders(List<Reminder> listOfReminders) {
        this.listOfReminders = listOfReminders;
        notifyDataSetChanged();
    }

}
