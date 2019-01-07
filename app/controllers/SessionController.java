package controllers;

import models.entities.Usuario;
import models.management.UsuarioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

import javax.inject.Inject;
import java.util.Objects;

public class SessionController extends Controller {

    private final FormFactory formFactory;
    private final UsuarioRepository usuarioRepository;

    @Inject
    public SessionController(FormFactory formFactory, UsuarioRepository usuarioRepository){
        this.formFactory = formFactory;
        this.usuarioRepository = usuarioRepository;
    }

    public Result index(){
        Form<Usuario> userForm = formFactory.form(Usuario.class);
        return ok(login.render(userForm, false));
    }

    public Result login(){
        Result redirection;
        Usuario formUser = formFactory.form(Usuario.class).bindFromRequest("email", "contrasena").get();
        Usuario databaseUser = usuarioRepository.findByEmail(formUser.getEmail());

        if (userIsCorrect(formUser, databaseUser)){
            redirection = listSocios(databaseUser);
        } else {
            redirection = reLogin();
        }

        return redirection;
    }

    private boolean userIsCorrect(Usuario formUser, Usuario databaseUser) {
        return Objects.nonNull(databaseUser)
              && formUser.getContrasena().equals(databaseUser.getContrasena());
    }

    private Result listSocios(Usuario databaseUser) {
        createUserSession(databaseUser);
        return redirect(routes.AgentController.listSocios());
    }

    private void createUserSession(Usuario databaseUser) {
        String userId = String.valueOf(databaseUser.getId());
        session("userId", userId);
    }

    private Result reLogin() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return unauthorized(login.render(userForm, true));
    }
}
