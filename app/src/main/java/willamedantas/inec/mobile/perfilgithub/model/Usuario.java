package willamedantas.inec.mobile.perfilgithub.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Classe de modelo para definir os campos de usuário.
 * Created by Willame Dantas.
 */

public class Usuario implements Serializable {

    public Usuario(){

    }

    public Usuario(String nome, String urlAvatar, String urlRepositorio, String urlPerfil) {
        this.login = nome;
        this.urlAvatar = urlAvatar;
        this.urlRepositorio = urlRepositorio;
        this.urlPerfil = urlPerfil;
    }

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String urlAvatar;
    @SerializedName("repos_url")
    private String urlRepositorio;
    @SerializedName("url")
    private String urlPerfil;

    public String getLogin() {
        return login;
    }

    public void setLogin(String nome) {
        this.login = nome;
    }

    public String getAvatar() {
        return urlAvatar;
    }

    public void setAvatar(String avatar) {
        this.urlAvatar = avatar;
    }

    public String getUrlRepositorio() {
        return urlRepositorio;
    }

    public void setUrlRepositorio(String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }

    public String getUrlPerfil() {
        return urlPerfil;
    }

    public void setUrlPerfil(String urlPerfil) {
        this.urlPerfil = urlPerfil;
    }
}
