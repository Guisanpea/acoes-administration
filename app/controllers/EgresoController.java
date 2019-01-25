package controllers;

import models.entities.*;
import models.forms.EgresoForm;
import models.management.*;
import org.springframework.beans.BeanUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create_egreso;
import views.html.index_egresos;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//TODO @Security.Authenticated(UserAuthenticator.class)
public class EgresoController extends Controller {

    private final EgresoRepository egresoRepository;
    private final AlumnoRepository alumnoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final TerceroRepository terceroRepository;
    private final SocioRepository socioRepository;
    private final ProyectoRepository proyectoRepository;
    private final PartidaRepository partidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public EgresoController(EgresoRepository egresoRepository, AlumnoRepository alumnoRepository, ColaboradorRepository colaboradorRepository, TerceroRepository terceroRepository, SocioRepository socioRepository, ProyectoRepository proyectoRepository, PartidaRepository partidaRepository, UsuarioRepository usuarioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.egresoRepository = egresoRepository;
        this.alumnoRepository = alumnoRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.terceroRepository = terceroRepository;
        this.socioRepository = socioRepository;
        this.proyectoRepository = proyectoRepository;
        this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
        this.ec = ec;
    }


    public CompletionStage<Result> listEgresos() {
        return egresoRepository.list().thenApplyAsync(egresoList ->
                    ok(index_egresos.render(egresoList))
              , ec.current()
        );
    }

    public CompletionStage<Result> renderCreateEgreso() {
        Form<EgresoForm> egresoForm = formFactory.form(EgresoForm.class);
        List<String> tipos = Arrays.stream(Egreso.TipoBeneficiario.values())
              .map(Enum::name)
              .collect(Collectors.toList());
        return alumnoRepository.list().thenComposeAsync(alumnos ->
              colaboradorRepository.list().thenComposeAsync(colaboradores ->
                    terceroRepository.list().thenComposeAsync(terceros ->
                          proyectoRepository.list().thenComposeAsync(proyectos ->
                                partidaRepository.list().thenComposeAsync(partidas ->
                                      socioRepository.list().thenApplyAsync(socios -> {
                                                List<String> nombreAlumnos = alumnos.stream()
                                                      .map(Alumno::getNombre)
                                                      .collect(Collectors.toList());
                                                List<String> nombreColaboradores = colaboradores.stream()
                                                      .map(Colaborador::getNombre)
                                                      .collect(Collectors.toList());
                                                List<String> nombreTerceros = terceros.stream()
                                                      .map(Tercero::getNombre)
                                                      .collect(Collectors.toList());
                                                List<String> nombreSocios = socios.stream()
                                                      .map(Socio::getNombre)
                                                      .collect(Collectors.toList());
                                                List<String> nombreProyectos = proyectos.stream()
                                                      .map(Proyecto::getNombre)
                                                      .collect(Collectors.toList());
                                                List<String> nombrePartidas = partidas.stream()
                                                      .map(Partida::getNombre)
                                                      .collect(Collectors.toList());
                                                return ok(create_egreso.render(egresoForm, tipos, nombreAlumnos, nombreColaboradores, nombreTerceros, nombreSocios, nombreProyectos, nombrePartidas));
                                            }, ec.current()
                                      ), ec.current()
                                ), ec.current()
                          ), ec.current()
                    ), ec.current()
              ), ec.current()
        );
    }


    public CompletionStage<Result> createEgreso() {
        EgresoForm egresoForm = formFactory.form(EgresoForm.class).bindFromRequest().get();
        Egreso egreso = new Egreso();

        BeanUtils.copyProperties(egresoForm, egreso);

        String usuarioEmail = session("email");

        return usuarioRepository.findByEmail(usuarioEmail).thenCompose(usuario ->
              partidaRepository.findByNombre(egresoForm.partida).thenCompose(partida ->
                    proyectoRepository.findByNombre(egresoForm.nombreProyecto).thenCompose(proyecto -> {
                        egreso.setPartida(partida);
                        egreso.setProyecto(proyecto);
                        egreso.setCreador(usuario);

                        switch (egresoForm.tipoBeneficiario) {
                            case Socio:
                                return socioRepository.findByNombre(egresoForm.nombreBeneficiarioSocio)
                                      .thenCompose(socio -> {
                                                egreso.setBeneficiarioSocio(socio);
                                                return egresoRepository.add(egreso).thenApplyAsync(e ->
                                                            redirect(routes.EgresoController.listEgresos())
                                                      , ec.current()
                                                );
                                            }
                                      );
                            case Alumno:
                                return alumnoRepository.findByNombre(egresoForm.nombreBeneficiarioAlumno)
                                      .thenCompose(alumno -> {
                                                egreso.setBeneficiarioAlumno(alumno);
                                                return egresoRepository.add(egreso).thenApplyAsync(e ->
                                                            redirect(routes.EgresoController.listEgresos())
                                                      , ec.current()
                                                );
                                            }
                                      );
                            case Colaborador:
                                return colaboradorRepository.findByNombre(egresoForm.nombreBeneficiarioColaborador)
                                      .thenCompose(colaborador -> {
                                                egreso.setBeneficiarioColaborador(colaborador);
                                                return egresoRepository.add(egreso).thenApplyAsync(e ->
                                                            redirect(routes.EgresoController.listEgresos())
                                                      , ec.current()
                                                );
                                            }
                                      );
                            default:
                                return terceroRepository.findByNombre(egresoForm.nombreBeneficiarioTercero)
                                      .thenCompose(tercero -> {
                                                egreso.setBeneficiarioTercero(tercero);
                                                return egresoRepository.add(egreso).thenApplyAsync(e ->
                                                            redirect(routes.EgresoController.listEgresos())
                                                      , ec.current()
                                                );
                                            }
                                      );
                        }
                    })
              )
        );
    }

}