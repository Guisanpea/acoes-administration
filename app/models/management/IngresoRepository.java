package models.management;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class RegistroRepository extends AbstractRepository<RegistroEconomico> {

    @Inject
    public RegistroRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<RegistroEconomico>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<RegistroEconomico> list(EntityManager em) {
        return em.createNamedQuery("RegistroEconomico.findAll", RegistroEconomico.class).getResultList();
    }
}
