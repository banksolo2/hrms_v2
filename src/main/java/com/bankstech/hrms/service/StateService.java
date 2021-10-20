package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.model.State;
import com.bankstech.hrms.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    private Word w = new Word();

    public ResponseMessage create(State state){
        state.setCode(w.getCode(state.getName()));
        //check if name already exist
        boolean isNameExist = stateRepository.isExistByName(state.getName());
        if(isNameExist) {
            return new ResponseMessage("error", "State name already exist");
        }
        else{
            State saveState = stateRepository.save(state);

            if( saveState.getId() >= 1){
                return new ResponseMessage("success","State has been created");
            }
            else{
                return new ResponseMessage("error", "Enable to create state, please contact the admin");
            }
        }

    }

    public List<State> getAllStatesReport(){
        return stateRepository.findAll();
    }

    public ResponseMessage update(State state){
        state.setCode(w.getCode(state.getName()));

        //check if state name already exist
        boolean isStateExist = stateRepository.isExistByNameOnUpdate(state.getId(), state.getName());
        if(isStateExist){
            return new ResponseMessage("error", "State name already exist");
        }
        else{
            stateRepository.save(state);
            return new ResponseMessage("success", "State has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        stateRepository.deleteById(id);
        return new ResponseMessage("success","State has been deleted");
    }

}
