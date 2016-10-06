package feignclient.api;

import feign.Param;
import feign.RequestLine;

/**
 * Created by suman.bn on 07/10/16.
 */
public interface APIInterface1 {

    @RequestLine("GET /geturl?queryparam={queryparam}")
    void getUrl(@Param("queryparam") String queryparam);
}
