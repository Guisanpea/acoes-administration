package controllers;

import models.entities.*;
import models.management.EgresoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_egreso;
import views.html.index_egresos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class EgresoController extends Controller {

    private final EgresoRepository egresoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public EgresoController(EgresoRepository egresoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.egresoRepository = egresoRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }


    public CompletionStage<Result> listEgresos() {
        return egresoRepository.list().thenApplyAsync(egresoList ->
                        ok(index_egresos.render(egresoList))
                , ec.current()
        );
    }

    public Result renderCreateEgreso() {
        Form<Egreso> egresoForm = formFactory.form(Egreso.class);
        List<String> tipos = Arrays.stream(Egreso.TipoBeneficiario.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ok(create_egreso.render(egresoForm, tipos));
    }


    public CompletionStage<Result> createEgreso() {
        Egreso newEgreso = formFactory.form(Egreso.class).bindFromRequest(
              "fecha", "concepto", "importe",
              "beneficiarioAlumno", "beneficiarioColaborador", "beneficiarioTercero",
              "beneficiarioSocio", "tipoBeneficiario", "observaciones", "partida",
              "proyecto", "creador", "responsable").get();

        return egresoRepository.add(newEgreso).thenApplyAsync(egreso ->
              redirect(routes.EgresoController.listEgresos())
              , ec.current()
        );
    }

}