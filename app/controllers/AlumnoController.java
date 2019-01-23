package controllers;

import lombok.SneakyThrows;
import models.entities.Alumno;
import models.management.AlumnoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_alumno;
import views.html.index_alumnos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class AlumnoController extends Controller {

    private final AlumnoRepository alumnoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public AlumnoController(AlumnoRepository alumnoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.alumnoRepository = alumnoRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listAlumnos() {
        return alumnoRepository.list().thenApplyAsync(alumnos ->
                    ok(index_alumnos.render(alumnos))
              , httpExecutionContext.current()
        );
    }

    public Result renderCreateAlumno() {
        Form<Alumno> alumnoForm = formFactory.form(Alumno.class);

        return ok(create_alumno.render(alumnoForm));
    }

    @SneakyThrows(Exception.class)
    public CompletionStage<Result> createAlumno() {
        Alumno newAlumno = formFactory.form(Alumno.class).bindFromRequest(
              "nombre", "apellidos", "estado", "sexo",
              "fechaNacimiento", "fechaEntradaAcoes",
              "fechaAlta", "fechaSalidaAcoes", "gradoCurso", "coloniaProcedencia",
              "coloniaActual", "observaciones").get();

        return alumnoRepository.add(newAlumno).thenApplyAsync(alumno ->
              redirect(routes.AlumnoController.listAlumnos())
              , httpExecutionContext.current()
        );
    }
}
