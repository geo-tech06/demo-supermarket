package com.Supermarket.demo.security.services;

import com.Supermarket.demo.dto.AddStaff;
import com.Supermarket.demo.dto.StaffInfo;
import com.Supermarket.demo.entities.Staff;
import com.Supermarket.demo.exceptions.ResourceNotFoundException;
import com.Supermarket.demo.projections.StaffSummary;
import com.Supermarket.demo.repositories.StaffRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository repository;
    private final PasswordEncoder encoder;

    public StaffService(StaffRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Staff create(AddStaff dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("EMAIL_ALREADY_IN_USE");
        }

        Staff staff = new Staff();
        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setEmail(dto.getEmail());
        staff.setPhone(dto.getPhone());
        staff.setPassword(encoder.encode(dto.getPassword()));
        staff.setRole(dto.getRole());

        return repository.save(staff);
    }

    public List<StaffSummary> getAll() {
        return repository.findAllProjectedBy();
    }

    public Staff getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("STAFF_NOT_FOUND"));
    }

    public StaffInfo getInfoById(Long id) {
        Staff staff = getById(id);
        return new StaffInfo(
                staff.getFirstName() + " " + staff.getLastName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getRole().name()
        );
    }
}