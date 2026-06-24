package com.Supermarket.demo.security.config;

import com.Supermarket.demo.entities.Staff;
import com.Supermarket.demo.enums.Role;
import com.Supermarket.demo.repositories.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedDefaultAdmin(
            StaffRepository repository,
            PasswordEncoder encoder) {

        return args -> {
            if (repository.count() > 0) {
                return;
            }

            Staff admin = new Staff();
            admin.setFirstName("Default");
            admin.setLastName("Admin");
            admin.setEmail("admin@supermarket.local");
            admin.setPassword(encoder.encode("ChangeMe123!"));
            admin.setRole(Role.ADMIN);

            repository.save(admin);
            System.out.println(">>> Default admin seeded successfully");
        };
    }
}