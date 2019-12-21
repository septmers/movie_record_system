package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class AgeUtil {

    public int calcAge(Date birthday, Date now){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return (Integer.parseInt(sdf.format(now)) - Integer.parseInt(sdf.format(birthday))) / 10000;
    }
}
