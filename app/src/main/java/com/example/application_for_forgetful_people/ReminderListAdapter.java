package com.example.application_for_forgetful_people;

import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

public class ReminderListAdapter  extends RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder> implements ItemTouchHelperAdapter{

    @NonNull
    LayoutInflater layoutInflater;

    private List<Reminder> listOfReminders;
    private ItemTouchHelper itemTouchHelper;

    public void setReminderViewModel(ReminderViewModel reminderViewModel) {
        this.reminderViewModel = reminderViewModel;
    }

    private ReminderViewModel reminderViewModel;
    private Reminder mReminder;

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

    @Override
    public void onItemSwiped(int position) {
        listOfReminders.remove(position);
        notifyItemRemoved(position);
        reminderViewModel.deleteReminderById(listOfReminders.get(position).getId()-1);
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder implements
                View.OnClickListener,
                GestureDetector.OnGestureListener {
        TextView nameOfRemidner;
        GestureDetector mGestureDetector;

        public ReminderViewHolder(View view) {
            super(view);

            mGestureDetector = new GestureDetector(view.getContext(),this);

            nameOfRemidner = view.findViewById(R.id.nameOfReminder);
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
