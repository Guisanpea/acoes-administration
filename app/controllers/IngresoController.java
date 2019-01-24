package controllers;

import models.entities.Ingreso;
import models.entities.Partida;
import models.entities.Proyecto;
import models.management.IngresoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index_ingresos;
import views.html.create_ingreso;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class IngresoController extends Controller {

    private final IngresoRepository ingresoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public IngresoController(IngresoRepository ingresoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.ingresoRepository = ingresoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }


    public CompletionStage<Result> listIngresos() {
        return ingresoRepository.list().thenApplyAsync(ingresoList ->
              ok(index_ingresos.render(ingresoList))
              , httpExecutionContext.current()
        );
    }

    public Result renderCreateIngreso() {
        Form<Ingreso> ingresoForm = formFactory.form(Ingreso.class);

        return ok(create_ingreso.render(ingresoForm));
    }

    public CompletionStage<Result> createIngreso() {
        Ingreso newIngreso = formFactory.form(Ingreso.class).bindFromRequest(
              "fecha", "concepto", "importe",
              "emisor", "observaciones", "partida",
              "proyecto", "creador", "validado", "responsable").get();

        return ingresoRepository.add(newIngreso).thenApplyAsync(ingreso ->
              redirect(routes.IngresoController.listIngresos())
              , httpExecutionContext.current()
        );
    }

}
