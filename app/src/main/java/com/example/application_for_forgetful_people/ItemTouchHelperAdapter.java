package com.example.application_for_forgetful_people;

public interface ItemTouchHelperAdapter {

    void onItemSwiped(int position);
    void notifyItemChanged(int position);
}
