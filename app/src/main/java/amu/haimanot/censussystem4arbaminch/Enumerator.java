package amu.haimanot.censussystem4arbaminch;

/**
 * Created by AlphaGeek on 3/23/2017.
 */

public class Enumerator {
    private CharSequence firstName;
    private CharSequence lastName;
    private CharSequence username;
    private CharSequence password;
    private CharSequence sex;
    private int age;
    public void setAge(int age){
        //validation
        this.age=age;

    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public CharSequence getFirstName(){
        return this.firstName;
    }
    public CharSequence getLastName(){
        return this.lastName;
    }
    public CharSequence getSex(){
        return this.sex;
    }
    public int getAge(){
        return this.age;
    }
    public CharSequence getUsername(){
        return this.username;
    }
    public CharSequence getPassword(){
        return this.password;
    }
}
