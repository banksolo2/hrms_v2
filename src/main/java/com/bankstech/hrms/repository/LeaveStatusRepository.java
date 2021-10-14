package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Integer> {

    public LeaveStatus findByName(String name);

    public LeaveStatus findByCode(String code);

    @Query(""+
            "SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END "+
            "FROM LeaveStatus l WHERE LOWER(l.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END "+
            "FROM LeaveStatus l "+
            "WHERE l.id != ?1 AND LOWER(l.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query(""+
            "SELECT l FROM LeaveStatus l  WHERE l.id != ?1"
    )
    public List<LeaveStatus> leaveStatusOptions(Integer id);
}
