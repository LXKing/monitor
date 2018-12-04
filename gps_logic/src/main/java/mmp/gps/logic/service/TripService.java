package mmp.gps.logic.service;


import mmp.gps.domain.trip.LoadTripRequest;
import mmp.gps.domain.trip.LoadTripResponse;
import mmp.gps.domain.trip.Trip;
import mmp.gps.domain.trip.TripDto;
import mmp.gps.logic.dao.ITripDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private ITripDao tripDao;


    public LoadTripResponse load(LoadTripRequest request) {
        LoadTripResponse response = new LoadTripResponse();
        List list = this.tripDao.load(request.getNumber(), request.getStart(), request.getEnd());
        if (list != null && list.size() > 0) {
            ArrayList trips = new ArrayList(list.size());
            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                TripDto dto = (TripDto) var5.next();
                Trip trip = new Trip();
                trip.setAvgBv(dto.avgBv);
                trip.setAvgOil(dto.avgOil);
                trip.setAvgSpeed(dto.avgSpeed);
                trip.setBreaks(dto.breaks);
                trip.setEndTime(dto.endTime);
                trip.setFatigueTime(dto.fatigueTime);
                trip.setIdleTime(dto.idleTime);
                trip.setMaxEct(dto.maxEct);
                trip.setMaxRpm(dto.maxRpm);
                trip.setMaxSpeed(dto.maxSpeed);
                trip.setMileage(dto.mileage);
                trip.setNumber(dto.number);
                trip.setOverSpeed(dto.overSpeed);
                trip.setOverSpeedTime(dto.overSpeedTime);
                trip.setSpeedUp(dto.speedUp);
                trip.setStartTime(dto.startTime);
                trip.setTotalOil(dto.totalOil);
                trips.add(trip);
            }

            response.setTrips(trips);
            return response;
        } else {
            return response;
        }
    }
}
