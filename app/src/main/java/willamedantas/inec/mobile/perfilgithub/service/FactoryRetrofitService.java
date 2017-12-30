package willamedantas.inec.mobile.perfilgithub.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe para criar uma fábrica de conexão usando retrofit.
 * Created by willa on 27/12/2017.
 */

public class FactoryRetrofitService {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }
}
