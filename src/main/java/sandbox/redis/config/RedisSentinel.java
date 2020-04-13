package sandbox.redis.config;

public class RedisSentinel extends RedisHost {
    RedisSentinel(String host) {
        super(host);
    }

    RedisSentinel(String host, int port) {
        super(host, port);
    }
}
