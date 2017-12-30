package willamedantas.inec.mobile.perfilgithub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import willamedantas.inec.mobile.perfilgithub.R;
import willamedantas.inec.mobile.perfilgithub.interfaces.OnItemClickListener;
import willamedantas.inec.mobile.perfilgithub.model.Usuario;

/**
 *  Classe padrão adapter responsável por tratar a exibição de cada item em um recyclerview.
 * Created by Willame Dantas.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Usuario> usuarios;
    private final OnItemClickListener listener;

    public CardAdapter(Context mContext, List<Usuario> usuarios, OnItemClickListener listener) {
        this.mContext = mContext;
        this.usuarios = usuarios;
        this.listener = listener;
    }

    public void clear(){
        usuarios.clear();
    }

    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_card, parent, false);

        return new CardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardAdapter.MyViewHolder holder, int position) {
        holder.bind(usuarios.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView login, urlPerfil;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            login = (TextView) view.findViewById(R.id.txtLogin);
            urlPerfil = (TextView) view.findViewById(R.id.txtUrl);
            avatar = (ImageView) view.findViewById(R.id.imgAvatar);

        }

        public void bind(final Usuario usuario, final OnItemClickListener listener) {
            login.setText(usuario.getLogin());
            urlPerfil.setText(usuario.getUrlPerfil());
            Picasso.with(itemView.getContext()).load(usuario.getAvatar()).into(avatar);
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(usuario);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(usuario);
                }
            });

        }
    }
}
