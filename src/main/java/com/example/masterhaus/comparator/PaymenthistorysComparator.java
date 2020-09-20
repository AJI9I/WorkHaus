package com.example.masterhaus.comparator;

import com.example.masterhaus.domain.Paymenthistorys;

import java.util.Comparator;

public class PaymenthistorysComparator implements Comparator<Paymenthistorys> {

    @Override
    public int compare(Paymenthistorys o1, Paymenthistorys o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
