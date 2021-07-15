package com.example.application_for_forgetful_people;

import android.content.Context;
import android.view.*;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

public class ReminderListAdapter  extends RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder>
        implements ItemTouchHelperAdapter{

    @NonNull
    LayoutInflater layoutInflater;

    private List<Reminder> listOfReminders;
    private ItemTouchHelper itemTouchHelper;
    private ReminderViewModel reminderViewModel;
    private Reminder mReminder;


    public void setReminderViewModel(ReminderViewModel reminderViewModel) {
        this.reminderViewModel = reminderViewModel;
    }

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
        holder.isActiveSwitch.setChecked(listOfReminders.get(position).isActive());
        if(listOfReminders.get(position).isIfBluetooth()){
                holder.bluetooth.setVisibility(View.VISIBLE);
        }else{
            holder.bluetooth.setVisibility(View.INVISIBLE);}
        if(listOfReminders.get(position).isIfRing()){
                holder.speaker.setVisibility(View.VISIBLE);
        }else{
            holder.speaker.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        if(listOfReminders != null)
            return listOfReminders.size();
        return 0;
    }

    @Override
    public void onItemSwiped(int position) {
        reminderViewModel.deleteReminderById(listOfReminders.get(position).getId());
        listOfReminders.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder implements
                View.OnClickListener,
                GestureDetector.OnGestureListener {
        TextView nameOfRemidner;
        ImageView bluetooth, speaker;
        GestureDetector mGestureDetector;
        Switch isActiveSwitch;

        public ReminderViewHolder(View view) {
            super(view);

            mGestureDetector = new GestureDetector(view.getContext(),this);

            nameOfRemidner = view.findViewById(R.id.nameOfReminder);
            bluetooth = view.findViewById(R.id.imageBT);
            speaker = view.findViewById(R.id.imageSpeaker);

            isActiveSwitch = view.findViewById(R.id.isActiveSwitch);

            isActiveSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    reminderViewModel.updateIsActiveStatus(isChecked,listOfReminders.get(getAdapterPosition()).getId());
                }
            });
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public void onClick(View v) {

        }
    }
    public void setListOfReminders(List<Reminder> listOfReminders) {
        this.listOfReminders = listOfReminders;
        notifyDataSetChanged();
    }

}
