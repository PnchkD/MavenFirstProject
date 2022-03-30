package by.iba.entity.user;

import by.iba.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class UserEntity extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password",  length = 256)
    private String password;

    @Column(name = "email", length = 128, unique = true)
    private String email;

    @Column(name =  "role")
    private Role role;

    @Column(name = "avatar")
    private Photo avatar;

    @Column(name = "is_mail_confirmed")
    private LocalDateTime isMailConfirmed;

}
