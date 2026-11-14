package guard.agro.mapper;

import guard.agro.dto.request.UserPostRequest;
import guard.agro.dto.request.UserPutRequest;
import guard.agro.dto.response.UserGetResponse;
import guard.agro.dto.response.UserPostResponse;
import guard.agro.dto.response.UserPutResponse;
import guard.agro.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetResponse toUserGetResponse(User user);

    List<UserGetResponse> toUserGetResponseList(List<User> users);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUser(UserPostRequest request);

    UserPostResponse toUserPostResponse(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUser(UserPutRequest request);

    UserPutResponse toUserPutResponse(User user);

}
