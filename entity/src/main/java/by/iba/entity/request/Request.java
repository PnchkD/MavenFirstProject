package by.iba.entity.request;

import by.iba.entity.AbstractCarDescription;
import by.iba.entity.region.Region;
import by.iba.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
public class Request extends AbstractCarDescription {

    @Column(name = "wishes")
    private String wishes;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
