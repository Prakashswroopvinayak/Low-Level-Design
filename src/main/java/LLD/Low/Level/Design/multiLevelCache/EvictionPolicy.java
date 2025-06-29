package LLD.Low.Level.Design.multiLevelCache;
import java.util.*;

public interface EvictionPolicy {
    void keyAccessed(String key);
    void keyInserted(String key);
    void keyRemoved(String key);
    String evictKey();
}
