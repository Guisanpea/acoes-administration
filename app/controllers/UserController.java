package controllers;

import lombok.SneakyThrows;
import models.entities.Usuario;
import models.management.UsuarioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.introducirusuario;
import views.html.modificarusuario;
import views.html.panelusuarios;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserController extends Controller {

    private UsuarioRepository usuarioRepository;
    private FormFactory formFactory;

    @Inject
    public UserController(UsuarioRepository usuarioRepository, FormFactory formFactory){
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
    }

    public Result listUsers(){
        List<Usuario> userList = usuarioRepository.list();

        return ok(panelusuarios.render(userList));
    }

    public Result renderEditUser(Integer userId){
        Form<Usuario> userForm = formFactory.form(Usuario.class);
        return ok(modificarusuario.render(userId, userForm));
    }

    public Result renderAddUser() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return ok(introducirusuario.render(userForm));
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    public Result editUser(Integer userId) {
        Usuario editedUser = formFactory.form(Usuario.class).bindFromRequest("nombre", "email", "contrasena", "rol").get();
        editedUser.setId(userId);

        usuarioRepository.update(editedUser).toCompletableFuture().get();

        return redirect(routes.UserController.listUsers());
    }

    public Result addUser() {
        Usuario newUser = formFactory.form(Usuario.class).bindFromRequest("nombre", "email", "contrasena", "rol").get();
        usuarioRepository.add(newUser);

        return redirect(routes.UserController.listUsers());
    }
}
