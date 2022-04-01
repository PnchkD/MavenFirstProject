package by.iba.entity.user;

import by.iba.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;


}
