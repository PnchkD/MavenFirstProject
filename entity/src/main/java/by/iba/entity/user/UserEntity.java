package by.iba.entity.user;

import by.iba.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
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

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "avatar")
    private Photo avatar;

    @Column(name = "is_mail_confirmed")
    private LocalDateTime isMailConfirmed;

    @Column(name = "date_of_last_login")
    private LocalDateTime dateOfLastLogin;

    @Column(name = "date_of_approved")
    private LocalDateTime dateOfApproved;

    @Column(name = "banned_date")
    private LocalDateTime bannedDate;

}
