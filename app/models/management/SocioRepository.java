package models.management;

import models.entities.Socio;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SocioRepository extends AbstractRepository<Socio> {

    @Inject
    public SocioRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Socio>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Socio> list(EntityManager em) {
        return em.createNamedQuery("Socio.findAll", Socio.class).getResultList();
    }
}
