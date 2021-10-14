package com.bankstech.hrms.service;

import com.bankstech.hrms.model.LeaveStatus;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.LeaveStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveStatusService {

    private LeaveStatusRepository leaveStatusRepository;

    @Autowired
    public LeaveStatusService(LeaveStatusRepository leaveStatusRepository){
        this.leaveStatusRepository = leaveStatusRepository;
    }

    public ResponseMessage create(LeaveStatus leaveStatus){
        leaveStatus.setCode(leaveStatus.getName().trim().toLowerCase().replace(" ", "_"));

        //Check if name already exist
        boolean isNameExist = leaveStatusRepository.existByName(leaveStatus.getName());
        if(isNameExist){
            return new ResponseMessage("error","Leave status name already exist");
        }
        else{
            LeaveStatus result = leaveStatusRepository.save(leaveStatus);
            if(result.getId() >= 1){
                return new ResponseMessage("success", "Leave status has been created");
            }
            else{
                return new ResponseMessage("error","Enable to create leave status, please contact the admin");
            }
        }
    }

    public List<LeaveStatus> getAll(){
        return leaveStatusRepository.findAll();
    }

    public ResponseMessage update(LeaveStatus leaveStatus){
        leaveStatus.setCode(leaveStatus.getName().toLowerCase().replace(" ", "_"));
        //check if name already exist
        boolean isNameExist = leaveStatusRepository.existByNameOnUpdate(leaveStatus.getId(), leaveStatus.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Leave status name already exist");
        }
        else{
            leaveStatusRepository.save(leaveStatus);
            return new ResponseMessage("success","Leave status has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        leaveStatusRepository.deleteById(id);
        return new ResponseMessage("success", "Leave status has been deleted");
    }

    public List<LeaveStatus> options(Integer id){
        List<LeaveStatus> options = leaveStatusRepository.leaveStatusOptions(id);
        return options;
    }

}
