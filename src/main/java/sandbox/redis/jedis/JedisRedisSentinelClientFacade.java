package sandbox.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import sandbox.redis.RedisClientFacade;

public class JedisRedisSentinelClientFacade implements RedisClientFacade {
    private final JedisSentinelPool pool;

    public JedisRedisSentinelClientFacade(JedisSentinelPool pool) {
        this.pool = pool;
    }

    @Override
    public void set(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public void close() {
        pool.close();
    }
}
