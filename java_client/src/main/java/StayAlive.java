public class StayAlive implements Runnable {
    private static final int KA_SIZE = 1;
    private static final int SLEEP_MS = 100;
    private static final String HEADER = "KEEP";
    private static final String FOOTER = "ALVE";
    private static final String ALIVE = "\n";
    private static final String DEAD = "\0";

    private boolean isAlive;

    private Connection serverConn;
    private MessageFactory beatFactory;

    public StayAlive(Connection serverConn) {
        this.isAlive = true;
        this.serverConn = serverConn;
        this.beatFactory = new MessageFactory(HEADER, FOOTER, KA_SIZE);
    }

    @Override
    public void run() {
        while(isAlive) {
            sendBeat(ALIVE);
            try {
                Thread.sleep(SLEEP_MS);
            }
            catch(InterruptedException e) {}
        }
        sendBeat(DEAD);
    }

    public void sendBeat(String status) {
        this.beatFactory.createMessage(status);
        serverConn.sendToServer(beatFactory.getData());
    }

    public void sendKill() {
        this.isAlive = false;
    }
}
