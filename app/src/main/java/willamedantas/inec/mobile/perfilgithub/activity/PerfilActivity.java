package willamedantas.inec.mobile.perfilgithub.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import willamedantas.inec.mobile.perfilgithub.R;
import willamedantas.inec.mobile.perfilgithub.adapter.ReposAdapter;
import willamedantas.inec.mobile.perfilgithub.model.Perfil;
import willamedantas.inec.mobile.perfilgithub.model.Repositorio;
import willamedantas.inec.mobile.perfilgithub.service.FactoryRetrofitService;
import willamedantas.inec.mobile.perfilgithub.service.GitHubApiService;
import willamedantas.inec.mobile.perfilgithub.util.StringUtil;

/**
 * Activity perfil: responsável por mostrar na tela o perfil de usuário selecionado na {@link PrincipalActivity}
 * e listar os repositórios em um recyclerview.
 *
 * @see PrincipalActivity
 * @author Willame Dantas
 */
public class PerfilActivity extends AppCompatActivity {

    /**
     * Variáveis privadas
     */
    private Perfil perfil;
    private String login;
    private GitHubApiService service;

    /**
     * variáveis para o uso do framework ButterKnife, essas não pode ter acesso privado pois o framework não funcionarar.
     */
    @BindView(R.id.recycler_repositorio)
    RecyclerView recyclerView;
    @BindView(R.id.imgPerfil)
    ImageView imgAvatar;
    @BindView(R.id.txtNomePerfil)
    TextView txtNome;
    @BindView(R.id.numRepositorio)
    TextView numRepositorio;
    @BindView(R.id.numSeguidores)
    TextView numSeguidores;
    @BindView(R.id.numSeguindo)
    TextView numSeguindos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        /**
        * Inciando framework ButterKnife
        */
        ButterKnife.bind(this);

        /**
        * Recuperação do parâmetro passado conténdo os dados do perfil e login do usuário selecionado.
        */
        Bundle args = getIntent().getBundleExtra("resultado");
        if(args != null){
            perfil = new Perfil();
            popularView(args);
        }

        /**
        * Preparando o recyclerview, setando o modo de otimização e layout para ordenação dos card na tela.
        */
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getApiService();
        getApiConnect();

    }

    /**
     * Chamanda para API do GitHub para buscar os repositórios.
     *
     */
    private void getApiConnect() {

        Call<List<Repositorio>> call = service.getRepos(login);
        call.enqueue(new Callback<List<Repositorio>>() {

            @Override
            public void onResponse(Call<List<Repositorio>> call, Response<List<Repositorio>> response) {

                /**
                 * Verificando e recuperando o response da busca solicitada, passando para o adapter do recyclerview para exibir em tela.
                 * Caso nenhum valor retornado informar em tela com uma mensagem.
                 */
                if(response != null && response.isSuccessful() && response.body().size() > 0){
                    List<Repositorio> repositorios = getRepositorios(response);
                    final ReposAdapter mCardAdapter = new ReposAdapter(getApplicationContext(), repositorios);
                    recyclerView.setAdapter(mCardAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Nenhum projeto encontrado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Repositorio>> call, Throwable t) {

            }
        });
    }

    /**
     * Método para extrair os repositórios do response que foi solicitado pelo retrofit e retornar uma lista.
     * @param response
     * @return
     */
    @NonNull
    public List<Repositorio> getRepositorios(Response<List<Repositorio>> response) {
        List<Repositorio> repositorios = new ArrayList<>();
        for(int i = 0; i < response.body().size(); i++){
            Repositorio r = new Repositorio();
            r = response.body().get(i);
            String dataCriarcao = StringUtil.dataFormat(r.getDataCriacao());
            String dataUpdate = StringUtil.dataFormat(r.getUltimaAtualizacao());
            r.setDataCriacao(dataCriarcao);
            r.setUltimaAtualizacao(dataUpdate);
            repositorios.add(r);
        }
        return repositorios;
    }

    /**
     * Método para setar as informações do perfil na tela.
     * @param args
     */
    public void popularView(Bundle args) {
        perfil = (Perfil) args.getSerializable("perfil");
        login = (String) args.getSerializable("login");
        txtNome.setText(perfil.getNome());
        numRepositorio.setText(perfil.getTotalProjetos());
        numSeguidores.setText(perfil.getFollowers());
        numSeguindos.setText(perfil.getFollowing());
        Picasso.with(getApplicationContext()).load(perfil.getUrlAvatar()).into(imgAvatar);
    }

    /**
     * Método para retornar um service instanciado.
     * @return
     */
    private GitHubApiService getApiService() {
        if(service == null){
            service =
                    FactoryRetrofitService.createRetrofitService(GitHubApiService.class, GitHubApiService.SERVICE_ENDPOINT);
        }
        return service;
    }

}
