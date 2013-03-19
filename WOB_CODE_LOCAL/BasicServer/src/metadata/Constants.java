package metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Request (1xx) + Response (2xx)
    public final static short CMSG_AUTH = 101;
    public final static short SMSG_AUTH = 201;
    public final static short CMSG_REGISTER = 102;
    public final static short SMSG_REGISTER = 202;
    public final static short CMSG_CHAT = 103;
    public final static short SMSG_CHAT = 203;
    public final static short CMSG_HEARTBEAT = 104;
    public final static short SMSG_HEARTBEAT = 204;
    public final static short CMSG_SAVE_EXIT_GAME = 105;
    public final static short SMSG_SAVE_EXIT_GAME = 205;
    public final static short SMSG_CREATE_ENV = 301;

    // Other
    public static final int SAVE_INTERVAL = 60000;
    public static final String CLIENT_VERSION = "1.00";
    public static final int TIMEOUT_SECONDS = 90;
}
