package com.board.async.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private String id;
    private String name;
    private String major;
    private int mileage;
    private int score;
    private String univ;
    private long recenttime;
    private int gender;
    private String birthday;
    private int age;
    private String profileUri;
    private int ismarried;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public long getRecenttime() {
        return recenttime;
    }

    public void setRecenttime(long recenttime) {
        this.recenttime = recenttime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(String profileUri) {
        this.profileUri = profileUri;
    }

    public int getIsmarried() {
        return ismarried;
    }

    public void setIsmarried(int ismarried) {
        this.ismarried = ismarried;
    }

    @Override
    public String toString() {
        return getId() + " - "  + getMileage() + " - " + getProfileUri();
    }
}
