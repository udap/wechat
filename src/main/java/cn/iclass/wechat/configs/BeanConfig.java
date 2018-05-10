package cn.iclass.wechat.configs;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class BeanConfig {
    /*@Bean
    public Admin getAdmin() {
        return Admin.build(new HttpService("http://localhost:8545/"));
    }*/

    @Value("${iclass.debug:false}")
    public boolean debug;

    /*@Bean
    public Web3j getWeb3j() {
        if (!debug) {
            return Web3j.build(new HttpService("http://localhost:8080/rpc"));
        } else {
            return Web3j.build(new HttpService("http://localhost:8080/rpc", createOkHttpClient(), false));
        }
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        builder.connectTimeout(0l, TimeUnit.SECONDS);
        builder.readTimeout(0l, TimeUnit.SECONDS);
        builder.writeTimeout(0l, TimeUnit.SECONDS);
        return builder.build();
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }*/
}
