package sandbox.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import sandbox.redis.RedisClientFacade;
import sandbox.redis.RedisClientFacadeFactory;
import sandbox.redis.config.RedisClusterConfig;
import sandbox.redis.config.RedisSentinelConfig;
import sandbox.redis.config.RedisStandaloneConfig;

import java.util.Set;
import java.util.stream.Collectors;

public class JedisRedisClientFacadeFactory implements RedisClientFacadeFactory {
    
    private static final int TIMEOUT = 20_000;
    private static final int MAX_ATTEMPTS = 10;

    @Override
    public RedisClientFacade createStandalone(RedisStandaloneConfig config) {
        Jedis jedis = new Jedis(config.hostConfig.host, config.hostConfig.port, TIMEOUT);
        
        return new JedisRedisClientFacade(jedis);
    }

    @Override
    public RedisClientFacade createSentinel(RedisSentinelConfig config) {
        Set<String> sentinels = config.sentinels.stream().map(sentinel -> sentinel.host + ":" + sentinel.port).collect(Collectors.toSet());

        JedisSentinelPool pool = new JedisSentinelPool(config.masterId, sentinels, new GenericObjectPoolConfig(), TIMEOUT);

        return new JedisRedisSentinelClientFacade(pool);
    }

    @Override
    public RedisClientFacade createCluster(RedisClusterConfig config) {
        Set<HostAndPort> hostAndPortSet = config.nodes.stream()
                .map(node -> new HostAndPort(node.host, node.port))
                .collect(Collectors.toSet());

//        JedisCluster jedis = new JedisCluster(hostAndPortSet, TIMEOUT, MAX_ATTEMPTS);
        JedisCluster jedis = new JedisCluster(hostAndPortSet);

        return new JedisRedisClusterClientFacade(jedis);
    }
}
