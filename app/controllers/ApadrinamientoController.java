package controllers;

import lombok.val;
import models.entities.AdministracionApadrinamiento;
import models.entities.Alumno;
import models.entities.Apadrinamiento;
import models.entities.Socio;
import models.forms.ApadrinamientoForm;
import models.management.*;
import org.springframework.beans.BeanUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_apadrinamiento;
import views.html.index_apadrinamientos;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class ApadrinamientoController extends Controller {
    private final SocioRepository socioRepository;
    private final AlumnoRepository alumnoRepository;
    private final ApadrinamientoRepository apadrinamientoRepository;
    private final AdministracionApadrinamientoRepository administracionApadrinamientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public ApadrinamientoController(SocioRepository socioRepository, AlumnoRepository alumnoRepository, ApadrinamientoRepository apadrinamientoRepository, AdministracionApadrinamientoRepository administracionApadrinamientoRepository, UsuarioRepository usuarioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.socioRepository = socioRepository;
        this.alumnoRepository = alumnoRepository;
        this.apadrinamientoRepository = apadrinamientoRepository;
        this.administracionApadrinamientoRepository = administracionApadrinamientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }

    public CompletionStage<Result> listApadrinamientos() {
        return apadrinamientoRepository.list().thenApplyAsync(apadrinamientoList ->
                    ok(index_apadrinamientos.render(apadrinamientoList))
              , ec.current()
        );
    }

    public CompletionStage<Result> renderCreateApadrinamiento() {
        Form<ApadrinamientoForm> apadrinamientoForm = formFactory.form(ApadrinamientoForm.class);
        return socioRepository.list().thenComposeAsync(socios ->
                    alumnoRepository.list().thenApplyAsync(alumnos -> {
                              val socioNames = socios.stream()
                                    .map(Socio::getNombre)
                                    .collect(Collectors.toList());
                              val alumnoNames = alumnos.stream()
                                    .map(Alumno::getNombre)
                                    .collect(Collectors.toList());
                              return ok(create_apadrinamiento.render(apadrinamientoForm, alumnoNames, socioNames));
                          }
                          , ec.current())
              , ec.current()
        );
    }

    public Result createApadrinamiento() {
        return TODO;
        /*
        ApadrinamientoForm apadrinamientoForm = formFactory.form(ApadrinamientoForm.class).bindFromRequest().get();
        Apadrinamiento apadrinamiento = new Apadrinamiento();

        BeanUtils.copyProperties(apadrinamientoForm, apadrinamiento);

        String usuarioEmail = session("email");
        return usuarioRepository.findByEmail(usuarioEmail).thenCompose(usuario ->
              socioRepository.findByNombre(apadrinamientoForm.nombrePadrino).thenCompose(socio ->
                    alumnoRepository.findByNombre(apadrinamientoForm.nombreApadrinado).thenCompose(alumno -> {
                        apadrinamiento.setApadrinado(alumno);
                        apadrinamiento.setPadrino(socio);
                        return apadrinamientoRepository.add(apadrinamiento).thenApplyAsync(a ->
                              redirect(routes.SocioController.listSocios())
                              , ec.current()
                        );
                    })
              )
        );
        */
    }

    /*
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

    }*/
}