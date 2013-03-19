package networking.response;

// Custom Imports
import metadata.Constants;
import utility.GamePacket;

/**
 * The ResponseCreateEnv class is used to send information to the client about
 * what is needed to create an environment.
 */
public class ResponseCreateEnv extends GameResponse {

    public ResponseCreateEnv() {
        responseCode = Constants.SMSG_CREATE_ENV;
    }

    @Override
    public byte[] constructResponseInBytes() {;
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(1);
        packet.addShort16((short) 1);

        return packet.getBytes();
    }
}
