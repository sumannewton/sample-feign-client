package feignclient;

import com.codahale.metrics.health.HealthCheck;
import feign.RequestLine;
import feignclient.api.APIInterface1;
import feignclient.api.APIInterface2;

/**
 * Created by suman.bn on 07/10/16.
 */
public interface SampleClientInterface extends APIInterface1,APIInterface2 {

    @RequestLine("GET /admin/healthcheck")
    HealthCheck.Result healthCheck();
}
