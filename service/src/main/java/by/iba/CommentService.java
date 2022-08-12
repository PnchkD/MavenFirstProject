package by.iba;

import by.iba.dto.req.comment.CommentReqDTO;
import by.iba.dto.resp.comment.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllByRequestId(Long id);

    CommentDTO create(CommentReqDTO commentReqDTO);

    void delete(Long id);

}
