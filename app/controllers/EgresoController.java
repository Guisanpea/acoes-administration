package controllers;

import models.entities.Egreso;
import models.management.EgresoRepository;
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

    private final EgresoRepository egresoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public EgresoController(EgresoRepository egresoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.egresoRepository = egresoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }


    public CompletionStage<Result> listEgresos() {
        return egresoRepository.list().thenApplyAsync(registroList ->
                        ok(index_egreso.render(egresoList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreateEgreso() {
        Form<Egreso> egresoForm = formFactory.form(Egreso.class);
        List<String> partidas = Arrays.stream(Partida.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> proyectos = Arrays.stream(Proyecto.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ok(create_egreso.render(egresoForm, partidas, proyectos));
    }


    public CompletionStage<Result> createEgreso() {
        Egreso newEgreso = formFactory.form(Egreso.class).bindFromRequest(
              "fecha", "concepto", "importe",
              "alumno", "colaborador", "tercero",
              "socio", "tipoBeneficiario", "observaciones", "partida",
              "proyecto", "creador", "responsable", "valido").get();

        return egresoRepository.add(newEgreso).thenApplyAsync(egreso ->
              redirect(routes.EgresoController.listEgresos())
              , httpExecutionContext.current()
        );
    }

}
