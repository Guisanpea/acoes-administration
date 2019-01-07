package models.management;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public abstract class AbstractRepository<T> {
    private final JPAApi jpaApi;
    final DatabaseExecutionContext executionContext;

    @Inject
    public AbstractRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    public CompletionStage<T> add(T entity) {
        return supplyAsync(() -> jpaWrapper(em -> insert(em, entity)), executionContext);
    }

    public CompletionStage<T> delete(T entity) {
        return supplyAsync(() -> jpaWrapper(em -> remove(em, entity)), executionContext);
    }

    public CompletionStage<T> update(T entity) {
        return supplyAsync(() -> jpaWrapper(em -> update(em, entity)), executionContext);
    }   

    /**
     * This functions wraps a persistence operation to be done with the injected JPAApi of the class
     * @param function The operation to be done
     * @return The result of the operation
     */
    <U> U jpaWrapper(Function<EntityManager, U> function) {
        return jpaApi.withTransaction(function);
    }

    private T insert(EntityManager em, T entity) {
        em.persist(entity);
        return entity;
    }

    private T remove(EntityManager em, T entity) {
        em.remove(entity);
        return entity;
    }

    private T update(EntityManager em, T entity) {
        em.refresh(entity);
        return entity;
    }
}
