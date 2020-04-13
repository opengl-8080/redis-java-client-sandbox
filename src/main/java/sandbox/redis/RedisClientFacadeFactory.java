package sandbox.redis;

import sandbox.redis.config.RedisClusterConfig;
import sandbox.redis.config.RedisSentinelConfig;
import sandbox.redis.config.RedisStandaloneConfig;

public interface RedisClientFacadeFactory {
    
    RedisClientFacade createStandalone(RedisStandaloneConfig config);
    
    RedisClientFacade createSentinel(RedisSentinelConfig config);
    
    RedisClientFacade createCluster(RedisClusterConfig config);
}
