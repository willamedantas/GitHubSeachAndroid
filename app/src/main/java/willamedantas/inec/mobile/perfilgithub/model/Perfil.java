package willamedantas.inec.mobile.perfilgithub.model;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Classe de modelo para definir os campos de perfil.
 * Created by Willame Dantas.
 */
public class Perfil implements Serializable {

    public Perfil(){}

    public Perfil(String nome, String totalProjetos, String followers, String following, String urlAvatar) {
        this.nome = nome;
        this.totalProjetos = totalProjetos;
        this.followers = followers;
        this.following = following;
        this.urlAvatar = urlAvatar;
    }

    @SerializedName("name")
    private String nome;
    @SerializedName("public_repos")
    private String totalProjetos;
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("avatar_url")
    private String urlAvatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTotalProjetos() {
        return totalProjetos;
    }

    public void setTotalProjetos(String totalProjetos) {
        this.totalProjetos = totalProjetos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

}
