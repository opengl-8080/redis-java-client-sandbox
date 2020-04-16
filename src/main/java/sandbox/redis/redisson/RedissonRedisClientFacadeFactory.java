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
    
    private static final int RETRY_ATTEMPTS = 15;
    private static final int RETRY_INTERVAL = 10000;

    @Override
    public RedisClientFacade createStandalone(RedisStandaloneConfig config) {
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer()
                .setAddress("redis://" + config.hostConfig.host + ":" + config.hostConfig.port)
                .setRetryAttempts(RETRY_ATTEMPTS)
                .setRetryInterval(RETRY_INTERVAL);

        RedissonClient redisson = Redisson.create(redissonConfig);
        return new RedissonRedisClientFacade(redisson);
    }

    @Override
    public RedisClientFacade createSentinel(RedisSentinelConfig config) {
        Config redissonConfig = new Config();
        SentinelServersConfig builder = redissonConfig.useSentinelServers()
                .setMasterName(config.masterId)
                .setRetryAttempts(RETRY_ATTEMPTS)
                .setRetryInterval(RETRY_INTERVAL);
        config.sentinels.forEach(sentinel -> builder.addSentinelAddress("redis://" + sentinel.host + ":" + sentinel.port));

        RedissonClient redission = Redisson.create(redissonConfig);

        return new RedissonRedisClientFacade(redission);
    }

    @Override
    public RedisClientFacade createCluster(RedisClusterConfig config) {
        Config redissonConfig = new Config();
        ClusterServersConfig builder = redissonConfig
                .useClusterServers()
                .setRetryAttempts(RETRY_ATTEMPTS)
                .setRetryInterval(RETRY_INTERVAL);
        config.nodes.forEach(node -> builder.addNodeAddress("redis://" + node.host + ":" + node.port));

        RedissonClient redisson = Redisson.create(redissonConfig);

        return new RedissonRedisClientFacade(redisson);
    }
}
