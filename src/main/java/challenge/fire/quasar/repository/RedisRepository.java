package challenge.fire.quasar.repository;

import challenge.fire.quasar.domain.Satellite;

import java.util.Map;
public interface RedisRepository {
    void save(Satellite satellite);
    Map<String,Satellite> findAll();
    Satellite findByName(String name);
    void delete(String name);
}
