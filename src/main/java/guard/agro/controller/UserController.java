package guard.agro.controller;

import guard.agro.dto.request.UserPutRequest;
import guard.agro.dto.response.UserPutResponse;
import guard.agro.mapper.UserMapper;
import guard.agro.service.UserService;
import guard.agro.dto.request.UserPostRequest;
import guard.agro.dto.response.UserGetResponse;
import guard.agro.dto.response.UserPostResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List users")
    public ResponseEntity<List<UserGetResponse>> findAllOrById(@RequestParam(required = false) Long id) {
        log.debug("request to list all users, param id: '{}'", id);

        var users = userService.findAll(id);

        var userGetResponseList = mapper.toUserGetResponseList(users);

        return ResponseEntity.ok(userGetResponseList);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "User created")
    public ResponseEntity<UserPostResponse> save(@Valid @RequestBody UserPostRequest request) {
        log.debug("request to save user");

        var userToSave = mapper.toUser(request);

        var userSaved = userService.save(userToSave);

        var userPostResponse = mapper.toUserPostResponse(userSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(userPostResponse);
    }

    @ApiResponse(responseCode = "200", description = "User updated")
    @PutMapping("{id}")
    public ResponseEntity<UserPutResponse> update(@Valid @RequestBody UserPutRequest request,
                                                  @PathVariable Long id) {
        log.debug("request to update user with id: '{}'", id);

        var userToUpdate = mapper.toUser(request);

        var userUpdated = userService.update(userToUpdate, id);

        var userPutResponse = mapper.toUserPutResponse(userUpdated);

        return ResponseEntity.ok(userPutResponse);
    }

    @ApiResponse(responseCode = "200", description = "User deleted")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("request to delete user with id: '{}'", id);

        var userToDelete = userService.findUserByIdOrElseThrow(id);

        userService.delete(userToDelete);

        return ResponseEntity.noContent().build();
    }

}
