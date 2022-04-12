package by.iba.impl;

import by.iba.AdminController;
import by.iba.dto.req.UserSearchCriteriaReqDTO;
import by.iba.dto.req.UserSortCriteriaReqDTO;
import by.iba.helper.ControllerHelper;
import by.iba.UserService;
import by.iba.dto.req.UserRolesReqDTO;
import by.iba.dto.resp.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    @Override
    public ResponseEntity<UsersDTO> getUsers(UserSortCriteriaReqDTO userSortCriteriaReqDTO) {
        List<UserDTO> users = userService.findAll(userSortCriteriaReqDTO);

        return ResponseEntity
                .ok()
                .body(new UsersDTO(users));
    }

    @Override
    public ResponseEntity<UsersDTO> search(UserSearchCriteriaReqDTO userSearchCriteriaReqDTO) {
        List<UserDTO> users = userService.searchUser(userSearchCriteriaReqDTO.getSearch());

        return ResponseEntity
                .ok()
                .body(new UsersDTO(users));
    }

    @Override
    public ResponseEntity<RespStatusDTO> banUser(@PathVariable Long id) {

        UserDTO user = userService.banUser(id, true);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER WITH ID " + id + " HAS BEEN UNBANNED"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> unbanUser(@PathVariable Long id) {

        userService.banUser(id, false);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER WITH ID " + id + " HAS BEEN UNBANNED"));
    }


    @Override
    public ResponseEntity<RespStatusDTO> confirmUser(@PathVariable Long id) {
        userService.confirmUser(id);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User with id " + id + " has been confirmed"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateUserRole(@PathVariable Long id, @RequestBody UserRolesReqDTO userChangeRoleInDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.updateUserRole(id, userChangeRoleInDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER_ROLES_WAS_CHANGED"));
    }

}
