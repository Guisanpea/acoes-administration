package controllers;

import lombok.SneakyThrows;
import models.entities.Alumno;
import models.management.AlumnoRepository;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.indexalumnos;
import views.html.introduciralumno;

import javax.inject.Inject;
import java.util.List;

public class AlumnoController extends Controller {

    private AlumnoRepository alumnoRepository;
    private FormFactory formFactory;

    @Inject
    public AlumnoController(AlumnoRepository alumnoRepository, FormFactory formFactory){
        this.alumnoRepository = alumnoRepository;
        this.formFactory = formFactory;
    }

    public Result listAlumnos(){
        List<Alumno> alumnoList = alumnoRepository.list();

        return ok(indexalumnos.render(alumnoList));
    }

    public Result renderAddAlumno() {
        Form<Alumno> alumnoForm = formFactory.form(Alumno.class);

        return ok(introduciralumno.render(alumnoForm));
    }

    @SneakyThrows(Exception.class)
    public Result addAlumno() {
        Alumno newAlumno = formFactory.form(Alumno.class).bindFromRequest(
                "nombre", "apellidos", "estado", "sexo",
                "fechaNacimiento", "fechaEntradaAcoes",
                "fechaAlta", "fechaSalidaAcoes", "gradoCurso", "coloniaProcedencia",
                "coloniaActual", "observaciones").get();

        alumnoRepository.add(newAlumno).toCompletableFuture().get();

        return redirect(routes.AlumnoController.listAlumnos());
    }
}
