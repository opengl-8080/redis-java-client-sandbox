package sandbox.redis.lettuce;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import sandbox.redis.RedisClientFacade;
import sandbox.redis.RedisClientFacadeFactory;
import sandbox.redis.config.RedisClusterConfig;
import sandbox.redis.config.RedisSentinelConfig;
import sandbox.redis.config.RedisStandaloneConfig;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

public class LettuceRedisClientFacadeFactory implements RedisClientFacadeFactory {

    @Override
    public RedisClientFacade createStandalone(RedisStandaloneConfig config) {
        RedisURI uri = RedisURI.create(config.hostConfig.host, config.hostConfig.port);
        RedisClient client = RedisClient.create(uri);

        ClientOptions options = ClientOptions.builder()
                .timeoutOptions(createTimeoutOptions())
                .build();

        client.setOptions(options);
        
        return new LettuceRedisClientFacade(client);
    }

    @Override
    public RedisClientFacade createSentinel(RedisSentinelConfig config) {
        RedisURI.Builder builder = RedisURI.builder().withSentinelMasterId(config.masterId);
        config.sentinels.forEach(sentinel -> builder.withSentinel(sentinel.host, sentinel.port));
        RedisURI uri = builder.build();
        RedisClient client = RedisClient.create(uri);

        ClientOptions options = ClientOptions.builder()
                .timeoutOptions(createTimeoutOptions())
                .build();
        
        client.setOptions(options);

        return new LettuceRedisClientFacade(client);
    }

    @Override
    public RedisClientFacade createCluster(RedisClusterConfig config) {
        Set<RedisURI> nodeSet = config.nodes.stream().map(node -> RedisURI.create(node.host, node.port)).collect(Collectors.toSet());
        RedisClusterClient client = RedisClusterClient.create(nodeSet);

        ClusterTopologyRefreshOptions topologyOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh(Duration.ofSeconds(1))
                .enableAllAdaptiveRefreshTriggers()
                .build();

        ClusterClientOptions clientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(topologyOptions)
                .timeoutOptions(createTimeoutOptions())
                .build();
        
        client.setOptions(clientOptions);

        return new LettuceRedisClusterClientFacade(client);
    }
    
    private TimeoutOptions createTimeoutOptions() {
        return TimeoutOptions.builder()
                .fixedTimeout(Duration.ofSeconds(20))
                .build();
    }
}
