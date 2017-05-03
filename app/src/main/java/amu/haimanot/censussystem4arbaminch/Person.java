package amu.haimanot.censussystem4arbaminch;

/**
 * Created by AlphaGeek on 4/4/2017.
 */

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
    private int age;
    private String region;
    private String zone;
    private String town;
    private String religion;
    private String placeOfBirth;
    private String durationOfResidence;
    private String martialStatus;
    private String orphanHood;
    private String previousResidence;
    public Person(String id,String firstName,String lastName,String middleName,String sex,int age){
        this.id=id;
        this.firstName=firstName;
        this.middleName=middleName;
        this.sex=sex;
        this.age=age;
    }
    public Person(){
        //default constructor
    }
    public void setId(String id){
        this.id=id;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setMiddleName(String middleName){
        this.middleName=middleName;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setRegion(String region){
        this.region=region;
    }
    public void setReligion(String religion){
        this.religion=religion;
    }
    public void setZone(String zone){
        this.zone=zone;
    }
    public void setTown(String town){
        this.town=town;
    }
    public void setPlaceOfBirth(String pob){
        this.placeOfBirth=pob;
    }
    public void setDurationOfResidence(String dor){
        this.durationOfResidence=dor;
    }
    public void setMartialStatus(String mstatus){
        this.martialStatus=mstatus;
    }
    public void setOrphanHood(String ohood){
        this.orphanHood=ohood;
    }
    public void setPreviousResidence(String previousResidence){
        this.previousResidence=previousResidence;
    }
    public String getId(){
        return this.id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getMiddleName(){
        return this.middleName;
    }
    public String getSex(){
        return this.sex;
    }
    public int getAge(){
        return  this.age;
    }
    public String getZone(){
        return this.zone;
    }
    public String getRegion(){
        return this.region;
    }
    public String getTown(){
        return this.town;
    }
    public String getReligion(){
        return this.religion;
    }
    public String getPlaceOfBirth(){
        return this.placeOfBirth;
    }
    public String getMartialStatus(){
        return this.martialStatus;
    }
    public String getDurationOfResidence(){
        return this.durationOfResidence;
    }
    public String getOrphanHood(){
        return this.orphanHood;
    }
    public String getPreviousResidence(){
        return this.previousResidence;
    }
    public String getUsername(){
        return "not implemented yet";
    }
}
