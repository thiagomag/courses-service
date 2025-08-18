package br.com.thiagomagdalena.coursesservice.persistance.repository;

import br.com.thiagomagdalena.coursesservice.persistance.domain.BaseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseReactiveCrudRepository<T extends BaseEntity<ID>, ID> extends ReactiveCrudRepository<T, ID> {

    @Override
    default Flux<T> findAll() {
        return findByDeletedTmspIsNull();
    }

    Flux<T> findByDeletedTmspIsNull();

    @Override
    default Mono<T> findById(ID id) {
        return findByIdAndDeletedTmspIsNull(id);
    }

    Mono<T> findByIdAndDeletedTmspIsNull(ID id);

    default Mono<T> softDelete(T entity) {
        entity.delete();
        return save(entity);
    }

    default Flux<T> softDeleteAll(Iterable<T> entities) {
        entities.forEach(BaseEntity::delete);
        return saveAll(entities);
    }
}
