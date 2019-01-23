package controllers;

import models.entities.Ingreso;
import models.management.IngresoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

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

    /*
    public CompletionStage<Result> listRegistrosEconomicos() {
        return ingresoRepository.list().thenApplyAsync(registroList ->
              ok(indexregistroeconomico.render(registroList))
              , httpExecutionContext.current()
        );
    }

    public Result renderAddRegistro() {
        Form<Ingreso> registroForm = formFactory.form(Ingreso.class);

        return ok(introducirregistro.render(registroForm));
    }

*/
    /*
    public CompletionStage<Result> addRegistro() {
        Ingreso newRegistro = formFactory.form(Ingreso.class).bindFromRequest(
              "fecha", "tipo", "concepto", "importe",
              "codigo_beneficiario", "observaciones", "codigo_servicio",
              "proyecto", "numero_socio", "responsable").get();

        return ingresoRepository.add(newRegistro).thenApplyAsync(registro ->
              redirect(routes.IngresoController.listIngresos())
              , httpExecutionContext.current()
        );
    }
    */
}
