package controllers;

import beanUtils.PropertyUtils;
import lombok.SneakyThrows;
import models.entities.Alumno;
import models.management.AlumnoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_alumno;
import views.html.edit_alumno;
import views.html.index_alumnos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class AlumnoController extends Controller {

    private final AlumnoRepository alumnoRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public AlumnoController(AlumnoRepository alumnoRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.alumnoRepository = alumnoRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }

    public CompletionStage<Result> listAlumnos() {
        return alumnoRepository.list().thenApplyAsync(alumnos ->
                    ok(index_alumnos.render(alumnos))
              , ec.current()
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
              , ec.current()
        );
    }

    public CompletionStage<Result> renderEditAlumno(Integer alumnoId) {
        final Form<Alumno> alumnoForm = formFactory.form(Alumno.class);

        return alumnoRepository.findById(alumnoId).thenApplyAsync(alumno ->
                    ok(edit_alumno.render(alumnoForm.fill(alumno), alumnoId))
              , ec.current()
        );
    }

    public CompletionStage<Result> editAlumno(Integer alumnoId) {
        Alumno editedAlumno = formFactory.form(Alumno.class).bindFromRequest(
              "nombre", "apellidos", "estado", "sexo",
              "fechaNacimiento", "fechaEntradaAcoes",
              "fechaAlta", "fechaSalidaAcoes", "gradoCurso", "coloniaProcedencia",
              "coloniaActual", "observaciones", "apadrinable").get();

        return alumnoRepository.findById(alumnoId).thenCompose(dbUser -> {
            PropertyUtils.copyNonNullProperties(editedAlumno, dbUser);

            return alumnoRepository.update(dbUser).thenApplyAsync(u ->
                  redirect(routes.AlumnoController.listAlumnos())
                  , ec.current()
            );
        });
    }
}
