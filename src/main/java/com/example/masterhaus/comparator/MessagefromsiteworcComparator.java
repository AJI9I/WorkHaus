package com.example.masterhaus.comparator;

import com.example.masterhaus.domain.Messagefromsiteworc;

import java.util.Comparator;

public class MessagefromsiteworcComparator implements Comparator<Messagefromsiteworc> {
    @Override
    public int compare(Messagefromsiteworc o1, Messagefromsiteworc o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
