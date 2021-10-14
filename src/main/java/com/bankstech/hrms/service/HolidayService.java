package com.bankstech.hrms.service;

import com.bankstech.hrms.model.Holiday;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HolidayService {
    private HolidayRepository holidayRepository;
    private long millis=System.currentTimeMillis();

    @Autowired
    public HolidayService(HolidayRepository holidayRepository){
        this.holidayRepository = holidayRepository;
    }

    public ResponseMessage create(Holiday holiday){
        holiday.setCreatedAt(new Date(millis));

        //check dateAt
        boolean isDataAtExist = holidayRepository.existByDateAt(holiday.getDateAt());
        if(isDataAtExist){
            return new ResponseMessage("error", "Holiday's date has already been taking");
        }
        else{
            Holiday h = holidayRepository.save(holiday);
            if(h.getId() >= 1){
                return new ResponseMessage("success", "Holiday has been created");
            }
            else{
                return new ResponseMessage("error","Enable to create the holiday, please contact the admin for assistant");
            }
        }
    }

    public List<Holiday> all(){
        return holidayRepository.findAll();
    }

    public ResponseMessage update(Holiday holiday){
        holiday.setUpdatedAt(new Date(millis));

        //Check if date already exist
        boolean isDateAtExist = holidayRepository.existByDateAtOnUpdate(holiday.getId(),holiday.getDateAt());
        if(isDateAtExist){
            return new ResponseMessage("error", "Holiday's date has already been taking");
        }
        else{
            holidayRepository.save(holiday);
            return new ResponseMessage("success","The holiday has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        holidayRepository.deleteById(id);
        return new ResponseMessage("success", "The holiday has been deleted");
    }
}
