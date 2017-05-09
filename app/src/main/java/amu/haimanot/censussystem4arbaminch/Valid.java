package amu.haimanot.censussystem4arbaminch;

import android.app.AlertDialog;
import android.content.Context;

public class Valid {
    static String userType;
    public static boolean isValidAge(String input){
        int age;
        try{
            age=Integer.parseInt(input);

        }catch(Exception e){
            return false;
        }
        if (age<1 || age >120 )
            return false;
        return true;
    }
    public static boolean isValidName(String input){
        if(input==null || input.isEmpty() || input.contains("-"))
            return false;
        return true;
    }
    public static void showDialog(Context context,String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
