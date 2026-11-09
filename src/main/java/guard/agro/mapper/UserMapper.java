package guard.agro.mapper;

import guard.agro.dto.request.UserPutRequest;
import guard.agro.dto.response.UserPutResponse;
import guard.agro.model.User;
import guard.agro.dto.request.UserPostRequest;
import guard.agro.dto.response.UserGetResponse;
import guard.agro.dto.response.UserPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserGetResponse toUserGetResponse (User user);

    List<UserGetResponse> toUserGetResponseList (List<User> users);

    User toUser (UserPostRequest request);

    UserPostResponse toUserPostResponse (User user);

    User toUser (UserPutRequest request);

    UserPutResponse toUserPutResponse (User user);

}
