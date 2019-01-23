package controllers;

import beanUtils.PropertyUtils;
import models.entities.TipoProyecto;
import models.management.TipoProyectoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_tipoProyecto;
import views.html.edit_tipoProyecto;
import views.html.index_tiposProyecto;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(TipoProyectoAuthenticator.class)
public class TipoProyectoController extends Controller {

    private final TipoProyectoRepository tipoProyectoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public TipoProyectoController(TipoProyectoRepository tipoProyectoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listTiposProyecto() {
        return tipoProyectoRepository.list().thenApplyAsync(tiposList ->
                    ok(index_tiposProyecto.render(tiposList))
              , httpExecutionContext.current()
        );
    }

    public Result renderCreateTipoProyecto() {
        Form<TipoProyecto> tipoForm = formFactory.form(TipoProyecto.class);

        return ok(create_tipoProyecto.render(tipoForm));
    }

    @Security.Authenticated
    public CompletionStage<Result> createTipoProyecto() {
        TipoProyecto newTipoProyecto = formFactory.form(TipoProyecto.class).bindFromRequest("nombre", "descripcion").get();

        return tipoProyectoRepository.add(newTipoProyecto).thenApplyAsync(tipoProyecto ->
                    redirect(routes.TipoProyectoController.listTiposProyecto())
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> renderEditTipoProyecto(Integer tipoId) {
        final Form<TipoProyecto> tipoForm = formFactory.form(TipoProyecto.class);

        return tipoProyectoRepository.findById(tipoId).thenApplyAsync(tipo ->
                    ok(edit_tipoProyecto.render(tipoForm.fill(tipo), tipoId))
              , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editTipoProyecto(Integer tipoId) {
        TipoProyecto editedTipoProyecto = formFactory.form(TipoProyecto.class).bindFromRequest("id", "nombre", "descripcion").get();

        return tipoProyectoRepository.findById(tipoId).thenCompose(dbTipoProyecto -> {
            PropertyUtils.copyNonNullProperties(editedTipoProyecto, dbTipoProyecto);

            return tipoProyectoRepository.update(dbTipoProyecto).thenApplyAsync(u ->
                  redirect(routes.TipoProyectoController.listTiposProyecto())
            );
        });
    }
}
