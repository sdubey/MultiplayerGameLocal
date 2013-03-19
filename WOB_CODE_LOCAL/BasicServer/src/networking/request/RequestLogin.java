package networking.request;

// Java Imports
import java.io.IOException;

// Custom Imports
import core.GameClient;
import core.GameServer;
import dataAccessLayer.PlayerDAO;
import metadata.Constants;
import model.Player;
import networking.response.ResponseCreateEnv;
import networking.response.ResponseLogin;
import utility.DataReader;

/**
 * The RequestLogin class authenticates the user information to log in. Other
 * tasks as part of the login process lies here as well.
 */
public class RequestLogin extends GameRequest {

    // Data
    private String version;
    private String user_id;
    private String password;
    // Responses
    private ResponseLogin responseLogin;
    private ResponseCreateEnv responseCreateEnv;

    public RequestLogin() {
        responses.add(responseLogin = new ResponseLogin());
        responses.add(responseCreateEnv = new ResponseCreateEnv());
    }

    @Override
    public void parse() throws IOException {
        version = DataReader.readString(dataInput).trim();
        user_id = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
    }

    @Override
    public void doBusiness() throws Exception {
        System.out.println("User '" + user_id + "' is connecting...");

        Player player = null;
        // Checks if the connecting client meets the minimum version required
        if (version.compareTo(Constants.CLIENT_VERSION) >= 0) {
            // Ensure the user ID and MD5 password is in the correct format
            if (!user_id.isEmpty() && password.matches("[a-fA-F0-9]{32}")) {
                // Attempt to retrieve the account
                player = PlayerDAO.getAccount(user_id, password);
            }

            if (player == null) {
                responseLogin.setStatus((short) 1); // User info is incorrect
                System.out.println("User '" + user_id + "' has failed to log in.");
            } else {
                // Prevent consecutive login attempts
                if (client.getPlayer() == null || player.getID() != client.getPlayer().getID()) {
                    GameClient thread = client.getServer().getThreadByPlayerID(player.getID());
                    // If account is already in use, remove and disconnect the client
                    if (thread != null) {
                        responseLogin.setStatus((short) 2); // Account is in use
                        thread.stopClient();
                        System.out.println("User '" + user_id + "' account is already in use.");
                    } else {
                        // Continue with the login process
                        PlayerDAO.updateLogin(player.getID(), client.getIP());
                        GameServer.getInstance().setActivePlayer(player);
                        player.setClient(client);
                        player.setLastSaved(System.currentTimeMillis());

                        // Pass Player reference into thread
                        client.setPlayer(player);
                        // Keep track of the new client thread
                        client.getServer().addToActiveThreads(client);
                        // Set response information
                        responseLogin.setStatus((short) 0); // Login is a success
                        responseLogin.setPlayer(player);

                        System.out.println("User '" + player.getUsername() + "' has successfully logged in.");
                    }
                } else {
                    responseLogin.setStatus((short) 4); // Consecutive logins
                }
            }
        } else {
            responseLogin.setStatus((short) 3); // Client version not compatible
            System.out.println("User '" + player.getUsername() + "' has failed to log in. (v" + version + ")");
        }
    }
}
