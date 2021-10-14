package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    public Branch findByName(String name);

    public Branch findByCode(String code);

    @Query(""+
            "SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Branch b WHERE LOWER(b.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Branch b "+
            "WHERE b.id != ?1 AND LOWER(b.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query(""+
            "SELECT b FROM Branch b WHERE b.id != ?1 "
    )
    public List<Branch> options(Integer id);
}
