package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Tercero;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class TerceroRepository {

    @Inject
    public TerceroRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Tercero>> list() {
        return supplyAsync(
                () -> jpaWrapper(this::list),
                executionContext
        );
    }

    private List<Tercero> list(EntityManager em) {
        return em.createNamedQuery("Tercero.findAll", Tercero.class).getResultList();
    }
    public CompletionStage<Tercero> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext
        );
    }

    private Tercero findById(int id, EntityManager em) {
        return (Tercero) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Tercero.findById", Tercero.class)
                        .setParameter("id", id)
        );
    }

    public CompletionStage<Tercero> findByNombre(String nombre) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findByNombre(nombre, em) ),
                executionContext
        );
    }

    private Tercero findByNombre(String nombre, EntityManager em) {
        return (Tercero) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Tercero.findByNombre", Tercero.class)
                        .setParameter("nombre", nombre)
        );
    }

}
