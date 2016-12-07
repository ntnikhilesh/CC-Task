package com.example.dell.cc_task.model.api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import static com.example.dell.cc_task.model.utilities.Constants.BASE_URL;

/**
 * Created by hp on 20-02-2016.
 */
public class NetworkApiGenerator {

  //  public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";
   // public static final String BASE_URL = "https://api.stackexchange.com/2.2/";
   // public static final String apikey = "3b0f2adc01e2e325c60ec5faccb91cfe";




    public  static <S> S createService(Class<S> serviceClass)
{

    RequestInterceptor requestInterceptor=new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
               request.addHeader("Accept", "application/json");
            //request.addQueryParam("api_key",apikey);
        }
    };

    OkHttpClient okHttpClient = new OkHttpClient();
    //okHttpClient.setConnectTimeout(QUERY_TIMEOUT_SECONDS, TimeUnit.SECONDS);
   // okHttpClient.setReadTimeout(QUERY_TIMEOUT_SECONDS, TimeUnit.SECONDS);

    RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setClient(new OkClient(okHttpClient))
            .setLogLevel(RestAdapter.LogLevel.FULL);
    builder.setRequestInterceptor(requestInterceptor);
    RestAdapter adapter = builder.build();

    return adapter.create(serviceClass);

}
}
