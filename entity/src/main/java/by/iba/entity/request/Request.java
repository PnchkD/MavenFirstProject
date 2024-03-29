package by.iba.entity.request;

import by.iba.entity.AbstractCarDescription;
import by.iba.entity.comment.Comment;
import by.iba.entity.photo.Photo;
import by.iba.entity.region.Region;
import by.iba.entity.ticket.Ticket;
import by.iba.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "request")
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "request")
    private Set<Comment> comments = new HashSet<>();

}
