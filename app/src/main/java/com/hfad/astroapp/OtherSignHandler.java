package com.hfad.astroapp;

import org.json.JSONException;
import org.json.JSONObject;

public class OtherSignHandler {
    private static final String START_MONTH = "Aries";
    private static final String END_MONTH= "Pisces";

    final public static String [] signs = new String[12];

    public OtherSignHandler(){

        signs[0] = "Aries";
        signs[1] = "Taurus";
        signs[2] = "Gemini";
        signs[3] = "Cancer";
        signs[4] = "Leo";
        signs[5] = "Virgo";
        signs[6] = "Libra";
        signs[7] = "Scorpio";
        signs[8] = "Sagittarius";
        signs[9] = "Capricorn";
        signs[10] = "Aquarius";
        signs[11] = "Pisces";

    }

    public String getBirthInfo(String birthInfoJsonStr) throws JSONException {
        JSONObject birthInfoJSONObj = new JSONObject(birthInfoJsonStr);
        return birthInfoJSONObj.getString("text");
    }
}