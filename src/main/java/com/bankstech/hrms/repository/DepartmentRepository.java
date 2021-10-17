package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public Department findByName(String name);

    public Department findByCode(String code);

    @Query(""+
            "SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Department d WHERE LOWER(d.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Department d "+
            "WHERE d.id != ?1 AND LOWER(d.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query(""+
            "SELECT d FROM Department d WHERE d.id != ?1"
    )
    public List<Department> options(Integer id);
}
