package willamedantas.inec.mobile.perfilgithub.builders;

import willamedantas.inec.mobile.perfilgithub.model.Perfil;

/**
 * Created by Willame Dantas
 */

public class CriadorPerfil {

    private Perfil perfil;

    public CriadorPerfil nome(String nome){
        perfil = new Perfil();
        perfil.setNome(nome);
        perfil.setFollowing("234");
        perfil.setFollowers("521");
        perfil.setTotalProjetos("50");
        return this;
    }

    public Perfil criar(){
        return this.perfil;
    }

}
