package by.iba.dto.resp.request;

import by.iba.dto.AbstractDTO;
import by.iba.dto.resp.comment.CommentDTO;
import by.iba.dto.resp.ticket.TicketDTO;
import by.iba.dto.resp.user.UserDTO;
import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO extends AbstractDTO {

    private String type;
    private String brand;
    private String color;
    private Integer price;
    private String yearOfIssue;
    private Double mileage;
    private String engineType;
    private String driveUnit;
    private String bodyType;
    private String engineCapacity;
    private String transmission;
    private String state;
    private String wishes;
    private String country;
    private String city;
    private UserDTO fromUser;
    private List<TicketDTO> tickets;
    private List<CommentDTO> comments;
}
