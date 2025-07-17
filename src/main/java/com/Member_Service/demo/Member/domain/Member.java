package com.Member_Service.demo.Member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(length = 50, unique = true, nullable = false)
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;


    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePw(String password){
        this.password = password;
    }
}
