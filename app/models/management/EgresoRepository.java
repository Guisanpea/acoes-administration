package models.management;

import models.entities.Egreso;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class EgresoRepository extends AbstractRepository<Egreso> {

    @Inject
    public EgresoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Egreso>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Egreso> list(EntityManager em) {
        return em.createNamedQuery("Egreso.findAll", Egreso.class).getResultList();
    }

    public CompletionStage<Egreso> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext);
    }

    private Egreso findById(int id, EntityManager em) {
        return (Egreso) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Egreso.findById", Egreso.class)
                        .setParameter("id", id)
        );
    }
}
