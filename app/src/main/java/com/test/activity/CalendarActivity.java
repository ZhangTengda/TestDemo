package com.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.test.R;
import com.test.utils.AppUtils;
import com.test.customview.calendarview.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by roger on 2018/1/21.
 */

public class CalendarActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_layout);


        Calendar calendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

        int currentMonth = calendar.get(Calendar.MONTH);
        if (currentMonth == 0) {
            endCalendar.set(calendar.get(Calendar.YEAR), 11, 32);
        } else {
            int daysInMonth = AppUtils.getDaysInMonth(calendar.get(Calendar.MONTH) - 1, calendar.get(Calendar.MONTH));
            endCalendar.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH) - 1, daysInMonth + 1);
        }


        CalendarPickerView calendarPickerView = (CalendarPickerView) findViewById(R.id.activity_calendar_calendarview);

        calendarPickerView.init(calendar.getTime(), endCalendar.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(new Date());

        // 这里没有的话，会null point
        ArrayList<Integer> list = new ArrayList<>();
        calendarPickerView.deactivateDates(list);
    }
}
