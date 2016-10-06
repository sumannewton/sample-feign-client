import feign.*;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feignclient.SampleClientInterface;
import utils.ModelUtils;
import utils.SampleClientException;

/**
 * Created by suman.bn on 07/10/16.
 */
public class SampleClient {

    static class SampleClientHeadersInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            template.header("Accept", "application/json");
            template.header("Content-Type", "application/json");
        }
    }

    static class SampleClientErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            return new SampleClientException(response.status(), response.reason());
        }
    }

    public static SampleClientInterface getInstance(String endPoint) {
        return getInstance(endPoint, Logger.Level.BASIC, 1000, 60000);
    }

    public static SampleClientInterface getInstance(String endPoint, int connectTimeoutMillis, int readTimeoutMillis) {
        return getInstance(endPoint, Logger.Level.BASIC, connectTimeoutMillis, readTimeoutMillis);
    }

    public static SampleClientInterface getInstance(String endpoint, Logger.Level logLevel, int connectTimeoutMillis, int readTimeoutMillis) {
        JacksonEncoder encoder = new JacksonEncoder(ModelUtils.MAPPER);
        JacksonDecoder decoder = new JacksonDecoder(ModelUtils.MAPPER);
        return Feign.builder()
                .logger(new Logger.JavaLogger())
                .logLevel(logLevel)
                .options(new Request.Options(connectTimeoutMillis, readTimeoutMillis))
                .encoder(encoder)
                .decoder(decoder)
                .client(new OkHttpClient())
                .errorDecoder(new SampleClientErrorDecoder())
                .requestInterceptor(new SampleClientHeadersInterceptor())
                .target(SampleClientInterface.class, endpoint);
    }
}
