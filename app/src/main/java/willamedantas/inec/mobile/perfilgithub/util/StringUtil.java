package willamedantas.inec.mobile.perfilgithub.util;

/**
 * Classe Util usada para fazer a conversão de data recebida pela API.
 * Created by Willame Dantas.
 */
public class StringUtil {
    public static String dataFormat(String dataOld){
        String dia = "";
        String mes = "";
        String ano = "";
        if(dataOld != null && !dataOld.isEmpty() && dataOld.length() > 12){
            dia = dataOld.substring(8,10);
            mes = dataOld.substring(5,7);
            ano = dataOld.substring(0,4);
            return dia+"/"+mes+"/"+ano;
        }

        return null;

    }

}
