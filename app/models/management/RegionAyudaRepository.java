package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.RegionAyuda;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class RegionAyudaRepository extends AbstractRepository<RegionAyuda> {

    @Inject
    public RegionAyudaRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<RegionAyuda>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<RegionAyuda> list(EntityManager em) {
        return em.createNamedQuery("RegionAyuda.findAll", RegionAyuda.class).getResultList();
    }

    public CompletionStage<RegionAyuda> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext
        );
    }

    private RegionAyuda findById(int id, EntityManager em) {
        return (RegionAyuda) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("RegionAyuda.findById", RegionAyuda.class)
                        .setParameter("id", id)
        );
    }

    public CompletionStage<RegionAyuda> findByNombre(String nombre) {
        return supplyAsync(
              () -> jpaWrapper( (em) -> findByNombre(nombre, em) ),
              executionContext
        );
    }

    private RegionAyuda findByNombre(String nombre, EntityManager em) {
        return (RegionAyuda) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("RegionAyuda.findByNombre", RegionAyuda.class)
                    .setParameter("nombre", nombre)
        );
    }

}
