package by.iba.dto.resp.comment;

import by.iba.dto.AbstractDTO;
import by.iba.dto.resp.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO extends AbstractDTO {

    private String content;
    private Integer rating;
    private UserDTO user;

}
