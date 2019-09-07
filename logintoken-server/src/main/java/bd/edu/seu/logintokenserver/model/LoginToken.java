package bd.edu.seu.logintokenserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
public class LoginToken {
    private String username;
    private String fullname;
    private String role;

    public LoginToken() {
        role = "norole";
    }
}
