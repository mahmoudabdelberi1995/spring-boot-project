package com.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
   @NotBlank
    private String username;
 
    @NotBlank

    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;
  

}
