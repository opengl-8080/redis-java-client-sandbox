package sandbox.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import sandbox.redis.RedisClientFacade;
import sandbox.redis.RedisClientFacadeFactory;
import sandbox.redis.config.RedisClusterConfig;
import sandbox.redis.config.RedisSentinelConfig;
import sandbox.redis.config.RedisStandaloneConfig;

public class RedissonRedisClientFacadeFactory implements RedisClientFacadeFactory {

    @Override
    public RedisClientFacade createStandalone(RedisStandaloneConfig config) {
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer()
                .setDatabase(0)
                .setAddress("redis://" + config.hostConfig.host + ":" + config.hostConfig.port);

        RedissonClient redisson = Redisson.create(redissonConfig);
        return new RedissonRedisClientFacade(redisson);
    }

    @Override
    public RedisClientFacade createSentinel(RedisSentinelConfig config) {
        Config redissonConfig = new Config();
        SentinelServersConfig builder = redissonConfig.useSentinelServers()
                .setMasterName(config.masterId)
                .setRetryAttempts(15)
                .setRetryInterval(10000);
        config.sentinels.forEach(sentinel -> builder.addSentinelAddress("redis://" + sentinel.host + ":" + sentinel.port));

        RedissonClient redission = Redisson.create(redissonConfig);

        return new RedissonRedisClientFacade(redission);
    }

    @Override
    public RedisClientFacade createCluster(RedisClusterConfig config) {
        Config redissonConfig = new Config();
        ClusterServersConfig builder = redissonConfig.useClusterServers();
        config.nodes.forEach(node -> builder.addNodeAddress("redis://" + node.host + ":" + node.port));

        RedissonClient redisson = Redisson.create(redissonConfig);

        return new RedissonRedisClientFacade(redisson);
    }
}
