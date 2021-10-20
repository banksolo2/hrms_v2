package com.bankstech.hrms.service;

import com.bankstech.hrms.format.Word;
import com.bankstech.hrms.model.Department;
import com.bankstech.hrms.model.ResponseMessage;
import com.bankstech.hrms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private long millis=System.currentTimeMillis();
    private Word w = new Word();

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public ResponseMessage create(Department department){
        department.setCode(w.getCode(department.getName()));
        department.setCreatedAt(new Date(millis));

        //check if department name already exist
        boolean isNameExist = departmentRepository.existByName(department.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Department name already exist");
        }
        else{
            Department newDepartment = departmentRepository.save(department);
            if(newDepartment.getId() >= 1){
                return new ResponseMessage("success", "Department has been created");
            }
            else{
                return new ResponseMessage("error","Enable to create department, please contact the assistant");
            }
        }
    }

    public List<Department> all(){
        return departmentRepository.findAll();
    }

    public ResponseMessage update(Department department){
        department.setCode(w.getCode(department.getName()));
        department.setUpdatedAt(new Date(millis));

        //check if department name exist
        boolean isNameExist = departmentRepository.existByNameOnUpdate(department.getId(),department.getName());
        if(isNameExist){
            return new ResponseMessage("error", "Department name already exist");
        }
        else{
            departmentRepository.save(department);
            return new ResponseMessage("success", "Department has been updated");
        }
    }

    public ResponseMessage delete(Integer id){
        departmentRepository.deleteById(id);
        return new ResponseMessage("success","Department has been deleted");
    }

    public List<Department> options(Integer id){
        return departmentRepository.options(id);
    }
}
