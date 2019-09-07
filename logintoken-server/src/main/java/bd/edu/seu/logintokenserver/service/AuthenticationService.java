package bd.edu.seu.logintokenserver.service;

import bd.edu.seu.logintokenserver.LogintokenServerApplication;
import bd.edu.seu.logintokenserver.model.LoginToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {


    private Map<String, String> dummydb;
    private Map<String, LoginToken> usernameToLoginTokenMap;

    public AuthenticationService(){
        dummydb = new HashMap<>();
        usernameToLoginTokenMap = new HashMap<>();
    }

    public LoginToken authenticate(String username, String password){
        String pass = dummydb.get(username);
        if(pass == null || !pass.equals(password))
            return new LoginToken(null, null, "norole");
        return usernameToLoginTokenMap.getOrDefault(username, new LoginToken(null, null, "norole"));
    }

    public LoginToken createCredentials(String username, String password, String fullname, String role){
        dummydb.put(username, password);
//        dummydb.put("raju", "onetwothree");
//        dummydb.put("raaj", "fourfivesix");

        usernameToLoginTokenMap.put(username, new LoginToken(username, fullname, role));
//        usernameToLoginTokenMap.put("raju", new LoginToken("raju", "Morshed Alam", "student"));
//        usernameToLoginTokenMap.put("raaj", new LoginToken("raaj", "Mx. Clever", "faculty"));
        return authenticate(username, password);
    }

    public LoginToken updateCredentials(String username, String oldPassword, String newPassword){
        LoginToken loginToken = authenticate(username, oldPassword);
        if(loginToken.getUsername().equals(username)){
            String fullname = loginToken.getFullname();
            String role = loginToken.getRole();
            dummydb.replace(username, oldPassword, newPassword);
            usernameToLoginTokenMap.replace(username, loginToken, new LoginToken(username, fullname, role));
            return authenticate(username, newPassword);
        } else return  null;
    }




}
