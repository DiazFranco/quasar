package challenge.fire.quasar.repository;

import challenge.fire.quasar.domain.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
@Repository
public class SatelliteRepository implements RedisRepository{

    private static final String KEY = "Satellite";

    private RedisTemplate<String,Satellite> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public SatelliteRepository(RedisTemplate<String, Satellite> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void save(Satellite satellite) {
        try {
            hashOperations.put(KEY, satellite.getName(), satellite);
        }catch (Exception e){
        e.printStackTrace();
        }
    }

    @Override
    public Map<String,Satellite> findAll() {
        return (Map<String, Satellite>) hashOperations.entries(KEY);
    }

    @Override
    public Satellite findByName(String name) {
        return (Satellite) hashOperations.get(KEY,name);
    }

    @Override
    public void delete(String name) {
        hashOperations.delete(KEY,name);
    }
}
