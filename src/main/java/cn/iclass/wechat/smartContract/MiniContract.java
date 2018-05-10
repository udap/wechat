package cn.iclass.wechat.smartContract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class MiniContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a0319909116179055610f0b8061003b6000396000f3006060604052600436106100615763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663971d852f8114610066578063b16daca11461007e578063db487f3614610122578063e47e2bac146103c8575b600080fd5b341561007157600080fd5b61007c600435610563565b005b341561008957600080fd5b6100cf60046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506105e195505050505050565b60405160208082528190810183818151815260200191508051906020019060200280838360005b8381101561010e5780820151838201526020016100f6565b505050509050019250505060405180910390f35b341561012d57600080fd5b6101386004356106a4565b604051808060200180602001881515151581526020018060200180602001806020018060200187810387528e818151815260200191508051906020019080838360005b8381101561019357808201518382015260200161017b565b50505050905090810190601f1680156101c05780820380516001836020036101000a031916815260200191505b5087810386528d818151815260200191508051906020019080838360005b838110156101f65780820151838201526020016101de565b50505050905090810190601f1680156102235780820380516001836020036101000a031916815260200191505b5087810385528b818151815260200191508051906020019080838360005b83811015610259578082015183820152602001610241565b50505050905090810190601f1680156102865780820380516001836020036101000a031916815260200191505b5087810384528a818151815260200191508051906020019080838360005b838110156102bc5780820151838201526020016102a4565b50505050905090810190601f1680156102e95780820380516001836020036101000a031916815260200191505b50878103835289818151815260200191508051906020019080838360005b8381101561031f578082015183820152602001610307565b50505050905090810190601f16801561034c5780820380516001836020036101000a031916815260200191505b50878103825288818151815260200191508051906020019080838360005b8381101561038257808201518382015260200161036a565b50505050905090810190601f1680156103af5780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390f35b34156103d357600080fd5b61007c60046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528181529291906020840183838082843750949650610b1a95505050505050565b6000543373ffffffffffffffffffffffffffffffffffffffff90811691161461058b57600080fd5b600081815260016020819052604091829020600201805460ff191690911790557fd0e55388c46e2da0ce466e229fea0cdd881aaaab1c1af0411dad1071221437fe9082905190815260200160405180910390a150565b6105e9610da8565b6003826040518082805190602001908083835b6020831061061b5780518252601f1990920191602091820191016105fc565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902080548060200260200160405190810160405280929190818152602001828054801561069857602002820191906000526020600020905b815481526020019060010190808311610684575b50505050509050919050565b6106ac610da8565b6106b4610da8565b60006106be610da8565b6106c6610da8565b6106ce610da8565b6106d6610da8565b600160008981526020019081526020016000206000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107805780601f1061075557610100808354040283529160200191610780565b820191906000526020600020905b81548152906001019060200180831161076357829003601f168201915b50505050509650600160008981526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108315780601f1061080657610100808354040283529160200191610831565b820191906000526020600020905b81548152906001019060200180831161081457829003601f168201915b50505060008b81526001602081815260409283902060028082015460039092018054989e5060ff9092169c509096600019938116156101000293909301909216919091049350601f840181900481020191505190810160405280929190818152602001828054600181600116156101000203166002900480156108f55780601f106108ca576101008083540402835291602001916108f5565b820191906000526020600020905b8154815290600101906020018083116108d857829003601f168201915b50505050509350600160008981526020019081526020016000206004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109a65780601f1061097b576101008083540402835291602001916109a6565b820191906000526020600020905b81548152906001019060200180831161098957829003601f168201915b50505050509250600160008981526020019081526020016000206005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a575780601f10610a2c57610100808354040283529160200191610a57565b820191906000526020600020905b815481529060010190602001808311610a3a57829003601f168201915b50505050509150600160008981526020019081526020016000206006018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b085780601f10610add57610100808354040283529160200191610b08565b820191906000526020600020905b815481529060010190602001808311610aeb57829003601f168201915b50505050509050919395979092949650565b610b22610dba565b6000543373ffffffffffffffffffffffffffffffffffffffff908116911614610b4a57600080fd5b60e06040519081016040528088815260200187815260200160001515815260200186815260200185815260200184815260200183815250905080600160006002548152602001908152602001600020600082015181908051610bb0929160200190610e1b565b50602082015181600101908051610bcb929160200190610e1b565b50604082015160028201805460ff1916911515919091179055606082015181600301908051610bfe929160200190610e1b565b50608082015181600401908051610c19929160200190610e1b565b5060a082015181600501908051610c34929160200190610e1b565b5060c082015181600601908051610c4f929160200190610e1b565b509050506003876040518082805190602001908083835b60208310610c855780518252601f199092019160209182019101610c66565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020805460018101610cc98382610e99565b50600091825260209091206002549101556003866040518082805190602001908083835b60208310610d0c5780518252601f199092019160209182019101610ced565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051908190039020805460018101610d508382610e99565b506000918252602090912060025491018190557fd3b769a2937619e52217a71926fa1771bc08121dfc62429ef7c72dca27e84e239060405190815260200160405180910390a150506002805460010190555050505050565b60206040519081016040526000815290565b60e060405190810160405280610dce610da8565b8152602001610ddb610da8565b815260006020820152604001610def610da8565b8152602001610dfc610da8565b8152602001610e09610da8565b8152602001610e16610da8565b905290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e5c57805160ff1916838001178555610e89565b82800160010185558215610e89579182015b82811115610e89578251825591602001919060010190610e6e565b50610e95929150610ec2565b5090565b815481835581811511610ebd57600083815260209020610ebd918101908301610ec2565b505050565b610edc91905b80821115610e955760008155600101610ec8565b905600a165627a7a723058206fefee86646a6080642bf3d86f19e7f9f9bd6929adc6b8895483a77e7e533b970029";

    protected MiniContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MiniContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<EMintEventResponse> getEMintEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("eMint", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<EMintEventResponse> responses = new ArrayList<EMintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EMintEventResponse typedResponse = new EMintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dbindex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<EMintEventResponse> eMintEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("eMint", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, EMintEventResponse>() {
            @Override
            public EMintEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                EMintEventResponse typedResponse = new EMintEventResponse();
                typedResponse.log = log;
                typedResponse.dbindex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<ECompleteEventResponse> getECompleteEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("eComplete", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<ECompleteEventResponse> responses = new ArrayList<ECompleteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ECompleteEventResponse typedResponse = new ECompleteEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dbindex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ECompleteEventResponse> eCompleteEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("eComplete", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ECompleteEventResponse>() {
            @Override
            public ECompleteEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                ECompleteEventResponse typedResponse = new ECompleteEventResponse();
                typedResponse.log = log;
                typedResponse.dbindex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> complete(BigInteger _dbindex) {
        final Function function = new Function(
                "complete", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_dbindex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> list(byte[] _owner) {
        final Function function = new Function("list", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<Tuple7<byte[], byte[], Boolean, byte[], byte[], byte[], byte[]>> detail(BigInteger _dbindex) {
        final Function function = new Function("detail", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_dbindex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Bool>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteCall<Tuple7<byte[], byte[], Boolean, byte[], byte[], byte[], byte[]>>(
                new Callable<Tuple7<byte[], byte[], Boolean, byte[], byte[], byte[], byte[]>>() {
                    @Override
                    public Tuple7<byte[], byte[], Boolean, byte[], byte[], byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<byte[], byte[], Boolean, byte[], byte[], byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (byte[]) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> mint(byte[] _A, byte[] _B, byte[] _title, byte[] _content, byte[] _sound, byte[] _picture) {
        final Function function = new Function(
                "mint", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_A), 
                new org.web3j.abi.datatypes.DynamicBytes(_B), 
                new org.web3j.abi.datatypes.DynamicBytes(_title), 
                new org.web3j.abi.datatypes.DynamicBytes(_content), 
                new org.web3j.abi.datatypes.DynamicBytes(_sound), 
                new org.web3j.abi.datatypes.DynamicBytes(_picture)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<MiniContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MiniContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MiniContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MiniContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static MiniContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MiniContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MiniContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MiniContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class EMintEventResponse {
        public Log log;

        public BigInteger dbindex;
    }

    public static class ECompleteEventResponse {
        public Log log;

        public BigInteger dbindex;
    }
}
