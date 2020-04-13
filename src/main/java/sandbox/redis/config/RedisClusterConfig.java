package sandbox.redis.config;

import java.util.HashSet;
import java.util.Set;

public class RedisClusterConfig {
    public final Set<RedisHost> nodes = new HashSet<>();
    
    public RedisClusterConfig addNode(String host) {
        this.nodes.add(new RedisHost(host));
        return this;
    }

    public RedisClusterConfig addNode(String host, int port) {
        this.nodes.add(new RedisHost(host, port));
        return this;
    }
}
