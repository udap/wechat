package cn.iclass.wechat.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

@Slf4j
@Component
public class OnStartUp {

    public static Web3j web3j = Web3j.build(new HttpService("http://localhost:8080/rpc"));

    //@Scheduled(initialDelay = 10 * 1000, fixedDelay = Long.MAX_VALUE)
    public void web3jListener() {
        log.info("OnStartUpJob");


        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST, (List<String>) null);
        web3j.ethLogObservable(filter).subscribe(log -> {
            String topic = log.getTopics().get(0);
            //your code to operate db cache
        });

    }

    public static void main(String[] args) {
        Observable observable = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        });
    }
}
