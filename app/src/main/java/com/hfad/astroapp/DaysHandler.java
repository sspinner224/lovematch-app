package com.hfad.astroapp;

import org.json.JSONException;
import org.json.JSONObject;

public class DaysHandler {
    private static final int START_DAY = 1;
    private static final int END_DAY= 31;
    //   public static final String YEAR_FACT = "YEAR_FACT";
    final public static String [] days = new String[END_DAY- START_DAY +1];;

    public DaysHandler(){

        //populate the array
        int i = 0;
        for (int day = START_DAY; day <= END_DAY; day++ )
            days[i++] = Integer.toString(day);
    }

    public String getBirthInfo(String birthInfoJsonStr) throws JSONException {
        JSONObject birthInfoJSONObj = new JSONObject(birthInfoJsonStr);
        return birthInfoJSONObj.getString("text");
    }
}
