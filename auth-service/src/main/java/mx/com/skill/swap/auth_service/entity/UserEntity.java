package mx.com.skill.swap.auth_service.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    @Column(name="last_name")
    private String lastName;
    @Column(name="second_last_name")
    private String secondLastName;
    private String address;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;


}
