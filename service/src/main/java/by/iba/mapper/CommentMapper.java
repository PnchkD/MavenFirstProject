package by.iba.mapper;

import by.iba.dto.req.comment.CommentReqDTO;
import by.iba.dto.resp.comment.CommentDTO;
import by.iba.entity.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentDTO fillInDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();

        if (Objects.nonNull(comment.getContent())) {
            commentDTO.setContent(comment.getContent());
        }

        if (Objects.nonNull(comment.getRating())) {
            commentDTO.setRating(comment.getRating());
        }

        if (Objects.nonNull(comment.getUser())) {
            commentDTO.setUser(userMapper.fillFromInDTO(comment.getUser()));
        }

        if(Objects.nonNull(comment.getId())) {
            commentDTO.setId(comment.getId());
        }

        if(Objects.nonNull(comment.getDateOfCreation())) {
            commentDTO.setDateOfCreation(comment.getDateOfCreation());
        }

        if(Objects.nonNull(comment.getDateOfLastUpdate())) {
            commentDTO.setDateOfLastUpdate(comment.getDateOfLastUpdate());
        }

        return commentDTO;
    }

    public Comment fillFromReq(CommentReqDTO commentReqDTO) {
        Comment comment = new Comment();

        if (Objects.nonNull(commentReqDTO.getContent())) {
            comment.setContent(commentReqDTO.getContent());
        }

        if (Objects.nonNull(commentReqDTO.getRating())) {
            comment.setRating(commentReqDTO.getRating());
        }

        return comment;
    }

}
