package controllers;

import models.entities.Usuario;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.login;

import javax.inject.Inject;

public class UserAuthenticator extends Security.Authenticator {
    private final FormFactory formFactory;

    @Inject
    public UserAuthenticator(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        Form<Usuario> usuarioForm = formFactory.form(Usuario.class);
        return unauthorized(login.render(usuarioForm, false));
    }
}
