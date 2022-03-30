package by.iba.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEntity extends BaseAbstractEntity {

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_last_update")
    private LocalDateTime dateOfLastUpdate;

    @Column(name = "date_of_last_login")
    private LocalDateTime dateOfLastLogin;

    @Column(name = "date_of_confirm")
    private LocalDateTime dateOfConfirm;

    @Column(name = "banned_date")
    private LocalDateTime bannedDate;

    @PrePersist
    protected void abstractEntityPreInit() {
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfLastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void abstractEntityPreUpdate() {
        this.dateOfLastUpdate = LocalDateTime.now();
    }

}
