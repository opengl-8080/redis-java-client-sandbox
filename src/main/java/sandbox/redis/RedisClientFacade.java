package sandbox.redis;

import java.io.Closeable;

public interface RedisClientFacade extends Closeable {
    
    void set(String key, String value);
    
    String get(String key);
    
    @Override
    void close();
}
