package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

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

    @Query(""+
            "SELECT CASE WHEN COUNT(h) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Holiday h WHERE h.dateAt = ?1"
    )
    public boolean existByDateAt(Date dateAt);

    @Query(""+
            "SELECT CASE WHEN COUNT(h) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Holiday h "+
            "where h.id != ?1 AND h.dateAt = ?2"
    )
    public boolean existByDateAtOnUpdate(Integer id, Date dateAt);

}
