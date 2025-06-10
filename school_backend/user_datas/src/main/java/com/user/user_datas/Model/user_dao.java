package com.user.user_datas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="user_scl")
public class user_dao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    

    private String username;
    private String password;
    private String role;

    

}
