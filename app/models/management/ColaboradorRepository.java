package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Colaborador;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ColaboradorRepository extends AbstractRepository<Colaborador> {

    @Inject
    public ColaboradorRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Colaborador>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<Colaborador> list(EntityManager em) {
        return em.createNamedQuery("Colaborador.findAll", Colaborador.class).getResultList();
    }

    public CompletionStage<Colaborador> findByNombre(String nombre) {
        return supplyAsync(
              () -> jpaWrapper((em) -> findByNombre(nombre, em)),
              executionContext
        );
    }

    private Colaborador findByNombre(String nombre, EntityManager em) {
        return (Colaborador) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Colaborador.findByNombre", Colaborador.class)
                    .setParameter("nombre", nombre)
        );
    }
}
