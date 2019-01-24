package models.management;

import models.entities.Apadrinamiento;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ApadrinamientoRepository extends AbstractRepository<Apadrinamiento> {

    @Inject
    public ApadrinamientoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Apadrinamiento>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<Apadrinamiento> list(EntityManager em) {
        return em.createNamedQuery("Apadrinamiento.findAll", Apadrinamiento.class).getResultList();
    }

}
