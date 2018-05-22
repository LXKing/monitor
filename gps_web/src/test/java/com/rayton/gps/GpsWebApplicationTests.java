package com.rayton.gps;

import com.rayton.gps.configuration.AppConfig;
import com.rayton.gps.dao.baseinfo.driver.IDriverDao;
import com.rayton.gps.dao.baseinfo.vehicle.IVehicleDao;
import com.rayton.gps.dao.icon.IconDao;
import com.rayton.gps.service.IconService;
import com.rayton.gps.service.UserService;
import com.rayton.gps.service.lisence.DrivingLisenceService;
import com.rayton.gps.util.Assist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpsWebApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsWebApplicationTests.class);

    @Autowired
    private IDriverDao driverDao;


    @Autowired
    private IVehicleDao vehicleDao;

    @Autowired
    private DrivingLisenceService drivingLisenceService;

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
        Assist assist = new Assist();
        assist.setRowSize(10);
        assist.setStartRow(0);
        assist.setRequires(Assist.andEq("company_id", "558ffc6603c70e31a2a53a30"));
        System.out.println(drivingLisenceService.selectDrivingLisence(assist));

        // LOGGER.warn(String.valueOf(userService.exist("5555555555")));
        // LOGGER.warn(appConfig.toString());
        // LOGGER.warn(String.valueOf(new Random().nextInt()));
        // LOGGER.warn(iconDao.get("999").toString());

        // throwsRun();
        // Assert.isTrue(false, "...Log isAnnotationPresent False");
        // LOGGER.warn(appConfig.toString());

        // MarkerFileInfo markerFileInfo=new MarkerFileInfo();
        // markerFileInfo.setType(MarkerType.CAR.getType());
        // iconService.insert(markerFileInfo);

        // DrivingLisence drivingLisence = new DrivingLisence();
        // drivingLisence.setId("666777");
        // drivingLisence.setAddress("dddd");
        // drivingLisenceService.insertDrivingLisence(drivingLisence);
        // Assist assist = new Assist();
        //
        // List<DrivingLisence> a= drivingLisenceService.selectDrivingLisence(assist);
        // DrivingLisence drivingLisence3 = drivingLisenceService.selectDrivingLisenceById("111");
        //
        // LOGGER.warn(a.toString());
        // LOGGER.warn(drivingLisence3.getId());
        // LOGGER.warn(iconService.listByType(MarkerType.CAR.getType()).toString());

        // LOGGER.warn("" + vehicleDao.queryPageDetail("558ffc6603c70e31a2a53a30", "",1,1));
    }

    private void throwsRun() {
        throw new RuntimeException();
    }

    private void throwsE() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {

    }
}
