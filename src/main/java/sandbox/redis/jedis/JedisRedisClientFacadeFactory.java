package sandbox.redis.jedis;

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

    @Override
    public RedisClientFacade createStandalone(RedisStandaloneConfig config) {
        Jedis jedis = new Jedis(config.hostConfig.host, config.hostConfig.port);
        
        return new JedisRedisClientFacade(jedis);
    }

    @Override
    public RedisClientFacade createSentinel(RedisSentinelConfig config) {
        Set<String> sentinels = config.sentinels.stream().map(sentinel -> sentinel.host + ":" + sentinel.port).collect(Collectors.toSet());

        JedisSentinelPool pool = new JedisSentinelPool(config.masterId, sentinels);

        return new JedisRedisSentinelClientFacade(pool);
    }

    @Override
    public RedisClientFacade createCluster(RedisClusterConfig config) {
        Set<HostAndPort> hostAndPortSet = config.nodes.stream()
                .map(node -> new HostAndPort(node.host, node.port))
                .collect(Collectors.toSet());

        JedisCluster jedis = new JedisCluster(hostAndPortSet);

        return new JedisRedisClusterClientFacade(jedis);
    }
}
