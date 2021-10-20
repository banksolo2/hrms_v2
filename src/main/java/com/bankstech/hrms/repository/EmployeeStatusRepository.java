package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Integer> {

    public EmployeeStatus findByName(String name);

    public EmployeeStatus findByCode(String code);

    @Query(""+
            "SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END "+
            "FROM EmployeeStatus e WHERE LOWER(e.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END "+
            "FROM EmployeeStatus e "+
            "WHERE e.id != ?1 AND LOWER(e.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query("SELECT e FROM EmployeeStatus e WHERE e.id != ?1")
    public List<EmployeeStatus> options(Integer id);
}
