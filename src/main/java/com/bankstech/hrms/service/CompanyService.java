package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Company;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CompanyService {
    private Word word = new Word();
    private long millis = System.currentTimeMillis();
    @Autowired
    private CompanyRepository companyRepository;

    public ResponseMessage create(Company c){
        c.setCode(word.getCode(c.getName()));
        c.setShortName(word.getFirstLetterInAWord(c.getName()));
        c.setCreatedAt(new Date(millis));

        boolean isNameExist = companyRepository.existByName(c.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Company name already exist");
        }
        else{
            c = companyRepository.save(c);
            if(c.getId() >= 1){
                return new ResponseMessage("success", "Company has been created");
            }
            else{
                return new ResponseMessage("error","Enable to create company, please contact the admin for assistant");
            }
        }
    }

    public ResponseMessage update(Company c){
        c.setCode(word.getCode(c.getName()));
        c.setShortName(word.getFirstLetterInAWord(c.getName()));
        c.setUpdatedAt(new Date(millis));

        boolean isNameExit = companyRepository.existByNameOnUpdate(c.getId(),c.getName());
        if(isNameExit){
            return new ResponseMessage("error","Company name already exist");
        }
        else{
            companyRepository.save(c);
            return new ResponseMessage("success", "Company has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        companyRepository.deleteById(id);
        return new ResponseMessage("success","Company has been deleted");
    }

    public List<Company> all(){
        return companyRepository.findAll();
    }

    public Company getById(Integer id){
        return companyRepository.getCompanyById(id);
    }

    public List<Company> options(Integer id){
        return companyRepository.options(id);
    }
}
