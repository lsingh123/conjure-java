package com.palantir.product;

import com.google.common.util.concurrent.ListenableFuture;
import com.palantir.conjure.java.lib.internal.ClientEndpoint;
import com.palantir.tokens.auth.AuthHeader;
import java.util.Optional;
import javax.annotation.processing.Generated;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

@Generated("com.palantir.conjure.java.services.Retrofit2ServiceGenerator")
public interface EteBinaryServiceRetrofit {
    @POST("./binary")
    @Headers({"hr-path-template: /binary", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "POST", path = "/binary")
    ListenableFuture<ResponseBody> postBinary(@Header("Authorization") AuthHeader authHeader, @Body RequestBody body);

    @POST("./binary/throws")
    @Headers({"hr-path-template: /binary/throws", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "POST", path = "/binary/throws")
    ListenableFuture<ResponseBody> postBinaryThrows(
            @Header("Authorization") AuthHeader authHeader,
            @Query("bytesToRead") int bytesToRead,
            @Body RequestBody body);

    @GET("./binary/optional/present")
    @Headers({"hr-path-template: /binary/optional/present", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "GET", path = "/binary/optional/present")
    ListenableFuture<Optional<ResponseBody>> getOptionalBinaryPresent(@Header("Authorization") AuthHeader authHeader);

    @GET("./binary/optional/empty")
    @Headers({"hr-path-template: /binary/optional/empty", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "GET", path = "/binary/optional/empty")
    ListenableFuture<Optional<ResponseBody>> getOptionalBinaryEmpty(@Header("Authorization") AuthHeader authHeader);

    /**
     * Throws an exception after partially writing a binary response.
     */
    @GET("./binary/failure")
    @Headers({"hr-path-template: /binary/failure", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "GET", path = "/binary/failure")
    ListenableFuture<ResponseBody> getBinaryFailure(
            @Header("Authorization") AuthHeader authHeader, @Query("numBytes") int numBytes);

    @GET("./binary/aliased")
    @Headers({"hr-path-template: /binary/aliased", "Accept: application/octet-stream"})
    @Streaming
    @ClientEndpoint(method = "GET", path = "/binary/aliased")
    ListenableFuture<Optional<ResponseBody>> getAliased(@Header("Authorization") AuthHeader authHeader);
}
