package star.tutorApp.security.routes;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SecurityRoutes {

    @PostMapping( value = "tutor")
    public String welcome(){
        return "Welcome to Tutor App";
    }


}
