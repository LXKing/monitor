package com.rayton.gps.dao.locate;

import java.util.ArrayList;
import java.util.List;

public class VehicleInfoDto {
    public String id;
    public VehicleBaseInfoDto baseInfo;
    public List<OwnerBaseInfoDto> owners = new ArrayList<OwnerBaseInfoDto>();
    public List<DriverBaseInfoDto> drivers = new ArrayList<DriverBaseInfoDto>();
}
