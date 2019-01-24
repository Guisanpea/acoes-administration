package controllers;

import beanUtils.PropertyUtils;
import models.entities.Partida;
import models.management.PartidaRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_partida;
import views.html.edit_partida;
import views.html.index_partidas;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(PartidaAuthenticator.class)
public class PartidaController extends Controller {

    private final PartidaRepository partidaRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public PartidaController(PartidaRepository partidaRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.partidaRepository = partidaRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listPartidas() {
        return partidaRepository.list().thenApplyAsync(partidasList ->
                        ok(index_partidas.render(partidasList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreatePartida() {
        Form<Partida> partidaForm = formFactory.form(Partida.class);

        return ok(create_partida.render(partidaForm));
    }

    // TODO Asignar rol,dado que ahora no se puede crear un partida sin estar logueado
    //@Security.Authenticated
    public CompletionStage<Result> createPartida() {
        Partida newPartida = formFactory.form(Partida.class).bindFromRequest("nombre", "tipo_partida", "region_ayuda").get();

        return partidaRepository.add(newPartida).thenApplyAsync(partida ->
                        redirect(routes.PartidaController.listPartidas())
                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> renderEditPartida(Integer partidaId) {
        final Form<Partida> partidaForm = formFactory.form(Partida.class);

        return partidaRepository.findById(partidaId).thenApplyAsync(partida ->
                        ok(edit_partida.render(partidaForm.fill(partida), partidaId))
                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editPartida(Integer partidaId) {
        Partida editedPartida = formFactory.form(Partida.class).bindFromRequest("id", "nombre", "tipo_partida", "region_ayuda").get();

        return partidaRepository.findById(partidaId).thenCompose(dbPartida -> {
            PropertyUtils.copyNonNullProperties(editedPartida, dbPartida);

            return partidaRepository.update(dbPartida).thenApplyAsync(u ->
                    redirect(routes.PartidaController.listPartidas())
            );
        });
    }
}



