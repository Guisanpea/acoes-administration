package controllers;

import beanUtils.PropertyUtils;
import models.entities.Proyecto;
import models.management.ProyectoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_proyecto;
import views.html.edit_proyecto;
import views.html.index_proyectos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(ProyectoAuthenticator.class)
public class ProyectoController extends Controller {

    private final ProyectoRepository proyectoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public ProyectoController(ProyectoRepository proyectoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.proyectoRepository = proyectoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listProyectos() {
        return proyectoRepository.list().thenApplyAsync(proyectosList ->
                        ok(index_proyectos.render(proyectosList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreateProyecto() {
        Form<Proyecto> proyectoForm = formFactory.form(Proyecto.class);

        return ok(create_proyecto.render(proyectoForm));
    }

    // TODO Asignar rol,dado que ahora no se puede crear un proyecto sin estar logueado
    @Security.Authenticated
    public CompletionStage<Result> createProyecto() {
        Proyecto newProyecto = formFactory.form(Proyecto.class).bindFromRequest("nombre", "tipo_proyecto", "region_ayuda").get();

        return proyectoRepository.add(newProyecto).thenApplyAsync(proyecto ->
                        redirect(routes.ProyectoController.listProyectos())
                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> renderEditProyecto(Integer proyectoId) {
        final Form<Proyecto> proyectoForm = formFactory.form(Proyecto.class);

        return proyectoRepository.findById(proyectoId).thenApplyAsync(proyecto ->
                        ok(edit_proyecto.render(proyectoForm.fill(proyecto), proyectoId))
                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editProyecto(Integer proyectoId) {
        proyecto editedProyecto = formFactory.form(Proyecto.class).bindFromRequest("id", "nombre", "tipo_proyecto", "region_ayuda").get();

        return proyectoRepository.findById(proyectoId).thenCompose(dbProyecto -> {
            PropertyUtils.copyNonNullProperties(editedProyecto, dbProyecto);

            return proyectoRepository.update(dbProyecto).thenApplyAsync(u ->
                    redirect(routes.ProyectoController.listProyectos())
            );
        });
    }
}



