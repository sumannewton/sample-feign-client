package feignclient.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Created by suman.bn on 07/10/16.
 */
public interface APIInterface2 {

    @RequestLine("POST /posturl")
    @Headers("header: {header}")
    void postUrl(@Param("header") String header);
}
