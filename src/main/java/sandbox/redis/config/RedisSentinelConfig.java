package sandbox.redis.config;

import java.util.HashSet;
import java.util.Set;

public class RedisSentinelConfig {
    public Set<RedisSentinel> sentinels = new HashSet<>();
    public String masterId;
    
    public RedisSentinelConfig masterId(String masterId) {
        this.masterId = masterId;
        return this;
    }
    
    public RedisSentinelConfig addSentinel(String host) {
        this.sentinels.add(new RedisSentinel(host));
        return this;
    }

    public RedisSentinelConfig addSentinel(String host, int port) {
        this.sentinels.add(new RedisSentinel(host, port));
        return this;
    }
}
