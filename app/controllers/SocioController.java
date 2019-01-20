package controllers;

import models.entities.Socio;
import models.management.SocioRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_socio;
import views.html.index_socios;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class SocioController extends Controller {

    private final SocioRepository socioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public SocioController(SocioRepository socioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.socioRepository = socioRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listSocios() {
        return socioRepository.list().thenApplyAsync(socioList ->
                    ok(index_socios.render(socioList))
              , httpExecutionContext.current()
        );
    }

    public Result renderCreateSocio() {
        Form<Socio> socioForm = formFactory.form(Socio.class);

        return ok(create_socio.render(socioForm));
    }

    public CompletionStage<Result> createSocio() {
        Socio newSocio = formFactory.form(Socio.class).bindFromRequest(
              "nombre", "apellidos", "estado", "nif",
              "direccion", "poblacion",
              "codigoPostal", "provincia", "telefonoFijo", "telefonoMovil",
              "email", "relacion", "certificado", "sector",
              "fechaAlta", "fechaBaja", "observaciones").get();

        return socioRepository.add(newSocio).toCompletableFuture().thenApplyAsync(socio ->
                    redirect(routes.SocioController.listSocios())
              , httpExecutionContext.current()
        );
    }
}
