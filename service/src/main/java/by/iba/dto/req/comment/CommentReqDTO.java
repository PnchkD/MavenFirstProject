package by.iba.dto.req.comment;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Content cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_COMMENT_CONTENT_LENGTH)
    private String content;

    @NotNull(message = "Rating cannot be empty")
    @Max(ReqValidation.MAX_COMMENT_RATING_LENGTH)
    private Integer rating;

    @NotNull
    private Long userId;

    @NotNull
    private Long requestId;
}
