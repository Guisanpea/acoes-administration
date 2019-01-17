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

        return usuarioRepository.findByEmail(formUser.getEmail()).thenApplyAsync( user -> {
            if (userIsCorrect(formUser, user)){
                return redirectAndSetSession(user);
            } else {
                return reLogin();
            }
        }, httpExecutionContext.current());
    }

    private Result redirectAndSetSession(Usuario user) {
        createUserSession(user);
        switch (user.getRol()){
            case Agente:
                return redirect(routes.AgentController.listSocios());
            case GerenteSede:
                return redirect(routes.RegistroEconomicoController.listRegistrosEconomicos());
            case GerenteRegional:
                return redirect(routes.RegistroEconomicoController.listRegistrosEconomicos());
            case CoordinadorLocal:
                return redirect(routes.AlumnoController.listAlumnos());
            case CoordinadorGeneral:
                return redirect(routes.AlumnoController.listAlumnos());
            case AdministradorLocal:
                return redirect(routes.RegistroEconomicoController.listRegistrosEconomicos());
            case AdministradorGeneral:
                return redirect(routes.RegistroEconomicoController.listRegistrosEconomicos());
            default:
                return internalServerError("Unhandled Role");
        }
    }

    private boolean userIsCorrect(Usuario formUser, Usuario databaseUser) {
        return Objects.nonNull(databaseUser)
              && formUser.getContrasena().equals(databaseUser.getContrasena());
    }

    private Result listSocios(Usuario databaseUser) {
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
