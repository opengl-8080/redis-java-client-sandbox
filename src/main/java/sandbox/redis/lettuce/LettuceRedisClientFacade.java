package sandbox.redis.lettuce;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import sandbox.redis.RedisClientFacade;

public class LettuceRedisClientFacade implements RedisClientFacade {
    private final RedisClient client;
    private final StatefulRedisMasterReplicaConnection<String, String> connection;

    public LettuceRedisClientFacade(RedisURI uri, ClientOptions options) {
        this.client = RedisClient.create();
        this.client.setOptions(options);
        this.connection = MasterReplica.connect(this.client, StringCodec.UTF8, uri);
        this.connection.setReadFrom(ReadFrom.REPLICA);
    }

    @Override
    public void set(String key, String value) {
        connection.sync().set(key, value);
    }

    @Override
    public String get(String key) {
        return connection.sync().get(key);
    }

    @Override
    public void close() {
        this.connection.close();
        this.client.shutdown();
    }
}
