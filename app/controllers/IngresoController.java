package controllers;

import models.management.IngresoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.indexregistroeconomico;
import views.html.introducirregistro;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class RegistroEconomicoController extends Controller {

    private final IngresoRepository ingresoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public RegistroEconomicoController(IngresoRepository ingresoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.ingresoRepository = ingresoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listRegistrosEconomicos() {
        return ingresoRepository.list().thenApplyAsync(registroList ->
              ok(indexregistroeconomico.render(registroList))
              , httpExecutionContext.current()
        );
    }

    public Result renderAddRegistro() {
        Form<RegistroEconomico> registroForm = formFactory.form(RegistroEconomico.class);

        return ok(introducirregistro.render(registroForm));
    }

    public CompletionStage<Result> addRegistro() {
        RegistroEconomico newRegistro = formFactory.form(RegistroEconomico.class).bindFromRequest(
              "fecha", "tipo", "concepto", "importe",
              "codigo_beneficiario", "observaciones", "codigo_servicio",
              "proyecto", "numero_socio", "responsable").get();

        return ingresoRepository.add(newRegistro).thenApplyAsync(registro ->
              redirect(routes.RegistroEconomicoController.listRegistrosEconomicos())
              , httpExecutionContext.current()
        );
    }
}
