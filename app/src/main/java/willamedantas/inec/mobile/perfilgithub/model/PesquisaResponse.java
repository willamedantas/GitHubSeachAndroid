package willamedantas.inec.mobile.perfilgithub.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Classe de modelo para definir o retorno dos campos da pesquisa.
 * Created by Willame Dantas.
 */

public class PesquisaResponse implements Serializable {

    public PesquisaResponse(){

    }

    public PesquisaResponse(int totalCount, boolean incompletResult, List<Usuario> usuarios) {
        this.totalCount = totalCount;
        this.incompletResult = incompletResult;
        this.usuarios = usuarios;
    }

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompletResult;

    @SerializedName("items")
    private List<Usuario> usuarios;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompletResult() {
        return incompletResult;
    }

    public void setIncompletResult(boolean incompletResult) {
        this.incompletResult = incompletResult;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
