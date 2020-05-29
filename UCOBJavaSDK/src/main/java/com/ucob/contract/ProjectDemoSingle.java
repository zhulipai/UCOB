package com.ucob.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

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
public class ProjectDemoSingle extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b50604051602080620017618339810180604052810190808051906020019092919050505080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000fb60405180807f50524f4a4543545f4d414e414745525f524f4c450000000000000000000000008152506014019050604051809103902060405180807f50524f4a4543545f4d414e414745525f524f4c4500000000000000000000000081525060140190506040518091039020620001d7640100000000026401000000009004565b6200018060405180807f50524f4a4543545f53544146465f524f4c4500000000000000000000000000008152506012019050604051809103902060405180807f50524f4a4543545f4d414e414745525f524f4c4500000000000000000000000081525060140190506040518091039020620001d7640100000000026401000000009004565b620001d060405180807f50524f4a4543545f4d414e414745525f524f4c45000000000000000000000000815250601401905060405180910390203362000259640100000000026401000000009004565b5062000423565b80600019166000808460001916600019168152602001908152602001600020600201546000191683600019167fbd79b86ffe0ab8e8776151514217cd7cacd52c909f66475c3af44e129f0b00ff60405160405180910390a480600080846000191660001916815260200190815260200160002060020181600019169055505050565b62000274828262000278640100000000026401000000009004565b5050565b620002b78160008085600019166000191681526020019081526020016000206000016200032164010000000002620010b2179091906401000000009004565b156200031d573373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1683600019167f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45b5050565b60006200035a836000018373ffffffffffffffffffffffffffffffffffffffff1660010262000362640100000000026401000000009004565b905092915050565b60006200037f8383620003f8640100000000026401000000009004565b1515620003ed5782600001829080600181540180825580915050906001820390600052602060002001600090919290919091509060001916905550826000018054905083600101600084600019166000191681526020019081526020016000208190555060019050620003f2565b600090505b92915050565b6000808360010160008460001916600019168152602001908152602001600020541415905092915050565b61132e80620004336000396000f300608060405260043610610112576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806319311a1c14610117578063204720d81461014a578063248a9ca31461017d57806329c76a73146101ca5780632f2ff15d146101fd578063304a334a1461024e57806336568abe146102815780633e8b99a8146102d257806358739e64146103055780638513c619146103385780638eaa6ac0146103b35780639010d07c1461040057806391d148541461047b578063a217fddf146104e4578063a413d88214610517578063ca15c8731461054a578063d547741f1461058f578063d88b71ca146105e0578063edc1c4b314610613578063f71f7a2514610642575b600080fd5b34801561012357600080fd5b5061012c610681565b60405180826000191660001916815260200191505060405180910390f35b34801561015657600080fd5b5061015f6106ba565b60405180826000191660001916815260200191505060405180910390f35b34801561018957600080fd5b506101ac60048036038101908080356000191690602001909291905050506106f3565b60405180826000191660001916815260200191505060405180910390f35b3480156101d657600080fd5b506101df61071a565b60405180826000191660001916815260200191505060405180910390f35b34801561020957600080fd5b5061024c6004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610753565b005b34801561025a57600080fd5b50610263610761565b60405180826000191660001916815260200191505060405180910390f35b34801561028d57600080fd5b506102d06004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061079a565b005b3480156102de57600080fd5b506102e76107a8565b60405180826000191660001916815260200191505060405180910390f35b34801561031157600080fd5b5061031a6107e1565b60405180826000191660001916815260200191505060405180910390f35b34801561034457600080fd5b50610399600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061081a565b604051808215151515815260200191505060405180910390f35b3480156103bf57600080fd5b506103e26004803603810190808035600019169060200190929190505050610976565b60405180826000191660001916815260200191505060405180910390f35b34801561040c57600080fd5b5061043960048036038101908080356000191690602001909291908035906020019092919050505061099b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561048757600080fd5b506104ca6004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109d4565b604051808215151515815260200191505060405180910390f35b3480156104f057600080fd5b506104f9610a0d565b60405180826000191660001916815260200191505060405180910390f35b34801561052357600080fd5b5061052c610a15565b60405180826000191660001916815260200191505060405180910390f35b34801561055657600080fd5b506105796004803603810190808035600019169060200190929190505050610a4e565b6040518082815260200191505060405180910390f35b34801561059b57600080fd5b506105de6004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a7c565b005b3480156105ec57600080fd5b506105f5610a8a565b60405180826000191660001916815260200191505060405180910390f35b34801561061f57600080fd5b50610628610ac3565b604051808215151515815260200191505060405180910390f35b34801561064e57600080fd5b5061067f60048036038101908080356000191690602001909291908035600019169060200190929190505050610acc565b005b60405180807f434f52504f524154494f4e5f4252414e43485f4d414e414745525f524f4c4500815250601f019050604051809103902081565b60405180807f434f52504f524154494f4e5f43454f5f524f4c450000000000000000000000008152506014019050604051809103902081565b60008060008360001916600019168152602001908152602001600020600201549050919050565b60405180807f50524f4a4543545f4d414e414745525f524f4c450000000000000000000000008152506014019050604051809103902081565b61075d8282610ba7565b5050565b60405180807f50524f4a4543545f53544146465f524f4c4500000000000000000000000000008152506012019050604051809103902081565b6107a48282610c76565b5050565b60405180807f434f52504f524154494f4e5f4d454d424552535f524f4c4500000000000000008152506018019050604051809103902081565b60405180807f434f52504f524154494f4e5f53544146465f524f4c45000000000000000000008152506016019050604051809103902081565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151561092d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252604b8152602001807f6d616e616765722063616e206f6e6c7920636f6d652066726f6d20746865206381526020017f6f72706f726174696f6e2074686174206f776e656420746869732070726f6a6581526020017f63742d636f6e747261637400000000000000000000000000000000000000000081525060600191505060405180910390fd5b61096c60405180807f50524f4a4543545f4d414e414745525f524f4c450000000000000000000000008152506014019050604051809103902083610753565b6001905092915050565b6000600260008360001916600019168152602001908152602001600020549050919050565b60006109cc826000808660001916600019168152602001908152602001600020600001610d4d90919063ffffffff16565b905092915050565b6000610a05826000808660001916600019168152602001908152602001600020600001610d6890919063ffffffff16565b905092915050565b600060010281565b60405180807f554e495445445f50524f4a4543545f4d414e414745525f524f4c450000000000815250601b019050604051809103902081565b6000610a756000808460001916600019168152602001908152602001600020600001610d98565b9050919050565b610a868282610dad565b5050565b60405180807f434f52504f524154494f4e5f43464f5f524f4c450000000000000000000000008152506014019050604051809103902081565b60006001905090565b610b0b60405180807f50524f4a4543545f53544146465f524f4c45000000000000000000000000000081525060120190506040518091039020336109d4565b1515610b7f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f7573657220686173206e6f20726967687400000000000000000000000000000081525060200191505060405180910390fd5b8060026000846000191660001916815260200190815260200160002081600019169055505050565b610bce600080846000191660001916815260200190815260200160002060020154336109d4","565b1515610c68576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f416363657373436f6e74726f6c3a2073656e646572206d75737420626520616e81526020017f2061646d696e20746f206772616e74000000000000000000000000000000000081525060400191505060405180910390fd5b610c728282610e7c565b5050565b3373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141515610d3f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636581526020017f20726f6c657320666f722073656c66000000000000000000000000000000000081525060400191505060405180910390fd5b610d498282610f14565b5050565b6000610d5c8360000183610fac565b60019004905092915050565b6000610d90836000018373ffffffffffffffffffffffffffffffffffffffff16600102611076565b905092915050565b6000610da6826000016110a1565b9050919050565b610dd4600080846000191660001916815260200190815260200160002060020154336109d4565b1515610e6e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001807f416363657373436f6e74726f6c3a2073656e646572206d75737420626520616e81526020017f2061646d696e20746f207265766f6b650000000000000000000000000000000081525060400191505060405180910390fd5b610e788282610f14565b5050565b610eab8160008085600019166000191681526020019081526020016000206000016110b290919063ffffffff16565b15610f10573373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1683600019167f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45b5050565b610f438160008085600019166000191681526020019081526020016000206000016110e290919063ffffffff16565b15610fa8573373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1683600019167ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b60405160405180910390a45b5050565b6000818360000180549050111515611052576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f456e756d657261626c655365743a20696e646578206f7574206f6620626f756e81526020017f647300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b826000018281548110151561106357fe5b9060005260206000200154905092915050565b6000808360010160008460001916600019168152602001908152602001600020541415905092915050565b600081600001805490509050919050565b60006110da836000018373ffffffffffffffffffffffffffffffffffffffff16600102611112565b905092915050565b600061110a836000018373ffffffffffffffffffffffffffffffffffffffff16600102611195565b905092915050565b600061111e8383611076565b151561118a578260000182908060018154018082558091505090600182039060005260206000200160009091929091909150906000191690555082600001805490508360010160008460001916600019168152602001908152602001600020819055506001905061118f565b600090505b92915050565b600080600080600086600101600087600019166000191681526020019081526020016000205493506000841415156112a2576001840392506001876000018054905003915086600001828154811015156111eb57fe5b9060005260206000200154905080876000018481548110151561120a57fe5b90600052602060002001816000191690555060018301876001016000836000191660001916815260200190815260200160002081905550866000018281548110151561125257fe5b90600052602060002001600090558660000180548091906001900361127791906112b1565b50866001016000876000191660001916815260200190815260200160002060009055600194506112a7565b600094505b5050505092915050565b8154818355818111156112d8578183600052602060002091820191016112d791906112dd565b5b505050565b6112ff91905b808211156112fb5760008160009055506001016112e3565b5090565b905600a165627a7a7230582057ec25ef3db15458a7e9ff7c664aa88d4d953496a7501ea9a67cc6fdca3303e10029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_BRANCH_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_CEO_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"}],\"name\":\"getRoleAdmin\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"PROJECT_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"grantRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"PROJECT_STAFF_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"renounceRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_MEMBERS_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_STAFF_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"corporationAddress\",\"type\":\"address\"},{\"name\":\"managerAddress\",\"type\":\"address\"}],\"name\":\"addManager\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"key\",\"type\":\"bytes32\"}],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getRoleMember\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"hasRole\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"DEFAULT_ADMIN_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"UNITED_PROJECT_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"}],\"name\":\"getRoleMemberCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"revokeRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_CFO_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"initProject\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"key\",\"type\":\"bytes32\"},{\"name\":\"value\",\"type\":\"bytes32\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"corporationControllerAddress\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"role\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"previousAdminRole\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"newAdminRole\",\"type\":\"bytes32\"}],\"name\":\"RoleAdminChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"role\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"account\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"sender\",\"type\":\"address\"}],\"name\":\"RoleGranted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"role\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"account\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"sender\",\"type\":\"address\"}],\"name\":\"RoleRevoked\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_CORPORATION_BRANCH_MANAGER_ROLE = "CORPORATION_BRANCH_MANAGER_ROLE";

    public static final String FUNC_CORPORATION_CEO_ROLE = "CORPORATION_CEO_ROLE";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_PROJECT_MANAGER_ROLE = "PROJECT_MANAGER_ROLE";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_PROJECT_STAFF_ROLE = "PROJECT_STAFF_ROLE";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_CORPORATION_MEMBERS_ROLE = "CORPORATION_MEMBERS_ROLE";

    public static final String FUNC_CORPORATION_STAFF_ROLE = "CORPORATION_STAFF_ROLE";

    public static final String FUNC_ADDMANAGER = "addManager";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETROLEMEMBER = "getRoleMember";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_UNITED_PROJECT_MANAGER_ROLE = "UNITED_PROJECT_MANAGER_ROLE";

    public static final String FUNC_GETROLEMEMBERCOUNT = "getRoleMemberCount";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_CORPORATION_CFO_ROLE = "CORPORATION_CFO_ROLE";

    public static final String FUNC_INITPROJECT = "initProject";

    public static final String FUNC_SET = "set";

    public static final Event ROLEADMINCHANGED_EVENT = new Event("RoleAdminChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected ProjectDemoSingle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProjectDemoSingle(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProjectDemoSingle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProjectDemoSingle(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<byte[]> CORPORATION_BRANCH_MANAGER_ROLE() {
        final Function function = new Function(FUNC_CORPORATION_BRANCH_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> CORPORATION_CEO_ROLE() {
        final Function function = new Function(FUNC_CORPORATION_CEO_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> PROJECT_MANAGER_ROLE() {
        final Function function = new Function(FUNC_PROJECT_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> grantRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void grantRole(byte[] role, String account, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String grantRoleSeq(byte[] role, String account) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<byte[], String> getGrantRoleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_GRANTROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<byte[]> PROJECT_STAFF_ROLE() {
        final Function function = new Function(FUNC_PROJECT_STAFF_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> renounceRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void renounceRole(byte[] role, String account, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String renounceRoleSeq(byte[] role, String account) {
        final Function function = new Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<byte[], String> getRenounceRoleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<byte[]> CORPORATION_MEMBERS_ROLE() {
        final Function function = new Function(FUNC_CORPORATION_MEMBERS_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> CORPORATION_STAFF_ROLE() {
        final Function function = new Function(FUNC_CORPORATION_STAFF_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> addManager(String corporationAddress, String managerAddress) {
        final Function function = new Function(
                FUNC_ADDMANAGER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(managerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addManager(String corporationAddress, String managerAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDMANAGER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(managerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addManagerSeq(String corporationAddress, String managerAddress) {
        final Function function = new Function(
                FUNC_ADDMANAGER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(managerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, String> getAddManagerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getAddManagerOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<byte[]> get(byte[] key) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> getRoleMember(byte[] role, BigInteger index) {
        final Function function = new Function(FUNC_GETROLEMEMBER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> hasRole(byte[] role, String account) {
        final Function function = new Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final Function function = new Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> UNITED_PROJECT_MANAGER_ROLE() {
        final Function function = new Function(FUNC_UNITED_PROJECT_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> getRoleMemberCount(byte[] role) {
        final Function function = new Function(FUNC_GETROLEMEMBERCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void revokeRole(byte[] role, String account, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String revokeRoleSeq(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<byte[], String> getRevokeRoleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REVOKEROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<byte[]> CORPORATION_CFO_ROLE() {
        final Function function = new Function(FUNC_CORPORATION_CFO_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> initProject() {
        final Function function = new Function(
                FUNC_INITPROJECT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void initProject(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INITPROJECT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String initProjectSeq() {
        final Function function = new Function(
                FUNC_INITPROJECT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<Boolean> getInitProjectOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_INITPROJECT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> set(byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(byte[] key, byte[] value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<byte[], byte[]> getSetInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<byte[], byte[]>(

                (byte[]) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue()
                );
    }

    public List<RoleAdminChangedEventResponse> getRoleAdminChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<RoleAdminChangedEventResponse> responses = new ArrayList<RoleAdminChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRoleAdminChangedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEADMINCHANGED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRoleAdminChangedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEADMINCHANGED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<RoleGrantedEventResponse> getRoleGrantedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEGRANTED_EVENT, transactionReceipt);
        ArrayList<RoleGrantedEventResponse> responses = new ArrayList<RoleGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRoleGrantedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEGRANTED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRoleGrantedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEGRANTED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<RoleRevokedEventResponse> getRoleRevokedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEREVOKED_EVENT, transactionReceipt);
        ArrayList<RoleRevokedEventResponse> responses = new ArrayList<RoleRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRoleRevokedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEREVOKED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRoleRevokedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ROLEREVOKED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static ProjectDemoSingle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProjectDemoSingle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProjectDemoSingle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProjectDemoSingle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProjectDemoSingle load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ProjectDemoSingle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProjectDemoSingle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProjectDemoSingle(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProjectDemoSingle> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String corporationControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationControllerAddress)));
        return deployRemoteCall(ProjectDemoSingle.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ProjectDemoSingle> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String corporationControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationControllerAddress)));
        return deployRemoteCall(ProjectDemoSingle.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ProjectDemoSingle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String corporationControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationControllerAddress)));
        return deployRemoteCall(ProjectDemoSingle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ProjectDemoSingle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String corporationControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationControllerAddress)));
        return deployRemoteCall(ProjectDemoSingle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class RoleAdminChangedEventResponse {
        public Log log;

        public byte[] role;

        public byte[] previousAdminRole;

        public byte[] newAdminRole;
    }

    public static class RoleGrantedEventResponse {
        public Log log;

        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse {
        public Log log;

        public byte[] role;

        public String account;

        public String sender;
    }
}
