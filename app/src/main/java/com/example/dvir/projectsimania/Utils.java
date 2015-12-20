package com.example.dvir.projectsimania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dvir on 12/11/2015.
 */
public class Utils {
    public final static Map<String, Integer> MASECHTOT = new HashMap<String, Integer>() {{
        put("ברכות", 63);
        put("שבת", 156);
        put("עירובין", 104);
        put("פסחים", 120);
        put("ראש-השנה", 34);
        put("יומא", 87);
        put("סוכה", 55);
        put("ביצה", 39);
        put("תענית", 30);
        put("מגילה", 31);
        put("מועד-קטן", 28);
        put("חגיגה", 26);
        put("יבמורת", 121);
        put("כתובות", 111);
        put("נדרים", 90);
        put("נזיר", 65);
        put("סוטה", 48);
        put("גיטין", 89);
        put("קידושין", 81);
        put("בבא-קמא", 118);
        put("בבא-מציעא", 118);
        put("בבא-בתרא", 176);
        put("סנהדרין", 112);

        put("מכות", 23);

        put("שבועות", 48);
        put("עבודה-זרה", 75);
        put("הוריות", 12);
        put("זבחים", 119);
        put("מנחות", 109);
        put("חולין", 141);
        put("בכורות", 60);
        put("ערכין", 33);
        put("תמורה", 33);
        put("כריתות", 27);
        put("מעילה", 21);
        put("תמיד", 8);
        put("נידה", 72);
    }};

    public static ArrayList<Integer> getSheetList(String masechet) {
        ArrayList<Integer> sheetList = new ArrayList<>();
        for (int p = 1; p <= MASECHTOT.get(masechet); p++) {
            sheetList.add(p + 1);
        }
        return sheetList;
    }

//    public ArrayAdapter<Integer> getSheetAdaper(String masechet){
//        ArrayList<Integer> sheetList = getSheetList(masechet);
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,);
//    }

}
