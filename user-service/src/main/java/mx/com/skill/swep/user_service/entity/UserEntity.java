package mx.com.skill.swep.user_service.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity
@Table(name="users")
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
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

}
