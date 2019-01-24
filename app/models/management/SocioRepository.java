package models.management;

import models.ResultHelpers.JpaResultHelper;
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
              executionContext
        );
    }

    private List<Socio> list(EntityManager em) {
        return em.createNamedQuery("Socio.findAll", Socio.class).getResultList();
    }
    public CompletionStage<Socio> findById(int id) {
        return supplyAsync(
              () -> jpaWrapper( (em) -> findById(id, em) ),
              executionContext
        );
    }

    private Socio findById(int id, EntityManager em) {
        return (Socio) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Socio.findByNumeroDeSocio", Socio.class)
                    .setParameter("numeroDeSocio", id)
        );
    }
}
