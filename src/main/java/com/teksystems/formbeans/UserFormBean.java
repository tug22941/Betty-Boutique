package com.teksystems.formbeans;

import com.teksystems.validation.EmailUnique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class UserFormBean {

    private Integer id;

    @NotEmpty(message = "First name is required")
    @Length(max=45, message="First name can not be longer than 45 characters")
    @Pattern(regexp="^[A-Z][-'a-zA-Z]+$", message="Invalid First Name Format")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Length(max=45, message="Last name can not be longer than 45 characters")
    @Pattern(regexp="^[A-Z][-'a-zA-Z]+$", message="Invalid Last Name Format")
    private String lastName;

    @NotEmpty (message = "Email address is required")
    @Length(max=45, message="Email can not be longer than 45 characters")
    @Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message="Invalid Email Format")
    @EmailUnique
    private String email;

    @NotEmpty(message = "Password is required")
    @Length(max=20, message="Password can not be longer than 20 characters")
    @Length(min=3, message="Password can not be shorter than 3 characters")
    private String password;

    @NotEmpty(message = "Password is required")
    private String confirmPassword;
}
