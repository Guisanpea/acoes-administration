package controllers;

import models.entities.Sede;
import models.entities.Usuario;
import models.management.SedeRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.hello;
import views.html.login;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final SedeRepository sedeRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public HomeController(SedeRepository sedeRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.sedeRepository = sedeRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Form<Usuario> usuarioForm = formFactory.form(Usuario.class);
        return ok(login.render(usuarioForm, false));
    }

    public Result hello() {
        return ok(hello.render());
    }

    public CompletionStage<Result> newHeadquarter() {
        Sede sede = new Sede();
        return sedeRepository.add(sede).thenApplyAsync(newSede ->
                    ok(toJson(newSede))
              , httpExecutionContext.current()
        );
    }
}
