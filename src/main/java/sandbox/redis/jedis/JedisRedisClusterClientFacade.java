package sandbox.redis.jedis;

import redis.clients.jedis.JedisCluster;
import sandbox.redis.RedisClientFacade;

public class JedisRedisClusterClientFacade implements RedisClientFacade {
    private final JedisCluster jedis;

    JedisRedisClusterClientFacade(JedisCluster jedis) {
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
