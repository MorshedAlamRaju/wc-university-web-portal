package bd.edu.seu.logintokenserver.controller;

import bd.edu.seu.logintokenserver.model.LoginToken;
import bd.edu.seu.logintokenserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "")
    @ResponseBody
    public LoginToken authenticate(@RequestParam String username, @RequestParam String password){
        return authenticationService.authenticate(username, password);
    }

    @PostMapping(value = "")
    @ResponseBody
    public LoginToken createCredentials(@RequestParam String username, @RequestParam String password,
                                        @RequestParam String fullname, @RequestParam String role){
        return authenticationService.createCredentials(username, password, fullname, role);

    }

}
