package com.tutorial.transportsystem.payload.request;

import com.tutorial.transportsystem.entity.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//TODO Signup service
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 250)
    private String email;

    private List<ERole> role;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
