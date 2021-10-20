package com.bankstech.hrms.repository;


import com.bankstech.hrms.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    public Level findByName(String name);

    public Level findByCode(String code);



    @Query(""+
            "SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Level l WHERE LOWER(l.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Level l "+
            "WHERE l.id != ?1 AND LOWER(l.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query(""+
            "SELECT l FROM Level l WHERE l.id != ?1"
    )
    public List<Level> options(Integer id);
}
