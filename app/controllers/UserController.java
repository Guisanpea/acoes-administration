package controllers;

import beanUtils.PropertyUtils;
import models.entities.Usuario;
import models.management.UsuarioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_usuario;
import views.html.edit_usuario;
import views.html.index_usuarios;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(UserAuthenticator.class)
public class UserController extends Controller {

    private final UsuarioRepository usuarioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public UserController(UsuarioRepository usuarioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listUsers() {
        return usuarioRepository.list().thenApplyAsync(userList ->
                    ok(index_usuarios.render(userList))
              , httpExecutionContext.current()
        );
    }

    public Result renderCreateUser() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return ok(create_usuario.render(userForm));
    }

    @Security.Authenticated
    public CompletionStage<Result> createUser() {
        Usuario newUser = formFactory.form(Usuario.class).bindFromRequest("nombre", "email", "contrasena", "rol").get();

        return usuarioRepository.add(newUser).thenApplyAsync(user ->
                    redirect(routes.UserController.listUsers())
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> renderEditUser(Integer userId) {
        final Form<Usuario> userForm = formFactory.form(Usuario.class);

        return usuarioRepository.findById(userId).thenApplyAsync(user ->
                    ok(edit_usuario.render(userForm.fill(user), userId))
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editUser(Integer userId) {
        Usuario editedUser = formFactory.form(Usuario.class).bindFromRequest("id", "nombre", "email", "rol").get();

        return usuarioRepository.findById(userId).thenCompose(dbUser -> {
            PropertyUtils.copyNonNullProperties(editedUser, dbUser);

            return usuarioRepository.update(dbUser).thenApplyAsync(u ->
                  redirect(routes.UserController.listUsers())
            );
        });
    }
}
