package com.example.application_for_forgetful_people.adapters;

public interface ItemTouchHelperAdapter {

    void onItemSwiped(int position);
    void notifyItemChanged(int position);
}
