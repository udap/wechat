package cn.iclass.wechat.controllers;

import cn.iclass.iclassboot.controllers.BaseControllerAbstract;
import cn.iclass.iclassboot.results.Result;
import cn.iclass.wechat.dals.MiniContractRepository;
import cn.iclass.wechat.models.MiniContractModel;
import cn.iclass.wechat.smartContract.MiniContract;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Setter
@Getter
@RestController
@RequestMapping("/api/v1/MiniContract")
public class MiniContractController extends BaseControllerAbstract{

    //put controller, service, ethInvoker(part of service) all in controller

    @Autowired
    public Web3j web3j;

    @Autowired
    public MiniContractRepository miniContractRepository;

    public Credentials credentials = Credentials.create("79972a6328ab7fd6bc90cae4b72fa41e9ad6ee376f39c0ebd5c4926157f1e97f");

    @Value("${mini-contract.ethereum-address}")
    public String contractAddress;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Result create(@RequestBody MiniContractModel miniContractModel){
        miniContractModel.signedA = true;
        miniContractModel.signedB = false;
        miniContractModel.finishedA =false;
        miniContractModel.finishedB =false;
        miniContractRepository.saveAndFlush(miniContractModel);
        return  succeed();
    }

    @RequestMapping(path = "/accept", method = RequestMethod.POST)
    public Result accept(@RequestParam long id, @RequestParam String B) throws Exception {
        MiniContractModel miniContractModel = miniContractRepository.findById(id);
        miniContractModel.B=B;
        miniContractModel.signedB =true;

        //it must be true
        if(miniContractModel.signedA == true){
            // request to ethereum and get dbindex back
            MiniContract miniContract = MiniContract.load(contractAddress,web3j, credentials, BigInteger.ZERO, BigInteger.valueOf(100000000l));
            String dbindexstr = miniContract.mint(
                    StringToBytesInUTF8(miniContractModel.A),
                    StringToBytesInUTF8(miniContractModel.B),
                    miniContractModel.title,
                    miniContractModel.content,
                    miniContractModel.sound,
                    miniContractModel.picture
            ).send().getLogs().get(0).getData();

            long dbindex = Long.parseLong(dbindexstr);
            miniContractModel.dbindex = dbindex;

            miniContractRepository.saveAndFlush(miniContractModel);
        }
        return  succeed();
    }

    @RequestMapping(path = "/complete", method = RequestMethod.POST)
    public Result complete(@RequestParam long id, @RequestParam String AorB) throws Exception {
        MiniContractModel miniContractModel = miniContractRepository.findById(id);
        if(miniContractModel.A.equals(AorB)){
            miniContractModel.finishedA =true;
        }
        else if(miniContractModel.B.equals(AorB)){
            miniContractModel.finishedB =true;
        }
        else {
            return fail();
        }
        miniContractRepository.saveAndFlush(miniContractModel);

        if(miniContractModel.finishedA == true && miniContractModel.finishedB == true){
            // request to ethereum to 'complete'

            MiniContract miniContract = MiniContract.load(contractAddress,web3j, credentials, BigInteger.ZERO, BigInteger.valueOf(100000000l));
            miniContract.complete(BigInteger.valueOf(miniContractModel.dbindex)).send();
            return  succeed();
        }
        return  succeed();
    }

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public Result list(@RequestParam String AorB) throws Exception {
        //query ethereum to get list

        MiniContract miniContract = MiniContract.load(contractAddress,web3j, credentials, BigInteger.ZERO, BigInteger.valueOf(100000000l));
        List l =  miniContract.list(StringToBytesInUTF8(AorB)).send();

        return  succeed(l);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.POST)
    public Result detail(@RequestParam long dbindex) throws Exception {

        MiniContractModel miniContractModelInDB = miniContractRepository.findById(dbindex);

        //query ethereum to get detail
        MiniContract miniContract = MiniContract.load(contractAddress,web3j, credentials, BigInteger.ZERO, BigInteger.valueOf(100000000l));
        Tuple7 result = miniContract.detail(BigInteger.valueOf(dbindex)).send();
        MiniContractModel miniContractModel = new MiniContractModel(
                BytesInUTF8ToString((byte[])result.getValue1()),
                BytesInUTF8ToString((byte[])result.getValue2()),
                miniContractModelInDB.signedA,
                miniContractModelInDB.signedB,
                miniContractModelInDB.finishedA,
                miniContractModelInDB.finishedB,
                //(Boolean)result.getValue3(),
                (byte[])result.getValue4(),
                (byte[])result.getValue5(),
                (byte[])result.getValue6(),
                (byte[])result.getValue7(),
                dbindex
        );

        return  succeed(miniContractModel);
    }

    @ApiOperation("Do Not Use This Method, This is only for test use")
    @RequestMapping(path = "/deploy", method = RequestMethod.POST)
    public Result deploy() throws Exception {
        contractAddress = MiniContract.deploy(web3j, credentials, BigInteger.ZERO, BigInteger.valueOf(100000000l)).send().getContractAddress();
        //query ethereum to get detail
        return  succeed();
    }

    public byte[] StringToBytesInUTF8(String str){
        return str.getBytes(Charset.forName("UTF-8"));
    }

    public String BytesInUTF8ToString(byte[] b){
        return new String(b,Charset.forName("UTF-8"));
    }
}
