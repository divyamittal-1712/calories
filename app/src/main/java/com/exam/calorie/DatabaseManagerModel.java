package com.exam.calorie;

public class DatabaseManagerModel {

    String name;
    String height;
    String age,sex,weight,mail;


    public DatabaseManagerModel(String s, String toString, String string, String s1, String toString1) {

    }

    public DatabaseManagerModel(String name, String height,String weight, String age, String sex,  String mail) {
        this.name = name;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
