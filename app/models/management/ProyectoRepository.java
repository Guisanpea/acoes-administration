package models.management;

import lombok.SneakyThrows;
import models.ResultHelpers.JpaResultHelper;
import models.entities.Proyecto;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProyectoRepository extends AbstractRepository<Proyecto> {

    @Inject
    public ProyectoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public List<Proyecto> list() {
        return supplyAsync(
                () -> jpaWrapper(em -> list(em)),
                executionContext).get();
    }

    private List<Proyecto> list(EntityManager em) {
        return em.createNamedQuery("Proyecto.findAll", Proyecto.class).getResultList();
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public Proyecto findById(int id) {
        return supplyAsync(
                () -> jpaWrapper(em -> findById(id)),
                executionContext)
                .get();
    }

    private Proyecto findById(int id, EntityManager em) {
        return (Proyecto) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Proyecto.findById")
                        .setParameter("id", id)
        );
    }
}
