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
import java.util.concurrent.CompletionStage;

public class SessionController extends Controller {

    private final FormFactory formFactory;
    private final UsuarioRepository usuarioRepository;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public SessionController(FormFactory formFactory, UsuarioRepository usuarioRepository, HttpExecutionContext ec){
        this.formFactory = formFactory;
        this.usuarioRepository = usuarioRepository;
        this.httpExecutionContext = ec;
    }

    public Result index(){
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return ok(login.render(userForm, false));
    }

    public CompletionStage<Result> login(){
        Usuario formUser = formFactory.form(Usuario.class).bindFromRequest("email", "contrasena").get();

        return usuarioRepository.findByEmail(formUser.getEmail()).thenApplyAsync(databaseUser -> {
            if (userIsCorrect(formUser, databaseUser)){
                return listSocios(databaseUser);
            } else {
                return reLogin();
            }
        }, httpExecutionContext.current());
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
