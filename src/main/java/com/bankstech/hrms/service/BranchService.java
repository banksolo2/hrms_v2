package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Branch;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

@Service
public class BranchService {

    private BranchRepository branchRepository;
    private long millis=System.currentTimeMillis();
    private Word w = new Word();

    @Autowired
    public BranchService(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    public ResponseMessage create(Branch branch){
        branch.setCode(w.getCode(branch.getName()));
        branch.setCreatedAt(new Date(millis));

        //check if name already exist
        boolean isNameExist = branchRepository.existByName(branch.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Branch name already exist");
        }
        else{
            Branch newB = branchRepository.save(branch);

            if(newB.getId() >= 1){
                return new ResponseMessage("success", "Branch has been created");
            }
            else{
                return new ResponseMessage("error","Enable to create branch, please contact the admin");
            }
        }
    }

    public List<Branch> all(){
        return branchRepository.findAll();
    }

    public ResponseMessage update(Branch branch){
        branch.setCode(w.getCode(branch.getName()));
        branch.setUpdatedAt(new Date(millis));

        //check if name already exist
        boolean isNameExist = branchRepository.existByNameOnUpdate(branch.getId(),branch.getName());
        if(isNameExist){
            return new ResponseMessage("error","Branch name already exist");
        }
        else{
            branchRepository.save(branch);
            return new ResponseMessage("success", "Branch has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        branchRepository.deleteById(id);
        return new ResponseMessage("success","Branch has been deleted");
    }

    public List<Branch> options(Integer id){
        return branchRepository.options(id);
    }
}
