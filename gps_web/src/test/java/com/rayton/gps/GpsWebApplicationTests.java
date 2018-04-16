package com.rayton.gps;

import com.rayton.gps.configuration.AppConfig;
import com.rayton.gps.dao.baseinfo.MarkerFileInfo;
import com.rayton.gps.dao.icon.IconDao;
import com.rayton.gps.service.IconService;
import com.rayton.gps.service.UserService;
import com.rayton.gps.util.enums.MarkerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpsWebApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsWebApplicationTests.class);


    @Autowired
    private UserService userService;

    @Autowired
    private IconDao iconDao;

    @Autowired
    private IconService iconService;

    @Autowired
    private AppConfig appConfig;

    @Test
    public void contextLoads() throws RuntimeException {

        // LOGGER.warn(String.valueOf(userService.exist("5555555555")));
        // LOGGER.warn(appConfig.toString());
        // LOGGER.warn(String.valueOf(new Random().nextInt()));
        // LOGGER.warn(iconDao.get("999").toString());

        // throwsE();
        // throwsRun();
        // Assert.isTrue(false, "...Log isAnnotationPresent False");
        // LOGGER.warn(appConfig.toString());

        MarkerFileInfo markerFileInfo=new MarkerFileInfo();
        markerFileInfo.setType(MarkerType.CAR.getType());
        iconService.insert(markerFileInfo);

        LOGGER.warn(iconService.listByType(MarkerType.CAR.getType()).toString());



    }

    private void throwsRun() {
        throw new RuntimeException();
    }

    private void throwsE() {
        // throw new Exception();
    }
}
