package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Level;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    private long mills = System.currentTimeMillis();
    private Word w = new Word();

    public ResponseMessage create(Level l){
        l.setCode(w.getCode(l.getName()));
        l.setCreatedAt(new Date(mills));

        //check if level name already exist
        boolean isNameExist = levelRepository.existByName(l.getName());
        if(isNameExist){
            return new ResponseMessage("error","Level name already exist");
        }
        else{
            Level l2 = levelRepository.save(l);
            if(l2.getId() >= 1){
                return new ResponseMessage("success", "Level has been created");
            }
            else{
                return new ResponseMessage("error", "Enable to create level, please contact the admin for assistant");
            }
        }
    }

    public List<Level> all(){
        return levelRepository.findAll();
    }

    public Level getLevel(Integer id){
        return levelRepository.getById(id);
    }

    public ResponseMessage update(Level l){
        l.setCode(w.getCode(l.getName()));
        l.setUpdatedAt(new Date(mills));

        //check if level already exist
        boolean isNameExist = levelRepository.existByNameOnUpdate(l.getId(), l.getName());
        if(isNameExist){
            return new ResponseMessage("error","Level name already exist");
        }
        else{
            levelRepository.save(l);
            return new ResponseMessage("success", "Level has been updated");
        }

    }

    public ResponseMessage delete(Integer id){
        levelRepository.deleteById(id);
        return new ResponseMessage("success", "Level has been deleted");
    }

    public List<Level> options(Integer id){
        return levelRepository.options(id);
    }
}
