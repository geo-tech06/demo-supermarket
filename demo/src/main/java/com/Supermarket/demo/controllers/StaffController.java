package com.Supermarket.demo.controllers;

import com.Supermarket.demo.dto.AddStaff;
import com.Supermarket.demo.dto.StaffInfo;
import com.Supermarket.demo.entities.Staff;
import com.Supermarket.demo.projections.StaffSummary;
import com.Supermarket.demo.security.services.StaffService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StaffInfo create(@Valid @RequestBody AddStaff dto) {
        Staff staff = service.create(dto);
        return new StaffInfo(
                staff.getFirstName() + " " + staff.getLastName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getRole().name()
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StaffSummary> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StaffInfo getById(@PathVariable Long id) {
        return service.getInfoById(id);
    }
}