package controllers;

import models.entities.*;
import models.forms.EgresoForm;
import models.management.AlumnoRepository;
import models.management.EgresoRepository;
import models.management.AlumnoRepository;
import models.management.TerceroRepository;
import models.management.SocioRepository;
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
    private final FormFactory formFactory;
    private final AlumnoRepository alumnoRepository;
    private final SocioRepository socioRepository;
    private final TerceroRepository terceroRepository;
    private final HttpExecutionContext ec;

    @Inject
    public EgresoController(EgresoRepository egresoRepository, AlumnoRepository alumnoRepository, SocioRepository socioRepository , TerceroRepository terceroRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.egresoRepository = egresoRepository;
        this.formFactory = formFactory;
        this.alumnoRepository = alumnoRepository;
        this.socioRepository = socioRepository;
        this.terceroRepository = terceroRepository;
        this.ec = ec;
    }


    public CompletionStage<Result> listEgresos() {
        return egresoRepository.list().thenApplyAsync(egresoList ->
                        ok(index_egresos.render(egresoList))
                , ec.current()
        );
    }

    public Result renderCreateEgreso() {
        Form<EgresoForm> egresoForm = formFactory.form(EgresoForm.class);
        List<String> tipos = Arrays.stream(Egreso.TipoBeneficiario.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ok(create_egreso.render(egresoForm, tipos));
    }


    public CompletionStage<Result> createEgreso() {
        EgresoForm EgresoForm = formFactory.form(EgresoForm.class).bindFromRequest().get();
        Egreso egreso = new Egreso();
        BeanUtils.copyProperties(EgresoForm, egreso);
        String usuarioEmail = session("email");

        return alumnoRepository.findByNombre(EgresoForm.nombreBeneficiarioAlumno).thenCompose(alumno ->
                socioRepository.findByNombre(EgresoForm.nombreBeneficiarioColaborador).thenCompose(socio ->
                        terceroRepository.findByNombre(EgresoForm.beneficiarioTercero).thenCompose(tercero ->
                            usuarioRepository.findByEmail(usuarioEmail).thenCompose(usuario -> {
                                egreso.setBeneficiarioAlumno(alumno);
                                egreso.setBeneficiarioColaborador(socio);
                                egreso.setCreador(usuario);
                                egresoRepository.add(egreso).thenApplyAsync(i -> {
                                            return redirect(routes.EgresoController.listEgresos());
                                        }
                                        , ec.current());
                        })
                    )
                )
        );
    }

}