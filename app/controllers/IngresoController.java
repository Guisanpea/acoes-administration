package controllers;

import models.entities.Ingreso;
import models.entities.Proyecto;
import models.management.IngresoRepository;
import models.management.ProyectoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_ingreso;
import views.html.index_ingresos;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class IngresoController extends Controller {

    private final IngresoRepository ingresoRepository;
    private final ProyectoRepository proyectoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public IngresoController(IngresoRepository ingresoRepository, ProyectoRepository proyectoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.ingresoRepository = ingresoRepository;
        this.proyectoRepository = proyectoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }


    public CompletionStage<Result> listIngresos() {
        return ingresoRepository.list().thenApplyAsync(ingresoList ->
              ok(index_ingresos.render(ingresoList))
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> renderCreateIngreso() {
        Form<Ingreso> ingresoForm = formFactory.form(Ingreso.class);
        return proyectoRepository.list().thenApply(proyectos -> {
            List<String> proyectosNames = proyectos.stream()
                  .map(Proyecto::getNombre)
                  .collect(Collectors.toCollection(ArrayList::new));
            return TODO;
            return ok(create_ingreso.render(ingresoForm, proyectos));
        });
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
