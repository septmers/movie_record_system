package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Record;

public class RecordValidator {

    public static List<String> validate(Record report){
        List<String>errors = new ArrayList<String>();

        String title_error = validateTitle(report.getTitle());
        if(! title_error.equals("")){
            errors.add(title_error);
        }

        String impression_error = validateImpression(report.getImpression());
        if(! impression_error.equals("")){
            errors.add(impression_error);
        }

        String mylife_error = validateMylife(report.getMylife());
        if(! mylife_error.equals("")){
            errors.add(mylife_error);
        }
        return errors;

    }

    private static String validateTitle(String title){
        if(title == null || title.equals("")){
            return "タイトルを入力してください。";
        }
        return "";
    }

    private static String validateImpression(String impression){
        if(impression == null || impression.equals("")){
            return "感想を入力してください。";
        }
        return "";
    }

    private static String validateMylife(String mylife){
        if(mylife == null || mylife.equals("")){
            return "人生録を入力してください。";
        }
        return "";
    }

}
