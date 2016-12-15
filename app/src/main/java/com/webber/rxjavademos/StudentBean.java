package com.webber.rxjavademos;

import java.util.List;

/**
 * Created by mxh on 2016/11/2.
 * Describe：
 */

public class StudentBean {
    private String unity;
    private String name;
    private List<Cause> causeList;
    private boolean isMale;

    public String getUnity() {
        return unity;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cause> getCauseList() {
        return causeList;
    }

    public void setCauseList(List<Cause> causeList) {
        this.causeList = causeList;
    }

    public Boolean isMale() {
        return isMale;
    }
}
