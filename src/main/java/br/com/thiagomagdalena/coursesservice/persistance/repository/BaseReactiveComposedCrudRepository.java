package br.com.thiagomagdalena.coursesservice.persistance.repository;


import br.com.thiagomagdalena.coursesservice.persistance.domain.BaseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseReactiveComposedCrudRepository<T extends BaseEntity<ID>, ID> {

    BaseReactiveCrudRepository<T, ID> getRepository();

    default Mono<T> save(T entity) {
        return getRepository().save(entity);
    }

    default Flux<T> saveAll(Iterable<T> entities) {
        return getRepository().saveAll(entities);
    }

    default Mono<T> softDelete(T entity) {
        return getRepository().softDelete(entity);
    }

    default Flux<T> softDeleteAll(Iterable<T> entities) {
        return getRepository().softDeleteAll(entities);
    }

    default Flux<T> findAll() {
        return getRepository().findAll();
    }

    default Mono<T> findById(ID id) {
        return getRepository().findById(id);
    }

    default Mono<Void> delete(T entity) {
        return getRepository().delete(entity);
    }

    default Mono<Void> deleteAll(Iterable<T> entities) {
        return getRepository().deleteAll(entities);
    }

}
