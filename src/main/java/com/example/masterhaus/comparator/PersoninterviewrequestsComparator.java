package com.example.masterhaus.comparator;

import com.example.masterhaus.domain.Personinterviewrequest;

import java.util.Comparator;

public class PersoninterviewrequestsComparator implements Comparator<Personinterviewrequest> {
    @Override
    public int compare(Personinterviewrequest o1, Personinterviewrequest o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
