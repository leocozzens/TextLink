import java.util.Scanner;

public class Main {
    private static final int MESSAGE_SIZE = 256;
    private static final String USERDATA_HEADER = "USER";
    private static final String USERDATA_FOOTER = "ENDU";
    private static final String PROMPT = "-> ";
    public static void main(String args[]) {
        MessageFactory outFactory = new MessageFactory(USERDATA_HEADER, USERDATA_FOOTER, MESSAGE_SIZE);
        Scanner in = new Scanner(System.in);

        String serverIP;
        if(args.length > 0) serverIP = args[0];
        else serverIP = "0.0.0.0";
        int serverPort = 9002;
        Connection serverConn = new Connection(serverIP, serverPort);

        StayAlive KALoop = new StayAlive(serverConn);
        Thread KAThread = new Thread(KALoop);
        KAThread.start();
        ExitHandler.handleExit(KALoop);

        while(true) {
            outFactory.createMessage(in, PROMPT);
            serverConn.sendToServer(outFactory.getData());
            System.out.println("Message sent: " + outFactory.getMessage());
        }
    }
}