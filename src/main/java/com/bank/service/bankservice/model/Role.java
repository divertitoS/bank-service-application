package com.bank.service.bankservice.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        USER,
        ADMIN
    }

}
