package models.management;

import lombok.SneakyThrows;
import models.entities.Sede;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SedeRepository extends AbstractRepository<Sede> {

    @Inject
    public SedeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public List<Sede> list() {
        return supplyAsync(
              () -> jpaWrapper(em -> list(em)),
              executionContext)
              .get();
    }

    private List<Sede> list(EntityManager em) {
        return em.createNamedQuery("Sede.findAll", Sede.class).getResultList();
    }
}
