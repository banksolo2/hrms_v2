package com.bankstech.hrms.repository;

import com.bankstech.hrms.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    public Company findByName(String name);

    public Company findByCode(String code);

    @Query(""+
            "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Company c WHERE LOWER(c.name) = LOWER(?1)"
    )
    public Boolean existByName(String name);

    @Query(""+
            "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END "+
            "FROM Company c "+
            "WHERE c.id != ?1 AND LOWER(c.name) = LOWER(?2)"
    )
    public Boolean existByNameOnUpdate(Integer id, String name);

    @Query(""+
            "SELECT c FROM Company c WHERE c.id = ?1"
    )
    public Company getCompanyById(Integer id);


    @Query(""+
            "SELECT c FROM Company c WHERE c.id != ?1"
    )
    public List<Company> options(Integer id);
}
