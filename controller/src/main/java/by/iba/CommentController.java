package by.iba;

import by.iba.dto.req.comment.CommentReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.comment.CommentDTO;
import by.iba.dto.resp.comment.CommentsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/comments")
@CrossOrigin(origins = "*")
public interface CommentController {

    @GetMapping("/{id}")
    ResponseEntity<CommentsDTO> getCommentsByRequestId(@PathVariable Long id);

    @PostMapping("/create")
    ResponseEntity<CommentDTO> createComment(@RequestBody @Valid CommentReqDTO commentReqDTO, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<RespStatusDTO> deleteComment(@PathVariable Long id);

}
