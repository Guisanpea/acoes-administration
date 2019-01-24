package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Partida;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PartidaRepository extends AbstractRepository<Partida> {

    @Inject
    public PartidaRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Partida>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext);
    }

    private List<Partida> list(EntityManager em) {
        return em.createNamedQuery("Partida.findAll", Partida.class).getResultList();
    }

    public CompletionStage<Partida> findById(int id) {
        return supplyAsync(
              () -> jpaWrapper((em) -> findById(id, em)),
              executionContext);
    }

    private Partida findById(int id, EntityManager em) {
        return (Partida) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Partida.findById", Partida.class)
                    .setParameter("id", id)
        );
    }

    public CompletionStage<Partida> findByNombre(String nombre) {
        return supplyAsync(
              () -> jpaWrapper((em) -> findByName(nombre, em)),
              executionContext);
    }

    private Partida findByName(String nombre, EntityManager em) {
        return (Partida) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Partida.findByNombre", Partida.class)
                    .setParameter("nombre", nombre)
        );
    }


}
