package com.rayton.gps;

import com.rayton.gps.configuration.AppConfig;
import com.rayton.gps.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpsWebApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsWebApplicationTests.class);


    @Autowired
    private UserService userService;

    @Autowired
    private AppConfig appConfig;

    @Test
    public void contextLoads() {

        LOGGER.warn(String.valueOf(userService.exist("5555555555")));
        LOGGER.warn(appConfig.toString());
        // Assert.isTrue(false, "...Log isAnnotationPresent False");
        LOGGER.warn(appConfig.toString());

    }

}
