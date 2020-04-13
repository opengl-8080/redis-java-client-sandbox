package sandbox.redis.config;

public class RedisHost {
    public final String host;
    public final int port;

    RedisHost(String host) {
        this(host, 6379);
    }

    RedisHost(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
