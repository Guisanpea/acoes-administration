package controllers;

import beanUtils.PropertyUtils;
import models.entities.Socio;
import models.entities.Usuario;
import models.forms.SocioForm;
import models.management.SocioRepository;
import models.management.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.create_socio;
import views.html.edit_socio;
import views.html.index_socios;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

// TODO @Security.Authenticated(UserAuthenticator.class)
public class SocioController extends Controller {

    private final SocioRepository socioRepository;
    private final UsuarioRepository usuarioRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public SocioController(SocioRepository socioRepository, UsuarioRepository usuarioRepository, FormFactory formFactory, HttpExecutionContext ec) {
        this.socioRepository = socioRepository;
        this.usuarioRepository = usuarioRepository;
        this.formFactory = formFactory;
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> listSocios() {
        return socioRepository.list().thenApplyAsync(socioList ->
                        ok(index_socios.render(socioList))
                , httpExecutionContext.current()
        );
    }

    public Result renderCreateSocio() {
        Form<SocioForm> socioForm = formFactory.form(SocioForm.class);

        return ok(create_socio.render(socioForm));
    }

    public CompletionStage<Result> createSocio() {
        SocioForm socioForm = formFactory.form(SocioForm.class).bindFromRequest().get();
        Socio newSocio = new Socio();
        BeanUtils.copyProperties(socioForm, newSocio);
        String emailSession = session("email");
        return usuarioRepository.findByEmail(emailSession).thenCompose(user -> {
            newSocio.setResponsable(user);
            return socioRepository.add(newSocio).thenApplyAsync(socio ->
                        redirect(routes.SocioController.listSocios())
                    , httpExecutionContext.current()
            );
        });
    }

    public CompletionStage<Result> renderEditSocio(Integer socioId) {
        Form<SocioForm> socioForm = formFactory.form(SocioForm.class);
        return socioRepository.findById(socioId).thenApplyAsync(socio ->
                        ok(edit_socio.render(socioForm, socioId))
                , httpExecutionContext.current()
        );
    }

    public CompletionStage<Result> editSocio(Integer socioId) {
        Socio editedSocio = formFactory.form(Socio.class).bindFromRequest(
                "nombre", "apellidos", "estado", "nif",
                "direccion", "poblacion",
                "codigoPostal", "provincia", "telefonoFijo", "telefonoMovil",
                "email", "relacion", "certificado", "sector",
                "fechaAlta", "fechaBaja", "observaciones", "respondable", "contribucionEconomica").get();

        return socioRepository.findById(socioId).thenCompose(dbUser -> {
            PropertyUtils.copyNonNullProperties(editedSocio, dbUser);

            return socioRepository.update(dbUser).thenApplyAsync(u ->
                    redirect(routes.SocioController.listSocios())
            );
        });
    }
}
