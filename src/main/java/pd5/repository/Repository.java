package pd5.repository;

import pd5.model.Identifiable;

import java.util.*;

public class Repository<E extends Identifiable<ID>, ID> {
    private final Map<ID, E> entities = new HashMap<>();

    public void save(E enity) {
        entities.put(enity.getID(), enity);
    }

    public Optional<E> findById(ID id){
        return Optional.ofNullable(entities.get(id));
    }

    public void deleteById(ID id){
        entities.remove(id);
    }

    public List<E> findAll(){
        return new ArrayList<>(entities.values());
    }
}
