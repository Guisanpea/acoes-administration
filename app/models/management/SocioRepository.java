package models.management;

import lombok.SneakyThrows;
import models.entities.Sede;
import models.entities.Socio;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SocioRepository extends AbstractRepository<Socio> {

    @Inject
    public SocioRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public List<Socio> list() {
        return supplyAsync(
              () -> jpaWrapper(em -> list(em)),
              executionContext)
              .get();
    }

    private List<Socio> list(EntityManager em) {
        return em.createNamedQuery("Socio.findAll", Socio.class).getResultList();
    }
}
