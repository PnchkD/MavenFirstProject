package by.iba.entity.bid;

import by.iba.entity.AbstractAutoDescription;
import by.iba.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bids")
@Getter
@Setter
@NoArgsConstructor
public class Bid extends AbstractAutoDescription {

    @Column(name = "region")
    private String region;

    @Column(name = "wishes")
    private String wishes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
