package controllers;

import models.entities.Usuario;
import models.management.UsuarioRepository;
import org.apache.commons.lang3.RandomStringUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.login;

import com.sendgrid.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class SessionController extends Controller {

    private final FormFactory formFactory;
    private final UsuarioRepository usuarioRepository;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public SessionController(FormFactory formFactory, UsuarioRepository usuarioRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.usuarioRepository = usuarioRepository;
        this.httpExecutionContext = ec;
    }

    public Result index() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);
        System.out.println("\n\n\n"+ httpExecutionContext.current());
        return ok(login.render(userForm, false));
    }

    public CompletionStage<Result> login() {
        Usuario formUser = formFactory.form(Usuario.class).bindFromRequest("email", "contrasena").get();

        return usuarioRepository.findByEmail(formUser.getEmail()).thenApplyAsync(user -> {
            if (userIsCorrect(formUser, user)) {
                return redirectAndSetSession(user);
            } else {
                return reLogin();
            }
        }, httpExecutionContext.current());
    }

    private boolean userIsCorrect(Usuario formUser, Usuario databaseUser) {
        return Objects.nonNull(databaseUser)
              && formUser.getContrasena().equals(databaseUser.getContrasena());
    }

    private Result redirectAndSetSession(Usuario user) {
        createUserSession(user);
        //TODO finish roles
        switch (user.getRol()) {
            case Agente:
                return redirect(routes.SocioController.listSocios());
            case GerenteSede:
                return TODO;
            case GerenteRegional:
                return TODO;
            case CoordinadorLocal:
                return redirect(routes.AlumnoController.listAlumnos());
            case CoordinadorGeneral:
                return redirect(routes.AlumnoController.listAlumnos());
            case AdministradorLocal:
                return TODO;
            case AdministradorGeneral:
                return TODO;
            default:
                return internalServerError("Unhandled Role");
        }
    }

    private void createUserSession(Usuario databaseUser) {
        String userId = String.valueOf(databaseUser.getId());
        session("email", databaseUser.getEmail());
    }

    private Result reLogin() {
        Form<Usuario> userForm = formFactory.form(Usuario.class);

        return unauthorized(login.render(userForm, true));
    }

    @Security.Authenticated(UserAuthenticator.class)
    public Result logOut() {
        session().clear();
        flash("message", "Has cerrado sesión correctamente");
        return redirect(routes.SessionController.index());
    }

    public Result recoverPassword(String email) {
        Optional.ofNullable(usuarioRepository.findByEmail(email))
              .ifPresent(user -> sendEmail(email));
        return ok();
    }

    private void sendEmail(String email) {
        Email from = new Email("acoes@realsolutionsuma.me");
        String subject = "Solicitud de cambio de contraseña en ACOES";
        Email to = new Email(email);
        String randomPassword = createRandomPassword();
        String text = "Se ha solicitado un cambio de contraseña para su cuenta, su nueva contraseña es: " + randomPassword;
        Content content = new Content("text/plain", text);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("");
        boolean noErrors = sendEmail(mail, sg);

        if (noErrors) {
            usuarioRepository.findByEmail(email).thenAccept(usuario -> {
                      usuario.setContrasena(randomPassword);
                      usuarioRepository.update(usuario);
                  }
            );
        }
    }

    private boolean sendEmail(Mail mail, SendGrid sg) {
        Request request = new Request();
        boolean noErrors = true;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            noErrors = false;
        }
        return noErrors;
    }

    private String createRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
