package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Proyecto;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProyectoRepository extends AbstractRepository<Proyecto> {

    @Inject
    public ProyectoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Proyecto>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Proyecto> list(EntityManager em) {
        return em.createNamedQuery("Proyecto.findAll", Proyecto.class).getResultList();
    }

    public CompletionStage<Proyecto> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext);
    }

    private Proyecto findById(int id, EntityManager em) {
        return (Proyecto) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Proyecto.findById", Proyecto.class)
                        .setParameter("id", id)
        );
    }


}
