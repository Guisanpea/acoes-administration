package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Region;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class RegionRepository extends AbstractRepository<Region> {

    @Inject
    public RegionRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Region>> list() {
        return supplyAsync(
                () -> jpaWrapper(this::list),
                executionContext
        );
    }

    private List<Region> list(EntityManager em) {
        return em.createNamedQuery("Region.findAll", Region.class).getResultList();
    }

    public CompletionStage<Region> findById(int id) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findById(id, em) ),
                executionContext
        );
    }

    private Region findById(int id, EntityManager em) {
        return (Region) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Region.findById", Region.class)
                        .setParameter("id", id)
        );
    }

    public CompletionStage<Region> findByNombre(String nombre) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findByNombre(nombre, em) ),
                executionContext
        );
    }

    private Region findByNombre(String nombre, EntityManager em) {
        return (Region) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("Region.findByNombre", Region.class)
                        .setParameter("nombre", nombre)
        );
    }

}