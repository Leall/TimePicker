package com.example.timepicker;

import java.util.Date;
/**
 *  @author xuchen
 *  @time 2017/6/15  15:41
 *  @describe
 */
public interface TimePickerListener {

    void onGetDate(Date date);
    void onCancel();

}
