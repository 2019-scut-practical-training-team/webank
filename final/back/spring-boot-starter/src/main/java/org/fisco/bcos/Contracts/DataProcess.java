package org.fisco.bcos.Contracts;

import java.math.BigInteger;
import java.util.Arrays;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class DataProcess extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061073c806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063b23983d914610051578063ff74927b146100f7575b600080fd5b34801561005d57600080fd5b5061007c6004803603810190808035906020019092919050505061021f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100bc5780820151818401526020810190506100a1565b50505050905090810190601f1680156100e95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561010357600080fd5b506101a4600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610516565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101e45780820151818401526020810190506101c9565b50505050905090810190601f1680156102115780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60606102296106e7565b60606000610140604051908101604052806040805190810160405280600181526020017f300000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f310000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f320000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f330000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f340000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f350000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f360000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f370000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f380000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600181526020017f3900000000000000000000000000000000000000000000000000000000000000815250815250925084905082600a808381151561049957fe5b04028203600a811015156104a957fe5b60200201519150600a818115156104bc57fe5b0490505b600081111561050b576104f583600a80848115156104da57fe5b04028303600a811015156104ea57fe5b602002015183610516565b9150600a8181151561050357fe5b0490506104c0565b819350505050919050565b606080606080606060008088955087945084518651016040519080825280601f01601f19166020018201604052801561055e5781602001602082028038833980820191505090505b50935083925060009150600090505b855181101561062057858181518110151561058457fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f01000000000000000000000000000000000000000000000000000000000000000283838060010194508151811015156105e357fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350808060010191505061056d565b600090505b84518110156106d857848181518110151561063c57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002838380600101945081518110151561069b57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610625565b83965050505050505092915050565b61014060405190810160405280600a905b60608152602001906001900390816106f857905050905600a165627a7a7230582073104b30aa7ae16eed13173d041b72760d22e50d4b76b0ec35d4177a48bf74ca0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"_input\",\"type\":\"uint256\"}],\"name\":\"getIntToString\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_a\",\"type\":\"string\"},{\"name\":\"_b\",\"type\":\"string\"}],\"name\":\"strConcat\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"}]";

    public static final String FUNC_GETINTTOSTRING = "getIntToString";

    public static final String FUNC_STRCONCAT = "strConcat";

    @Deprecated
    protected DataProcess(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DataProcess(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DataProcess(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DataProcess(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getIntToString(BigInteger _input) {
        final Function function = new Function(FUNC_GETINTTOSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_input)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> strConcat(String _a, String _b) {
        final Function function = new Function(FUNC_STRCONCAT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_a), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static DataProcess load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataProcess(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DataProcess load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataProcess(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DataProcess load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DataProcess(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DataProcess load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DataProcess(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DataProcess> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataProcess.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataProcess> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataProcess.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DataProcess> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataProcess.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataProcess> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataProcess.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
