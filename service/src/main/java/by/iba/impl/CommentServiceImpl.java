package by.iba.impl;

import by.iba.CommentService;
import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.req.comment.CommentReqDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.dto.resp.comment.CommentDTO;
import by.iba.entity.car.CarBrand;
import by.iba.entity.comment.Comment;
import by.iba.entity.request.Request;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.CommentMapper;
import by.iba.repository.CommentRepository;
import by.iba.repository.RequestRepository;
import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getAllByRequestId(Long id) {
        return commentRepository.findAllByRequestId(id)
                .stream()
                .map(commentMapper::fillInDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CommentDTO create(CommentReqDTO commentReqDTO) {

        Comment comment = commentMapper.fillFromReq(commentReqDTO);

        UserEntity user = userRepository.findById(commentReqDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND"));

        Request request = requestRepository.findById(commentReqDTO.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("REQUEST_HAS_BEEN_NOT_FOUND"));

        comment.setUser(user);
        comment.setRequest(request);
        comment = commentRepository.save(comment);

        return commentMapper.fillInDTO(comment);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
