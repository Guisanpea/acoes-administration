package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Sede;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SedeRepository extends AbstractRepository<Sede> {

    @Inject
    public SedeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Sede>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<Sede> list(EntityManager em) {
        return em.createNamedQuery("Sede.findAll", Sede.class).getResultList();
    }

    public CompletionStage<Sede> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext
        );
    }

    private Sede findById(int id, EntityManager em) {
        return (Sede) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Sede.findById", Sede.class)
                        .setParameter("id", id)
        );
    }

    public CompletionStage<Sede> findByNombre(String nombre) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findByNombre(nombre, em) ),
                executionContext
        );
    }

    private Sede findByNombre(String nombre, EntityManager em) {
        return (Sede) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Sede.findByNombre", Sede.class)
                        .setParameter("nombre", nombre)
        );
    }
}
