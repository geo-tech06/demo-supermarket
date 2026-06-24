package com.Supermarket.demo.repositories;

import com.Supermarket.demo.entities.Staff;
import com.Supermarket.demo.projections.StaffSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmail(String email);

    List<StaffSummary> findAllProjectedBy();
}