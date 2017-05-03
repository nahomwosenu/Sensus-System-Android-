package amu.haimanot.censussystem4arbaminch;

public class Valid {
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
}
