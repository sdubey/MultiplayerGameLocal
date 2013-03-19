package networking.response;

// Custom Imports
import metadata.Constants;
import utility.GamePacket;

/**
 * The ResponseExitGame class is used to inform the client whether it can allow
 * the user to exit the game safely.
 */
public class ResponseExitGame extends GameResponse {

    private short status = 0;

    public ResponseExitGame() {
        responseCode = Constants.SMSG_SAVE_EXIT_GAME;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        return packet.getBytes();
    }
}
