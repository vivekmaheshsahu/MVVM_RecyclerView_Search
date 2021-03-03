package com.android.greenlight.common.data.retrofit;

import com.android.greenlight.common.data.service.NetworkCallServiceAPI;
import com.android.greenlight.common.data.service.NetworkService;

import retrofit2.Retrofit;

/**
 * @author Vivek
 * Retrofit DataSource used for retrofit network call
 */

public class RemoteDataSource {
    private static RemoteDataSource mRemoteDataSource;
    private final Retrofit mRestClient;

    private RemoteDataSource(Retrofit restClient) {
        mRestClient = restClient;
    }

    public static RemoteDataSource getInstance() {
        if (mRemoteDataSource == null) {
            mRemoteDataSource = new RemoteDataSource(RestClient.getClient());
        }
        return mRemoteDataSource;
    }

    public NetworkService getDataService() {
        return new NetworkService(createApiService(NetworkCallServiceAPI.class));
    }

    public <T> T createApiService(Class<T> apiInterface) {
        return mRestClient.create(apiInterface);
    }

}
