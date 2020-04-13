package sandbox.redis.lettuce;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import sandbox.redis.RedisClientFacade;

public class LettuceRedisClusterClientFacade implements RedisClientFacade {
    private final RedisClusterClient client;
    private final StatefulRedisClusterConnection<String, String> connection;

    public LettuceRedisClusterClientFacade(RedisClusterClient client) {
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
