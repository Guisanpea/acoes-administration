package models.management;

import lombok.SneakyThrows;
import models.entities.Alumno;
import models.entities.RegistroEconomico;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AlumnoRepository extends AbstractRepository<Alumno> {

    @Inject
    public AlumnoRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public List<Alumno> list() {
        return supplyAsync(
                () -> jpaWrapper(em -> list(em)),
                executionContext)
                .get();
    }

    private List<Alumno> list(EntityManager em) {
        return em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
    }
}
