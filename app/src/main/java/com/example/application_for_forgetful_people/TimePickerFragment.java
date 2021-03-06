package com.example.application_for_forgetful_people;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)
                getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Anuluj", timePickerDialog);
        return timePickerDialog;
    }

}
