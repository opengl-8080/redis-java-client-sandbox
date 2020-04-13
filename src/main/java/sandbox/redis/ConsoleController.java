package sandbox.redis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConsoleController {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final RedisClientFacade facade;
    private final String clientType;
    private final String target;

    ConsoleController(RedisClientFacade facade, String clientType, String target) {
        this.facade = facade;
        this.clientType = clientType;
        this.target = target;
    }

    void execute() {
        try {
            while (true) {
                System.out.printf("[%s:%s]> ", this.clientType, this.target);
                String line = reader.readLine();
                if (line.equals("exit")) {
                    return;
                }

                String[] tokens = line.split(" ");
                String command = tokens[0];
                if (command.equals("get")) {
                    String key = tokens[1];
                    String value = this.facade.get(key);
                    System.out.println(value);
                } else if (command.equals("set")) {
                    String key = tokens[1];
                    String value = tokens[2];
                    this.facade.set(key, value);
                } else {
                    System.out.println("Unknown Command : " + command);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            facade.close();
        }
    }
}
