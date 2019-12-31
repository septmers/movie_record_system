package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;


public class UserValidator {

    public static List<String> validate(User u, Boolean user_name_duplicate_check_flag, Boolean password_chack_flag, String birthday){
        List<String> errors = new ArrayList<String>();

        String user_name_error = _validateUser_name(u.getUser_name(), user_name_duplicate_check_flag);
        if(!user_name_error.equals("")){
            errors.add(user_name_error);
        }

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")){
            errors.add(name_error);
        }

        String password_error = _validatePassword(u.getPassword(), password_chack_flag);
        if(!password_error.equals("")){
            errors.add(password_error);
        }

        String birthday_error = _validateBirthday(birthday);
        if(!birthday_error.equals("")){
            errors.add(birthday_error);
        }

        return errors;

    }


    //ユーザー名
    private static String _validateUser_name(String user_name, Boolean user_name_duplicate_check_flag){
        //必須入力チェック
        if(user_name == null || user_name.equals("")){
            return "ユーザー名を入力してください。";
        }

        //既に登録されているユーザー名との重複チェック
        if(user_name_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredUser_name", Long.class)
                                        .setParameter("user_name", user_name)
                                        .getSingleResult();
            em.close();
            if(users_count > 0){
                return "入力されたユーザ名はすでに存在しています。";
            }

        }

        return "";
    }

    //名前の必須入力チェック
    private static String _validateName(String name){
        if(name == null || name.equals("")){
            return "氏名を入力してください。";
        }
        return "";
    }

    //パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag){
        //パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))){
            return "パスワードを入力してください。";
        }
        return "";
    }

    //生年月日の必須入力チェック
    private static String _validateBirthday(String birthday){
        if(birthday == null || birthday.equals("")){
            return "生年月日を入力してください。";
        }
        try{
            Date.valueOf(birthday);
        } catch(IllegalArgumentException e) {
            return "生年月日を正しい形式で入力してください。";
        }
        return "";
    }
}
