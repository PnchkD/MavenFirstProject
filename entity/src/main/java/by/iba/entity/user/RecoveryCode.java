package by.iba.entity.user;

import by.iba.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "recovery_codes")
@Data
@NoArgsConstructor
public class RecoveryCode extends AbstractEntity {

    private String recoveryCode;

}
