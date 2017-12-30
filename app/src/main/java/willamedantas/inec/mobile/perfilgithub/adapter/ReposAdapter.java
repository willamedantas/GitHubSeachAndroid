package willamedantas.inec.mobile.perfilgithub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import willamedantas.inec.mobile.perfilgithub.R;
import willamedantas.inec.mobile.perfilgithub.model.Repositorio;

/**
 * Classe padrão adapter responsável por tratar a exibição de cada item em um recyclerview.
 * Created by Willame Dantas.
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Repositorio> repositorios;

    public ReposAdapter(Context mContext, List<Repositorio> repositorios) {
        this.mContext = mContext;
        this.repositorios = repositorios;
    }

    public void clear(){
        repositorios.clear();
    }

    @Override
    public ReposAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_repos, parent, false);

        return new ReposAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReposAdapter.MyViewHolder holder, int position) {
        holder.bind(repositorios.get(position));
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRepositorio, txtDescricao, txtDataCriacao, txtUpdate, txtLanguagem;

        public MyViewHolder(View view) {
            super(view);
            txtRepositorio = view.findViewById(R.id.txtRepositorio);
            txtDescricao = view.findViewById(R.id.txtDescricao);
            txtDataCriacao = view.findViewById(R.id.txtDataCriacao);
            txtUpdate = view.findViewById(R.id.txtUpdate);
            txtLanguagem = view.findViewById(R.id.txtLanguagem);
        }

        public void bind(final Repositorio repositorio) {
            txtRepositorio.setText(repositorio.getNomeRepositorio());
            txtDescricao.setText(repositorio.getDescricao());
            txtLanguagem.setText(repositorio.getLanguagemProjeto());
            txtUpdate.setText(repositorio.getUltimaAtualizacao());
            txtDataCriacao.setText(repositorio.getDataCriacao());
        }
    }
}
