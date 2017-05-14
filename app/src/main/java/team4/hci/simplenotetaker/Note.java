package team4.hci.simplenotetaker;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by dejan on 13.05.2017.
 */

public class Note implements Serializable{

    private long mDateTime;
    private String mTitle;
    private String mContent;

    public Note(long dateTime, String title, String content){
        mContent = content;
        mTitle = title;
        mDateTime = dateTime;
    }

    // Functions for setting the Data

    public void setDateTime (long dateTime) {
        mDateTime = dateTime;
    }

    public void setTitle (String title) {
        mTitle = title;
    }

    public void setContent (String content) {
        mContent = content;
    }
    // Functions for getting the Data

    public long getDateTime () {
        return mDateTime;
    }

    public String getTitle () {
        return mTitle;
    }

    public String getContent () {
        return mContent;
    }

    // Getting the formatted Date in "dd/MM/yyyy HH:mm:ss"

    public String getFormattedDate (Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);

        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(mDateTime));
    }
}
