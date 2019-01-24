package models.management;

import models.ResultHelpers.JpaResultHelper;
import models.entities.Usuario;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UsuarioRepository extends AbstractRepository<Usuario> {

    @Inject
    public UsuarioRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    public CompletionStage<List<Usuario>> list() {
        return supplyAsync(
              () -> jpaWrapper(this::list),
              executionContext
        );
    }

    private List<Usuario> list(EntityManager em) {
        return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }

    public CompletionStage<Usuario> findByEmail(String email) {
        return supplyAsync(
              () -> jpaWrapper(em -> findByEmail(email, em)),
              executionContext
        );
    }

    private Usuario findByEmail(String email, EntityManager em) {
        return (Usuario) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", email)
        );
    }

    public CompletionStage<Usuario> findById(int id) {
        return supplyAsync(
              () -> jpaWrapper(em -> findById(id, em)),
              executionContext
        );
    }

    private Usuario findById(int id, EntityManager em) {
        return (Usuario) JpaResultHelper.getSingleResultOrNull(
              em.createNamedQuery("Usuario.findById", Usuario.class)
                    .setParameter("id", id)
        );
    }

}
