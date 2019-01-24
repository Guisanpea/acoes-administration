package controllers;

import models.entities.*;
import models.management.EgresoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_egreso;
import views.html.index_egresos;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class EgresoController extends Controller {

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
        return egresoRepository.list().thenApplyAsync(egresoList ->
                        ok(index_egresos.render(egresoList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreateEgreso() {
        Form<Egreso> egresoForm = formFactory.form(Egreso.class);
        List<String> bAlumnos = Arrays.stream(Alumno.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> bColaboradores = Arrays.stream(Colaborador.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> bTerceros = Arrays.stream(Tercero.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> bSocios = Arrays.stream(Socio.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> bTipos = Arrays.stream(Tipo_beneficiaro.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> partidas = Arrays.stream(Partida.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> proyectos = Arrays.stream(Proyecto.Nombre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        List<String> responsables = Arrays.stream(Usuario.Nombres.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ok(create_egreso.render(egresoForm, bAlumnos, bColaboradores, bTerceros, bSocios
                , bTipos, partidas, proyectos, responsables));
    }


    public CompletionStage<Result> createEgreso() {
        Egreso newEgreso = formFactory.form(Egreso.class).bindFromRequest(
              "fecha", "concepto", "importe",
              "beneficiarioAlumno", "beneficiarioColaborador", "beneficiarioTercero",
              "beneficiarioSocio", "tipoBeneficiario", "observaciones", "partida",
              "proyecto", "creador", "responsable", "valido").get();

        return egresoRepository.add(newEgreso).thenApplyAsync(egreso ->
              redirect(routes.EgresoController.listEgresos())
              , httpExecutionContext.current()
        );
    }

}
