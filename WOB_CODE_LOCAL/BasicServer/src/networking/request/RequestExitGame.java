package networking.request;

// Java Imports
import java.io.IOException;

// Custom Imports
import networking.response.ResponseExitGame;

/**
 * The RequestExitGame class is used to alert the server that the user wants
 * to disconnect.
 */
public class RequestExitGame extends GameRequest {

    // Responses
    private ResponseExitGame responseExitGame;

    public RequestExitGame() {
        responses.add(responseExitGame = new ResponseExitGame());
    }

    @Override
    public void parse() throws IOException {
    }

    @Override
    public void doBusiness() throws Exception {
        client.stopClient();
    }
}
