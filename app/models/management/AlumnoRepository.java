package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Alumno;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AlumnoRepository extends AbstractRepository<Alumno> {

    @Inject
    public AlumnoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Alumno>> list() {
        return supplyAsync(
                () -> jpaWrapper(this::list),
                executionContext
        );
    }

    private List<Alumno> list(EntityManager em) {
        return em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
    }

    public CompletionStage<Alumno> findById(int id) {
        return supplyAsync(
              () -> jpaWrapper( (em) -> findById(id, em) ),
              executionContext
        );
    }

    private Alumno findById(int id, EntityManager em) {
        return (Alumno) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Alumno.findByCodigo", Alumno.class)
                    .setParameter("codigo", id)
        );
    }

    public CompletionStage<Alumno> findByNombre(String nombre) {
        return supplyAsync(
              () -> jpaWrapper( (em) -> findByNombre(nombre, em)),
              executionContext
        );
    }

    private Alumno findByNombre(String nombre, EntityManager em) {
        return (Alumno) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Alumno.findByNombre", Alumno.class)
                    .setParameter("nombre", nombre)
        );
    }
}
