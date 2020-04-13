package sandbox.redis.jedis;

import redis.clients.jedis.Jedis;
import sandbox.redis.RedisClientFacade;

public class JedisRedisClientFacade implements RedisClientFacade {
    private final Jedis jedis;
    
    JedisRedisClientFacade(Jedis jedis) {
        this.jedis = jedis;
    }
    
    @Override
    public void set(String key, String value) {
        this.jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        return this.jedis.get(key);
    }

    @Override
    public void close() {
        this.jedis.close();
    }
}
