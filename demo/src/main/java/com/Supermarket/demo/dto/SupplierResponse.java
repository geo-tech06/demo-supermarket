package com.Supermarket.demo.dto;

import com.Supermarket.demo.entities.Supplier;

public class SupplierResponse {

    private Long id;
    private String name;
    private String contactEmail;

    public SupplierResponse() {}

    public SupplierResponse(Long id, String name, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public static SupplierResponse from(Supplier s) {
        return new SupplierResponse(s.getId(), s.getName(), s.getContactEmail());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
}
