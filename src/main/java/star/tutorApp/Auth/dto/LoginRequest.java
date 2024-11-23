package star.tutorApp.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // me permite crear los getter y setter de manera automatica
@Builder // me permite construir los objetos de una manera muy limpia
@AllArgsConstructor // Genera un constructor que incluye un argumento para cada campo de la clase
@NoArgsConstructor // Genera un constructor sin argumentos

//Se utiliza las 2 anotaciones porque se puede necesitar un constructor vacio como uno que inicialice todos los campos
public class LoginRequest {
    String email;
    String password;

}
