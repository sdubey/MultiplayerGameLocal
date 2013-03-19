package networking.request;

// Java Imports
import java.io.IOException;

// Custom Imports
import dataAccessLayer.PlayerDAO;
import networking.response.ResponseRegist;
import utility.DataReader;

/**
 * The RequestRegist class handles the registration process to create new
 * accounts for users.
 */
public class RequestRegist extends GameRequest {

    // Data
    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    // Responses
    private ResponseRegist responseRegist;

    public RequestRegist() {
        responses.add(responseRegist = new ResponseRegist());
    }

    @Override
    public void parse() throws IOException {
        username = DataReader.readString(dataInput).trim();
        email = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
//        first_name = DataReader.readString(dataInput).trim();
//        last_name = DataReader.readString(dataInput).trim();
        first_name = "???";
        last_name = "???";
    }

    @Override
    public void doBusiness() throws Exception {
        if (!username.isEmpty() && !email.isEmpty() && email.split("@").length == 2 && password.matches("[a-fA-F0-9]{32}")) {
            if (PlayerDAO.containsUsername(username)) {
                responseRegist.setStatus((short) 2);
            } else {
                PlayerDAO.createAccount(username, password, username, first_name, last_name, client.getIP());
                responseRegist.setStatus((short) 0);
            }
        }
    }
}
