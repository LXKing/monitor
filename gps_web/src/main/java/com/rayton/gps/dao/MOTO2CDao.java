package com.rayton.gps.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface MOTO2CDao {

    int add(String MOTORCADEID, String CARID);
}
