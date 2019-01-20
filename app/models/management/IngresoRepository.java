package models.management;

import models.entities.Ingreso;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class IngresoRepository extends AbstractRepository<Ingreso> {

    @Inject
    public IngresoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Ingreso>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Ingreso> list(EntityManager em) {
        return em.createNamedQuery("Ingreso.findAll", Ingreso.class).getResultList();
    }
}
