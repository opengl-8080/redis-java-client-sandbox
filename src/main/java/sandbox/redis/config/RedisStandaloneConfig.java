package sandbox.redis.config;

public class RedisStandaloneConfig {
    public RedisHost hostConfig;
    
    public RedisStandaloneConfig host(String host) {
        this.hostConfig = new RedisHost(host);
        return this;
    }

    public RedisStandaloneConfig hostPort(String host, int port) {
        this.hostConfig = new RedisHost(host, port);
        return this;
    }
}
