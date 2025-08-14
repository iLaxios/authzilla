package com.laxios.mfaservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String userKey;

    @Column(nullable = false)
    private String name;

}
