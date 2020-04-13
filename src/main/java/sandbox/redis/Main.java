package sandbox.redis;

import sandbox.redis.config.RedisClusterConfig;
import sandbox.redis.config.RedisSentinelConfig;
import sandbox.redis.config.RedisStandaloneConfig;
import sandbox.redis.jedis.JedisRedisClientFacadeFactory;
import sandbox.redis.lettuce.LettuceRedisClientFacadeFactory;
import sandbox.redis.redisson.RedissonRedisClinetFacadeFactory;

import java.util.Map;

public class Main {

    /**
     * main.
     * @param args
     *  [0]: client-type (jedis, lettuce, redisson)
     *  [1]: target (replica, sentinel, cluster) 
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("args must be 2(client-type and target).");
            System.exit(1);
        }
        
        String clientType = args[0];
        RedisClientFacadeFactory factory = decideFactory(clientType);

        String target = args[1];
        RedisClientFacade facade = createFacade(factory, target);
        
        new ConsoleController(facade, clientType, target).execute();
        System.out.println("EXIT");
    }
    
    private static RedisClientFacadeFactory decideFactory(String clientType) throws Exception {
        Map<String, Class<? extends RedisClientFacadeFactory>> factoryMap = Map.of(
                "jedis", JedisRedisClientFacadeFactory.class,
                "lettuce", LettuceRedisClientFacadeFactory.class,
                "redisson", RedissonRedisClinetFacadeFactory.class
        );

        if (!factoryMap.containsKey(clientType)) {
            throw new RuntimeException("Unknown client-type : " + clientType);
        }

        return factoryMap.get(clientType).getConstructor().newInstance();
    }
    
    private static RedisClientFacade createFacade(RedisClientFacadeFactory factory, String target) {
        if (target.equals("replica")) {
            RedisStandaloneConfig config = new RedisStandaloneConfig().hostPort("redis-master", 6379);
            
            return factory.createStandalone(config);
        } else if (target.equals("sentinel")) {
            RedisSentinelConfig config = new RedisSentinelConfig().masterId("mysentinel")
                    .addSentinel("redis-sentinel1", 26379)
                    .addSentinel("redis-sentinel2", 26379)
                    .addSentinel("redis-sentinel3", 26379);
            
            return factory.createSentinel(config);
        } else if (target.equals("cluster")) {
            RedisClusterConfig config = new RedisClusterConfig()
                    .addNode("redis-instance1", 6379)
                    .addNode("redis-instance2", 6379)
                    .addNode("redis-instance3", 6379);
            
            return factory.createCluster(config);
        } else {
            throw new RuntimeException("Unknown target : " + target);
        }
    }
    
}
