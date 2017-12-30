package willamedantas.inec.mobile.perfilgithub.activity;

import org.junit.Test;

import retrofit2.Response;
import willamedantas.inec.mobile.perfilgithub.builders.CriadorPerfil;
import willamedantas.inec.mobile.perfilgithub.model.Perfil;

import static org.junit.Assert.assertEquals;

/**
 * Created by willa on 29/12/2017.
 */

public class PerfilActivityTest {

    @Test
    public void deveRetornar(){
        PrincipalActivity principal = new PrincipalActivity();
        Response<Perfil> response = Response.success( new CriadorPerfil().nome("Willame Dantas").criar());

        Perfil perfil = principal.getPerfil(response);

        assertEquals("Willame Dantas", perfil.getNome());
        assertEquals("234", perfil.getFollowing());

    }


}
