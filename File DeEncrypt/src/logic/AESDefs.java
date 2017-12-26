package logic;

public class AESDefs {
    public static final byte[] SALT = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    public static final int ITERATION_COUNT = 65536;
    public static final int KEY_LENGTH = 256;
    public static final String AES_IV = "encmydLmeizaiOdp";

}
