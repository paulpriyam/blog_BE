package com.example.blog.payload;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotEmpty(message = "name cannot be null or empty !!")
    @Size(min = 3, message = "min characters required is 3 !!")
    private String name;

    @Email(message = "Email not in valid format !!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 12, message = "password should be between 4 to 12 characters long !!")
    private String password;

    @NotNull(message = "About cannot be null or empty!!")
    private String about;

    private Set<CommentDto> comments = new HashSet<>();
}
