package willamedantas.inec.mobile.perfilgithub.service;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import willamedantas.inec.mobile.perfilgithub.model.Perfil;
import willamedantas.inec.mobile.perfilgithub.model.PesquisaResponse;
import willamedantas.inec.mobile.perfilgithub.model.Repositorio;

/**
 * Interface para uso do retrofit, definindo o endereço da API e os tipos de chamadas que serão realizadas.
 * Created by Willame Dantas.
 */
public interface GitHubApiService {

    String SERVICE_ENDPOINT = "https://api.github.com/";

    @GET("search/users")
    Call<PesquisaResponse> getPesquisar(@Query("q") String login);

    @GET("users/{users}")
    Call<Perfil> getPerfil(@Path("users") String usuario);

    @GET("users/{users}/repos")
    Call<List<Repositorio>> getRepos(@Path("users") String usuario);
}
