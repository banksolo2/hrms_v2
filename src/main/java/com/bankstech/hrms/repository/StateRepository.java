package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    public State findByName(String name);

    public State findByCode(String code);

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM State s " +
            "WHERE LOWER(s.name) = LOWER(?1)"
    )
    public Boolean isExistByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(s) > 0 THEN "+
            "TRUE ELSE FALSE END "+
            "FROM State s "+
            "WHERE s.id != ?1 AND LOWER(s.name) = LOWER(?2)"
    )
    public Boolean isExistByNameOnUpdate(Integer id, String name);




}
