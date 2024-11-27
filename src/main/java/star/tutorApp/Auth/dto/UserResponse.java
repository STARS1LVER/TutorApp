package star.tutorApp.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import star.tutorApp.User.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String career;
    private String modality;
    private String semester;
    private Role role;  

}
