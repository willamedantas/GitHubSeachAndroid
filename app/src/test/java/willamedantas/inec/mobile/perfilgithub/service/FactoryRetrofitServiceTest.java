package willamedantas.inec.mobile.perfilgithub.service;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by willa on 29/12/2017.
 */

public class FactoryRetrofitServiceTest {

    @Test
    public void deveRetornarUmaInstaciaDeConexaoRetrofit(){
        GitHubApiService retrofitService =
                FactoryRetrofitService.createRetrofitService(GitHubApiService.class, GitHubApiService.SERVICE_ENDPOINT);

        assertNotNull(retrofitService);
    }
}
