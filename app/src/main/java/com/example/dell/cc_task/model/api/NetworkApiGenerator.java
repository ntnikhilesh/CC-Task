package com.example.dell.cc_task.model.api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import static com.example.dell.cc_task.model.utilities.Constants.BASE_URL;


public class NetworkApiGenerator {





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

    RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setClient(new OkClient(okHttpClient))
            .setLogLevel(RestAdapter.LogLevel.FULL);
    builder.setRequestInterceptor(requestInterceptor);
    RestAdapter adapter = builder.build();

    return adapter.create(serviceClass);

}
}
