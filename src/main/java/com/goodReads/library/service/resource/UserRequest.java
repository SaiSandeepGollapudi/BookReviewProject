package com.goodReads.library.service.resource;

import com.goodReads.library.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "password cannot be blank")// usually the password validation like upper & lower case etc. are automatically taken care by front end
    private String password;

   @Email(message = "Enter a valid email address")// Ensures that the annotated element is a valid email address.
    private String email;

    @Pattern(regexp = "\\+?\\d{10,}",message = "phonenumber has to be 10 digits" )
    private String phonenumber;
    public User getUser(){

        return User.builder()
                .username(this.username)
                .password(this.password)
              //  .authority(this.authority)
                .email(this.email)
                .phonenumber(this.phonenumber)
                .build();
    }
    }

