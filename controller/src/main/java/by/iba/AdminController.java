package by.iba;

import by.iba.dto.req.UserRolesReqDTO;
import by.iba.dto.req.UserSearchCriteriaReqDTO;
import by.iba.dto.req.UserSortCriteriaReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.UsersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/admin")
public interface AdminController {

    @GetMapping("/users")
    ResponseEntity<UsersDTO> getUsers(UserSearchCriteriaReqDTO userSortCriteriaReqDTO);

    @PatchMapping("/users/ban/{id}")
    ResponseEntity<RespStatusDTO> banUser(@PathVariable Long id);

    @PatchMapping("/users/unban/{id}")
    ResponseEntity<RespStatusDTO> unbanUser(@PathVariable Long id);

    @PatchMapping("/users/confirm/{id}")
    ResponseEntity<RespStatusDTO> confirmUser(@PathVariable Long id);

    @PatchMapping("/users/role/{id}")
    ResponseEntity<RespStatusDTO> updateUserRole(@PathVariable Long id, @RequestBody @Valid UserRolesReqDTO userRolesReqDTO, BindingResult bindingResult);

}
