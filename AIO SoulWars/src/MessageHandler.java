import aids.Variables;

public class MessageHandler {

    private static final int CHAT = 2;
    private static final int YELL = 18;
    private static final int CLAN = 13;
    private static final int GAME = 0;


    public static void handle(String msg, int type, String username) {
        msg = msg.toLowerCase();

        if (type == GAME) {

            if (msg.contains("you have been added to")) {

                String relevant = msg.substring(msg.indexOf(">"), msg.indexOf("/")).replaceAll("[^a-z]", "");
                System.out.println(relevant);
                if (relevant.contains("blue")) {
                    Variables.setCurrentTeam(Variables.Team.BLUE);
                } else {
                    Variables.setCurrentTeam(Variables.Team.RED);
                }
            }

            if (msg.contains("next game begins")) {
                // String relevant = msg.substring(msg.indexOf(">"), msg.indexOf("/"));
                // int time = Integer.parseInt(relevant.replaceAll("[^\\d]", ""));
                // aids.Variables.setNextGame(System.currentTimeMillis() + time * 1000);
            }

            if (msg.contains("minimum 4 players")) {
                Variables.setNextGame(300 * 1000);
            }

            if (msg.contains("you left your team")) {
                Variables.setCurrentTeam(null);
            }

        }

    }
}
