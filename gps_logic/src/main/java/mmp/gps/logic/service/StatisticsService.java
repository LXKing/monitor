package mmp.gps.logic.service;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.statistics.*;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.config.GodpGlobalConfiguration;
import mmp.gps.logic.dao.IStatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private IStatisticsDao statisticsDao;


    public HistoryOnlineOfflineStatisticsResponse historyOnlineOffline(HistoryOnlineOfflineStatisticsRequest request) {
        HistoryOnlineOfflineStatisticsResponse response = new HistoryOnlineOfflineStatisticsResponse();
        ArrayList numbers = new ArrayList();
        Iterator var4 = request.getNumbers().iterator();

        while (var4.hasNext()) {
            String number = (String) var4.next();
            if (this.statisticsDao.historyOnlineOffline(number, request.getStart(), request.getEnd())) {
                numbers.add(number);
            }
        }

        response.setNumbers(numbers);
        return response;
    }

    public CurrentOnlineOfflineStatisticsResponse currentOnlineOffline(CurrentOnlineOfflineStatisticsRequest request) {
        CurrentOnlineOfflineStatisticsResponse response = new CurrentOnlineOfflineStatisticsResponse();
        GodpGlobalConfiguration cnf = GodpGlobalConfiguration.current;
        ArrayList numbers = new ArrayList();
        Iterator var5 = request.getNumbers().iterator();

        while (var5.hasNext()) {
            String number = (String) var5.next();
            Device device = Devices.getCurrent().get(number);
            if (device != null && device.isOnline(cnf.getOfflineTimeout() * 1000) == 1) {
                numbers.add(number);
            }
        }

        response.setNumbers(numbers);
        return response;
    }

    public HistoryOnlineTimeStatisticsResponse historyOnlineTime(HistoryOnlineTimeStatisticsRequest request) {
        HistoryOnlineTimeStatisticsResponse response = new HistoryOnlineTimeStatisticsResponse();
        HashMap result = new HashMap();
        Iterator var4 = request.getNumbers().iterator();

        while (var4.hasNext()) {
            String number = (String) var4.next();
            Integer times = this.statisticsDao.historyOnlineTime(number, request.getStart(), request.getEnd());
            result.put(number, times);
        }

        response.setResult(result);
        return response;
    }

    public MileageOilCountResponse countMileageOil(MileageOilCountRequest request) {
        MileageOilCountResponse response = new MileageOilCountResponse();
        ArrayList results = new ArrayList();
        Iterator list = request.getNumbers().iterator();

        MileageOilCountResultDto dto;
        while (list.hasNext()) {
            String number = (String) list.next();
            dto = new MileageOilCountResultDto();
            dto.number = number;
            List result = this.statisticsDao.mileageOilRecords(number, request.getStart(), request.getEnd());
            double mileage = 0.0D;
            double mileageStart = -1.0D;
            double prevMileage = 0.0D;
            double mileageEnd = -1.0D;
            float oilmass = 0.0F;
            float prevOil = -1.0F;
            Iterator var18 = result.iterator();

            while (var18.hasNext()) {
                MileageOilDto record = (MileageOilDto) var18.next();
                if (mileageStart == -1.0D) {
                    mileageStart = record.mileage;
                    prevMileage = mileageStart;
                } else {
                    mileageEnd = record.mileage;
                    if (mileageEnd < prevMileage) {
                        mileage = prevMileage - mileageStart;
                        mileageStart = 0.0D;
                    }

                    prevMileage = mileageEnd;
                }

                if (prevOil == -1.0F) {
                    prevOil = record.oilmass;
                } else {
                    float currentOil = record.oilmass;
                    float oil = prevOil - currentOil;
                    if (oil > 0.0F) {
                        oilmass += oil;
                    }

                    prevOil = currentOil;
                }
            }

            dto.mileages = mileage;
            dto.mileages += mileageEnd - mileageStart;
            dto.oils = (double) oilmass;
            results.add(dto);
        }

        ArrayList list1 = new ArrayList();
        Iterator number1 = results.iterator();

        while (number1.hasNext()) {
            dto = (MileageOilCountResultDto) number1.next();
            MileageOilCountResult result1 = new MileageOilCountResult();
            result1.number = dto.number;
            result1.mileages = dto.mileages;
            result1.oils = dto.oils;
            list1.add(result1);
        }

        response.setResults(list1);
        return response;
    }
}
