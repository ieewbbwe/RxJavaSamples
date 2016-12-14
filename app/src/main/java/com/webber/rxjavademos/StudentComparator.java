package com.webber.rxjavademos;

import java.util.Comparator;

/**
 * Created by mxh on 2016/11/4.
 * Describe：
 */

public class StudentComparator implements Comparator<String> {

    @Override
    public int compare(String lhs, String rhs) {
        return lhs.compareTo(rhs);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
