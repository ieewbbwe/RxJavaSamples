package com.webber.rxjavademos;

/**
 * Created by mxh on 2016/11/2.
 * Describe：
 */

public class Cause {
    private String name;
    private float grade;

    public Cause() {
    }

    public Cause(String name, float grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
