package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.TipoProyecto;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class TipoProyectoRepository extends AbstractRepository<TipoProyecto> {

    @Inject
    public TipoProyectoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<TipoProyecto>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<TipoProyecto> list(EntityManager em) {
        return em.createNamedQuery("TipoProyecto.findAll", TipoProyecto.class).getResultList();
    }
    public CompletionStage<TipoProyecto> findById(int id) {
        return supplyAsync(
              () -> jpaWrapper( (em) -> findById(id, em) ),
              executionContext
        );
    }

    private TipoProyecto findById(int id, EntityManager em) {
        return (TipoProyecto) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("TipoProyecto.findById", TipoProyecto.class)
                    .setParameter("id", id)
        );
    }

    public CompletionStage<TipoProyecto> findByNombre(String nombre) {
        return supplyAsync(
                () -> jpaWrapper( (em) -> findByNombre(nombre, em) ),
                executionContext
        );
    }

    private TipoProyecto findByNombre(String nombre, EntityManager em) {
        return (TipoProyecto) JpaResultHelper.getSingleResultOrNull(
                em.createNamedQuery("TipoProyecto.findByNombre", TipoProyecto.class)
                        .setParameter("nombre", nombre)
        );
    }
}
