package com.pharmacy.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "email", nullable = true, unique = true) // 'email' is still unique
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "remember_token", length = 100, nullable = true)
    private String rememberToken;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Column(name = "role", nullable = true)
    private String role;

    @Column(name = "identification_number", nullable = true)
    private BigInteger identificationNumber;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "phone_number", nullable = true)
    private BigInteger phoneNumber;

    @Column(name = "salary", nullable = true)
    private Float salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacy_id", nullable = true)
    @ToString.Exclude
    private Pharmacy pharmacy;

    @Column(name = "profile_picture_url", nullable = true)
    private String profilePictureUrl;
}