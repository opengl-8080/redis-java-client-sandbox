package sandbox.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import sandbox.redis.RedisClientFacade;

public class LettuceRedisClientFacade implements RedisClientFacade {
    private final RedisClient client;
    private final StatefulRedisConnection<String, String> connection;

    public LettuceRedisClientFacade(RedisClient client) {
        this.client = client;
        this.connection = client.connect();
    }

    @Override
    public void set(String key, String value) {
        connection.sync().set(key, value);
    }

    @Override
    public String get(String key) {
        return connection.sync().get(key);
    }

    @Override
    public void close() {
        this.connection.close();
        this.client.shutdown();
    }
}
