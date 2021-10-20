package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.EmployeeStatus;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

@Service
public class EmployeeStatusService {

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;

    private long millis = System.currentTimeMillis();
    private Word w = new Word();

    public ResponseMessage create(EmployeeStatus es){
        es.setCode(w.getCode(es.getName()));
        es.setCreatedAt(new Date(millis));

        //check if name already exist
        boolean isNameExist = employeeStatusRepository.existByName(es.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Employee status name already exist");
        }
        else{
            EmployeeStatus es2 = employeeStatusRepository.save(es);
            if(es2.getId() >= 1){
                return new ResponseMessage("success", "Employee status has been created");
            }
            else{
                return new ResponseMessage("error", "Enable to create employee status, please contact the admin for assistant");
            }
        }
    }

    public ResponseMessage update(EmployeeStatus es){
        es.setCode(w.getCode(es.getName()));
        es.setUpdatedAt(new Date(millis));

        //check if name already exist
        boolean isNameExist = employeeStatusRepository.existByNameOnUpdate(es.getId(),es.getName());
        if(isNameExist){
            return new ResponseMessage("error","Employee status name already exist");
        }
        else{
            employeeStatusRepository.save(es);
            return new ResponseMessage("success","Employee status has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        employeeStatusRepository.deleteById(id);
        return new ResponseMessage("success", "Employee status has been deleted");
    }

    public List<EmployeeStatus> all(){
        return employeeStatusRepository.findAll();
    }

    public EmployeeStatus getById(Integer id){
        EmployeeStatus employeeStatus = employeeStatusRepository.getById(id);
        return employeeStatus;
    }

    public List<EmployeeStatus> options(Integer id){
        return employeeStatusRepository.options(id);
    }

}
