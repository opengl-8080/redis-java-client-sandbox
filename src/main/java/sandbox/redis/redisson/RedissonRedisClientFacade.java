package sandbox.redis.redisson;

import org.redisson.api.RedissonClient;
import sandbox.redis.RedisClientFacade;

public class RedissonRedisClientFacade implements RedisClientFacade {
    private final RedissonClient client;

    RedissonRedisClientFacade(RedissonClient client) {
        this.client = client;
    }

    @Override
    public void set(String key, String value) {
        client.getBucket(key).set(value);
    }

    @Override
    public String get(String key) {
        return client.<String>getBucket(key).get();
    }

    @Override
    public void close() {
        this.client.shutdown();
    }
}
