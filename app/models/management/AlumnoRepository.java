package models.management;

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
                executionContext);
    }

    private List<Alumno> list(EntityManager em) {
        return em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
    }
}
