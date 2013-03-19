package networking.response;

// Custom Imports
import utility.GamePacket;
import metadata.Constants;

/**
 * The ResponseRegist class is used to inform the client whether the
 * registration was successful.
 */
public class ResponseRegist extends GameResponse {

    private short status;

    public ResponseRegist() {
        responseCode = Constants.SMSG_REGISTER;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);

        return packet.getBytes();
    }
}