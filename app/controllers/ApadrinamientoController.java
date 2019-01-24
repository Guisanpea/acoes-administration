package controllers;

import models.entities.Apadrinamiento;
import models.management.AlumnoRepository;
import models.management.ApadrinamientoRepository;
import models.management.SocioRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class ApadrinamientoController extends Controller {
    private final SocioRepository socioRepository;
    private final AlumnoRepository alumnoRepository;
    private final ApadrinamientoRepository apadrinamientoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    public ApadrinamientoController(SocioRepository socioRepository, AlumnoRepository alumnoRepository, ApadrinamientoRepository apadrinamientoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.socioRepository = socioRepository;
        this.alumnoRepository = alumnoRepository;
        this.apadrinamientoRepository = apadrinamientoRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }

    public CompletionStage<Result> createApadrinamiento(Integer socioId, Integer alumnoId) {
        Apadrinamiento apadrinamiento = formFactory.form(Apadrinamiento.class).bindFromRequest("fechaInicio", "aportacion").get();

        return alumnoRepository.findById(alumnoId).thenCompose(alumno ->
                socioRepository.findById(socioId).thenCompose(socio -> {
                            apadrinamiento.setAlumno(alumno);
                            apadrinamiento.setSocio(socio);
                            return apadrinamientoRepository.add(apadrinamiento).thenApplyAsync(a ->
                                    redirect(routes.SocioController.listSocios())
                                  , ec.current()
                            );
                        }
                )
        );

    }
}