package models.management;

import models.entities.Sede;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SedeRepository extends AbstractRepository<Sede> {

    @Inject
    public SedeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Sede>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Sede> list(EntityManager em) {
        return em.createNamedQuery("Sede.findAll", Sede.class).getResultList();
    }
}
