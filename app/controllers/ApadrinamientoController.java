package controllers;

import models.entities.Apadrinamiento;
import models.management.AlumnoRepository;
import models.management.ApadrinamientoRepository;
import models.management.SocioRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index_apadrinamientos;

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

    public CompletionStage<Result> listApadrinamientos() {
        return apadrinamientoRepository.list().thenApplyAsync(apadrinamientoList ->
                        ok(index_apadrinamientos.render(apadrinamientoList))
                , ec.current()
        );
    }

    public CompletionStage<Result> renderCreateApadrinamiento() {
        Form<Apadrinamiento> apadrinamientoForm = formFactory.form(Apadrinamiento.class);
        return socioRepository.list().thenComposeAsync(socios ->
                        alumnoRepository.list().thenApplyAsync(alumnos -> {
                                    val socioNames = socios.stream()
                                            .map(Socio::getNombre)
                                            .collect(Collectors.toList());
                                    val alumnoNames = alumnos.stream()
                                            .map(Alumno::getNombre)
                                            .collect(Collectors.toList());
                                    return ok(create_apadrinamiento.render(apadrinamientoForm, socioNames, alumnoNames));
                                }
                                , ec.current())
                , ec.current()
        );
    }

    public CompletionStage<Result> createApadrinamiento() {
        Apadrinamiento apadrinamientoForm = formFactory.form(Apadrinamiento.class).bindFromRequest().get();
/*
        String usuarioEmail = session("email");
        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail).toCompletableFuture().get();

        Ingreso ingreso = new Ingreso();
        BeanUtils.copyProperties(ingresoForm, ingreso);
        ingreso.setPartida(partida);
        ingreso.setProyecto(proyecto);
        ingreso.setCreador(usuario);

        ingresoRepository.add(ingreso).toCompletableFuture().get();
        return redirect(routes.SocioController.listSocios());
*/
        Ingreso ingreso = new Ingreso();
        BeanUtils.copyProperties(ingresoForm, ingreso);
        String usuarioEmail = session("email");

        return partidaRepository.findByNombre(ingresoForm.nombrePartida).thenCompose(partida ->
                proyectoRepository.findByNombre(ingresoForm.nombreProyecto).thenCompose(proyecto ->
                        usuarioRepository.findByEmail(usuarioEmail).thenCompose(usuario -> {
                            ingreso.setPartida(partida);
                            ingreso.setProyecto(proyecto);
                            ingreso.setCreador(usuario);
                            System.out.println(ingreso.getPartida());
                            System.out.println(ingreso.getProyecto());
                            System.out.println(ingreso.getCreador());
                            return ingresoRepository.add(ingreso).thenApplyAsync(i -> {
                                        return redirect(routes.SocioController.listSocios());
                                    }
                                    , ec.current());
                        })
                )
        );
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