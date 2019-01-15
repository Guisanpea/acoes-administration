package controllers;

import models.entities.Usuario;
import models.management.UsuarioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.introducirusuario;
import views.html.modificarusuario;
import views.html.panelusuarios;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

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
                    ok(panelusuarios.render(userList))
              , httpExecutionContext.current()
        );
    }

    public Result renderAddUser() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return ok(introducirusuario.render(userForm));
    }

    public CompletionStage<Result> renderEditUser(Integer userId) {
        final Form<Usuario> userForm = formFactory.form(Usuario.class);

        return usuarioRepository.findById(userId).thenApplyAsync(user ->
                    ok(modificarusuario.render(userForm.fill(user)))
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editUser() {
        Usuario editedUser = formFactory.form(Usuario.class).bindFromRequest("id", "nombre", "email", "rol").get();

        return usuarioRepository.findById(editedUser.getId()).thenCompose( dbUser -> {
            dbUser.setNombre(editedUser.getNombre());
            dbUser.setRol(editedUser.getRol());
            dbUser.setEmail(editedUser.getEmail());

            return usuarioRepository.update(dbUser).thenApplyAsync( u ->
                  redirect(routes.UserController.listUsers())
            );
        });
    }

    public CompletionStage<Result> addUser() {
        Usuario newUser = formFactory.form(Usuario.class).bindFromRequest("nombre", "email", "contrasena", "rol").get();

        return usuarioRepository.add(newUser).thenApplyAsync(user ->
                    redirect(routes.UserController.listUsers())
              , httpExecutionContext.current()
        );
    }
}
