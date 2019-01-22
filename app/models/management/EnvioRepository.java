package models.management;

import models.entities.Envio;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class EnvioRepository extends AbstractRepository<Envio> {

    @Inject
    public EnvioRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Envio>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Envio> list(EntityManager em) {
        return em.createNamedQuery("Envio.findAll", Envio.class).getResultList();
    }

}
