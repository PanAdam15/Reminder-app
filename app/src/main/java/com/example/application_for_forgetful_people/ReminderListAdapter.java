package com.example.application_for_forgetful_people;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setReminderViewModel(ReminderViewModel reminderViewModel) {
        this.reminderViewModel = reminderViewModel;
    }

    public ReminderListAdapter(Context context, OnItemClickListener mOnItemClickListener) {
        layoutInflater = LayoutInflater.from(context);
        this.listOfReminders = null;
        this.mOnItemClickListener = mOnItemClickListener;
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
        }
        else{
            holder.bluetooth.setVisibility(View.INVISIBLE);}
        if(listOfReminders.get(position).isIfRing()){
                holder.speaker.setVisibility(View.VISIBLE);
        }
        else{
            holder.speaker.setVisibility(View.INVISIBLE);}

        if(!listOfReminders.get(position).isIfMonday())
            holder.mon.setBackgroundColor(525252);
        else
            holder.mon.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfTuesday())
            holder.tue.setBackgroundColor(525252);
        else
            holder.tue.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfWednesday())
            holder.wed.setBackgroundColor(525252);
        else
            holder.wed.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfThursday())
            holder.thu.setBackgroundColor(525252);
        else
            holder.thu.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfFriday())
            holder.fri.setBackgroundColor(525252);
        else
            holder.fri.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfSaturday())
            holder.sat.setBackgroundColor(525252);
        else
            holder.sat.setBackgroundResource(R.drawable.rounded_corner_title);

        if(!listOfReminders.get(position).isIfSunday())
            holder.sun.setBackgroundColor(525252);
        else
            holder.sun.setBackgroundResource(R.drawable.rounded_corner_title);
    }

    @Override
    public int getItemCount() {
        if(listOfReminders != null)
            return listOfReminders.size();
        return 0;
    }

    @Override
    public void onItemSwiped(int position) {
        AlertDialog diaBox = AskOption(position);
        diaBox.show();


    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder implements
                View.OnClickListener,
                GestureDetector.OnGestureListener {
        TextView nameOfRemidner, mon,tue,wed,thu,fri,sat,sun;
        ImageView bluetooth, speaker;
        GestureDetector mGestureDetector;
        Switch isActiveSwitch;

        public ReminderViewHolder(View view) {
            super(view);

            mGestureDetector = new GestureDetector(view.getContext(),this);

            nameOfRemidner = view.findViewById(R.id.nameOfReminder);
            bluetooth = view.findViewById(R.id.imageBT);
            speaker = view.findViewById(R.id.imageSpeaker);
            mon = view.findViewById(R.id.iconMon);
            tue = view.findViewById(R.id.iconTue);
            wed = view.findViewById(R.id.iconWed);
            thu = view.findViewById(R.id.iconThu);
            fri = view.findViewById(R.id.iconFri);
            sat = view.findViewById(R.id.iconSat);
            sun = view.findViewById(R.id.iconSun);

            isActiveSwitch = view.findViewById(R.id.isActiveSwitch);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
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

    private AlertDialog AskOption(int position)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(layoutInflater.getContext())
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
               // .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        reminderViewModel.deleteReminderById(listOfReminders.get(position).getId());
                        listOfReminders.remove(position);
                        notifyItemRemoved(position);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

}
