package br.com.livet.domain.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CreateUserRequest {
    private String firstName;
    private String lastName;

}
