package willamedantas.inec.mobile.perfilgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import willamedantas.inec.mobile.perfilgithub.R;
import willamedantas.inec.mobile.perfilgithub.adapter.CardAdapter;
import willamedantas.inec.mobile.perfilgithub.interfaces.OnItemClickListener;
import willamedantas.inec.mobile.perfilgithub.model.Perfil;
import willamedantas.inec.mobile.perfilgithub.model.PesquisaResponse;
import willamedantas.inec.mobile.perfilgithub.model.Usuario;
import willamedantas.inec.mobile.perfilgithub.service.FactoryRetrofitService;
import willamedantas.inec.mobile.perfilgithub.service.GitHubApiService;

/**
 * Activity principal: é a primeira tela exibida para o usuário trazendo o campo de pesquisa, após realizar a pesquisa
 * aparecerá cartões dos usuários retornado pela API do GitHub.
 *
 * @author Willame Dantas on 27/12/2017
 */
public class PrincipalActivity extends AppCompatActivity {

    /**
     * variáveis para o uso do framework ButterKnife, essas não pode ter acesso privado pois o framework não funcionarar.
     */
    @BindView(R.id.recycler_view2)
     RecyclerView recyclerView;{recyclerView = null;}
    @BindView(R.id.editText)
     EditText login;
    @BindView(R.id.btnPesquisar)
     Button pesquisar;
    @BindView(R.id.btnLimpar)
     Button limpar;
    @BindView(R.id.imageViewPrincipal)
    ImageView icon;
    @BindView(R.id.textViewMensagem)
    TextView textViewMensagem;

    /**
     * Variável privada
     */
    private CardAdapter cardAdapter;
    private GitHubApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        /**
         * Inciando framework ButterKnife
         */
        ButterKnife.bind(this);

        /**
         * Preparando o recyclerview, setando o modo de otimização e layout para ordenação dos card na tela.
         */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /**
         * Inicializando fábrica de conexão.
         */
        getApiService();

    }

    /**
     *Método para ação do botão pesquisar
     * @param view
     */
    @OnClick(R.id.btnPesquisar)
    public void pesquisarOnClick(View view) {
        String pesquisa = login.getText().toString();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(login.getWindowToken(), 0);
        getCallPesquisar(pesquisa);
        icon.getLayoutParams().height = 0;
        icon.getLayoutParams().width = 0;
        textViewMensagem.getLayoutParams().width = 0;
        textViewMensagem.getLayoutParams().height = 0;
        textViewMensagem.requestLayout();
        icon.requestLayout();

    }

    /**
     * Método para ação do botão limpar.
     * @param view
     */
    @OnClick(R.id.btnLimpar)
    public void limparOnClick(View view) {
        login.setText("");
    }

    /**
     * Método para retornar o usuário que foi selecionado por click dentro da imagemview ou cardview.
     * @return
     */
    @NonNull
    private OnItemClickListener getListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(final Usuario usuario) {
                getActivityPerfil(usuario);
            }
        };
    }

    /**
     * Método para fazer a chamada na API do GitHub com framework retrofit passando como parâmetro
     * o texto digitado no campo editext.
     * @param login
     */
    private void getCallPesquisar(String login) {

        Call<PesquisaResponse> call = service.getPesquisar(login);
        call.enqueue(new Callback<PesquisaResponse>() {
            @Override
            public void onResponse(Call<PesquisaResponse> call, Response<PesquisaResponse> response) {

                /**
                 * Verificando e recuperando o response da busca solicitada, passando para o adapter do recyclerview para exibir em tela.
                 * Caso nenhum valor retornado informar em tela com uma mensagem.
                 */
                if(response != null && response.isSuccessful() && response.body().getUsuarios().size() > 0){
                    List<Usuario> usuarios = response.body().getUsuarios();
                    cardAdapter = new CardAdapter(getApplicationContext(), usuarios, getListener());
                    recyclerView.setAdapter(cardAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Nenhum perfil encontrado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PesquisaResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na aplicação inesperado.",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }



    /**
     * Método para chamar a tela de perfil através de click no cardview.
     * @param usuario
     */
    private void getActivityPerfil(final Usuario usuario) {
        Call<Perfil> call = service.getPerfil(usuario.getLogin());
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {

                /**
                 * Retornando perfil do usuário selecionado.
                 */
                Perfil perfil = getPerfil(response);

                /**
                 * Preparando parâmetro de envio para tela de perfil.
                 */
                Bundle args = new Bundle();
                args.putSerializable("perfil", perfil);
                args.putSerializable("login", usuario.getLogin());

                /**
                 * Preparando intent para chamar a tela de perfil.
                 */
                Intent intent = new Intent(PrincipalActivity.this, PerfilActivity.class);
                intent.putExtra("resultado", args);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {

            }
        });
    }

    /**
     * Método para retornar perfil que foi solicitado por click.
     * @param response
     * @return
     */
    @NonNull
    public Perfil getPerfil(Response<Perfil> response) {
        Perfil perfil = new Perfil();
        perfil.setNome(response.body().getNome());
        perfil.setUrlAvatar(response.body().getUrlAvatar());
        perfil.setFollowers(response.body().getFollowers());
        perfil.setFollowing(response.body().getFollowing());
        perfil.setTotalProjetos(response.body().getTotalProjetos());
        return perfil;
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


    /**
     * Classe para estender e poder personalizar os cardview na tela.
     *
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount; 

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}



















