package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

    public Holiday findByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(h) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Holiday h WHERE LOWER(h.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(h) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Holiday h "+
            "WHERE h.id != ?1 AND LOWER(h.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id,String name);
}
