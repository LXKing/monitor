package mmp.gps.logic.dao;

import mmp.gps.domain.trip.TripDto;

import java.util.Date;
import java.util.List;

public interface ITripDao {

    List<TripDto> load(String number, Date start, Date end);

}
