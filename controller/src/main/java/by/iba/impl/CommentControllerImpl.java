package by.iba.impl;

import by.iba.CommentController;
import by.iba.CommentService;
import by.iba.dto.req.comment.CommentReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.comment.CommentDTO;
import by.iba.dto.resp.comment.CommentsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentsDTO> getCommentsByRequestId(@PathVariable Long id) {
        List<CommentDTO> comments = commentService.getAllByRequestId(id);

        return ResponseEntity
                .ok()
                .body(new CommentsDTO(comments));
    }

    @Override
    public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid CommentReqDTO commentReqDTO, BindingResult bindingResult) {
        CommentDTO comment = commentService.create(commentReqDTO);

        return ResponseEntity
                .ok()
                .body(comment);
    }

    @Override
    public ResponseEntity<RespStatusDTO> deleteComment(@PathVariable Long id) {

        commentService.delete(id);

        return ResponseEntity
                .status(200)
                .body(new RespStatusDTO("COMMENT_DELETED"));
    }
    
}
