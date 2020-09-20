package com.example.masterhaus.comparator;

import com.example.masterhaus.domain.Persons;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class PersonsComparator implements Comparator<Persons> {

    @Override
    public int compare(Persons o1, Persons o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
