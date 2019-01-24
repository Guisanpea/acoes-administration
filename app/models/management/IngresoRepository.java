package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Ingreso;
import play.db.jpa.JPAApi;
import models.ResultHelpers.JpaResultHelper;
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

    public CompletionStage<Ingreso> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext);
    }

    private Ingreso findById(int id, EntityManager em) {
        return (Ingreso) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Ingreso.findById", Ingreso.class)
                        .setParameter("id", id)
        );
    }
}
