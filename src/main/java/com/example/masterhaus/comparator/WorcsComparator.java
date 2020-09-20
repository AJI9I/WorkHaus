package com.example.masterhaus.comparator;

import com.example.masterhaus.domain.Worcs;

import java.util.Comparator;

public class WorcsComparator implements Comparator<Worcs> {
    @Override
    public int compare(Worcs o1, Worcs o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
