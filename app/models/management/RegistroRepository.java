package models.management;

import lombok.SneakyThrows;
import models.entities.RegistroEconomico;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class RegistroRepository extends AbstractRepository<RegistroEconomico> {

    @Inject
    public RegistroRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public List<RegistroEconomico> list() {
        return supplyAsync(
              () -> jpaWrapper(em -> list(em)),
              executionContext)
              .get();
    }

    private List<RegistroEconomico> list(EntityManager em) {
        return em.createNamedQuery("RegistroEconomico.findAll", RegistroEconomico.class).getResultList();
    }
}
