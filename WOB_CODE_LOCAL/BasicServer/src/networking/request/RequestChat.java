package networking.request;

// Java Imports
import java.io.IOException;

// Custom Imports
import core.GameServer;
import networking.response.ResponseChat;
import utility.DataReader;

/**
 * The RequestChat class handles all incoming chat messages and redirect those
 * messages, if needed, to other users.
 */
public class RequestChat extends GameRequest {

    // Data
    private short type;
    private String message;
    // Responses
    private ResponseChat responseChat;

    public RequestChat() {
        responses.add(responseChat = new ResponseChat());
    }

    @Override
    public void parse() throws IOException {
        type = DataReader.readShort(dataInput);
        message = DataReader.readString(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        responseChat.setMessage(message);
        responseChat.setName(client.getPlayer().getUsername());
        responseChat.setType(type);

        GameServer.getInstance().addResponseForAllOnlinePlayers(client.getId(), responseChat);
    }
}
