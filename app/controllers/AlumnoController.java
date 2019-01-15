package controllers;

import lombok.SneakyThrows;
import models.entities.Alumno;
import models.management.AlumnoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.indexalumnos;
import views.html.introduciralumno;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

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
                    ok(indexalumnos.render(alumnos))
              , httpExecutionContext.current()
        );
    }

    public Result renderAddAlumno() {
        Form<Alumno> alumnoForm = formFactory.form(Alumno.class);

        return ok(introduciralumno.render(alumnoForm));
    }

    @SneakyThrows(Exception.class)
    public CompletionStage<Result> addAlumno() {
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
