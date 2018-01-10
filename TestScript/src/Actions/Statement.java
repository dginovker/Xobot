package Actions;

/**
 * Created by Cyn on 1/9/2018.
 */
public class Statement {
    private final String action;
    private final String param1;
    private final String param2;
    private final String param3;

    public Statement(String action, String param1, String param2, String param3)
    {
        this.action = action;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "action='" + action + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                '}';
    }
}
