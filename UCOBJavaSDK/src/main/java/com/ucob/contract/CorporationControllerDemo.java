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
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
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
public class CorporationControllerDemo extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5060405160408061295e8339810180604052810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505061288f806100cf6000396000f30060806040526004361061011d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063078d3b79146101c257806312d9a6ad1461024757806319311a1c146102b0578063204720d8146102e357806329c76a73146103165780632f2ff15d14610349578063304a334a1461039a5780633e8b99a8146103cd57806358739e64146104005780636191698d14610433578063a413d88214610492578063a6f9dae1146104c5578063a9059cbb14610508578063bd76bf8a1461056d578063ca15c87314610612578063d547741f14610657578063d88b71ca146106a8578063e9d9c3a1146106db578063ed3e17a41461070a578063f042e02014610753578063f31d7dcf146107b8575b34801561012957600080fd5b50600061016b60405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b151561017657600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050366000803760008036600080855af13d6000803e80600081146101bd573d6000f35b3d6000fd5b3480156101ce57600080fd5b5061022d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610982565b604051808215151515815260200191505060405180910390f35b34801561025357600080fd5b506102966004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107fb565b604051808215151515815260200191505060405180910390f35b3480156102bc57600080fd5b506102c5610b86565b60405180826000191660001916815260200191505060405180910390f35b3480156102ef57600080fd5b506102f8610bbf565b60405180826000191660001916815260200191505060405180910390f35b34801561032257600080fd5b5061032b610bf8565b60405180826000191660001916815260200191505060405180910390f35b34801561035557600080fd5b506103986004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c31565b005b3480156103a657600080fd5b506103af610d63565b60405180826000191660001916815260200191505060405180910390f35b3480156103d957600080fd5b506103e2610d9c565b60405180826000191660001916815260200191505060405180910390f35b34801561040c57600080fd5b50610415610dd5565b60405180826000191660001916815260200191505060405180910390f35b34801561043f57600080fd5b5061049060048036038101908080356000191690602001909291908035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e0e565b005b34801561049e57600080fd5b506104a76114e2565b60405180826000191660001916815260200191505060405180910390f35b3480156104d157600080fd5b50610506600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061151b565b005b34801561051457600080fd5b50610553600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061186e565b604051808215151515815260200191505060405180910390f35b34801561057957600080fd5b506105f8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611a13565b604051808215151515815260200191505060405180910390f35b34801561061e57600080fd5b506106416004803603810190808035600019169060200190929190505050611cb9565b6040518082815260200191505060405180910390f35b34801561066357600080fd5b506106a66004803603810190808035600019169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611d95565b005b3480156106b457600080fd5b506106bd611ec7565b60405180826000191660001916815260200191505060405180910390f35b3480156106e757600080fd5b506106f0611f00565b604051808215151515815260200191505060405180910390f35b34801561071657600080fd5b506107396004803603810190808035600019169060200190929190505050611ff5565b604051808215151515815260200191505060405180910390f35b34801561075f57600080fd5b5061079e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050612470565b604051808215151515815260200191505060405180910390f35b3480156107c457600080fd5b506107f9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612772565b005b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166391d1485484846040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b1580156108c957600080fd5b505af11580156108dd573d6000803e3d6000fd5b505050506040513d60208110156108f357600080fd5b81019080805190602001909291905050501515610978576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f796f7520646f206e6f74206861766520726967687420726f6c6500000000000081525060200191505060405180910390fd5b6001905092915050565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166385e2b880863387876040518563ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001945050505050602060405180830381600087803b158015610ab157600080fd5b505af1158015610ac5573d6000803e3d6000fd5b505050506040513d6020811015610adb57600080fd5b810190808051906020019092919050505090508373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff167f221c885199c2b06bfb79ef9d9cc6a27bd2e71ca67a6b15bcc74e9aa198b0b4c284876040518083600019166000191681526020018281526020019250505060405180910390a460019150509392505050565b60405180807f434f52504f524154494f4e5f4252414e43485f4d414e414745525f524f4c4500815250601f019050604051809103902081565b60405180807f434f52504f524154494f4e5f43454f5f524f4c450000000000000000000000008152506014019050604051809103902081565b60405180807f50524f4a4543545f4d414e414745525f524f4c450000000000000000000000008152506014019050604051809103902081565b610c7060405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b1515610c7b57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632f2ff15d83836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610d4757600080fd5b505af1158015610d5b573d6000803e3d6000fd5b505050505050565b60405180807f50524f4a4543545f53544146465f524f4c4500000000000000000000000000008152506012019050604051809103902081565b60405180807f434f52504f524154494f4e5f4d454d424552535f524f4c4500000000000000008152506018019050604051809103902081565b60405180807f434f52504f524154494f4e5f53544146465f524f4c45000000000000000000008152506016019050604051809103902081565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663158ef93e6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e9557600080fd5b505af1158015610ea9573d6000803e3d6000fd5b505050506040513d6020811015610ebf57600080fd5b8101908080519060200190929190505050905060001515811515148015610eea5750610ee9611f00565b5b156112ed576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638129fc1c60405181","63ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b158015610f7457600080fd5b505af1158015610f88573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e4e009160405180807f434f52504f524154494f4e5f43454f5f524f4c450000000000000000000000008152506014019050604051809103902060405180807f434f52504f524154494f4e5f43454f5f524f4c45000000000000000000000000815250601401905060405180910390206040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050600060405180830381600087803b15801561109e57600080fd5b505af11580156110b2573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632d95847160405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156111b757600080fd5b505af11580156111cb573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632d95847160405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020306040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156112d057600080fd5b505af11580156112e4573d6000803e3d6000fd5b505050506114dc565b61132c60405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b151561133757600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e4e009185856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050600060405180830381600087803b1580156113df57600080fd5b505af11580156113f3573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632d95847185846040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156114c357600080fd5b505af11580156114d7573d6000803e3d6000fd5b505050505b50505050565b60405180807f554e495445445f50524f4a4543545f4d414e414745525f524f4c450000000000815250601b019050604051809103902081565b61155a60405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b151561156557600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f2fde38b826040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561162157600080fd5b505af1158015611635573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632d95847160405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b15801561173a57600080fd5b505af115801561174e573d6000803e3d6000fd5b505050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166336568abe60405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020306040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b15801561185357600080fd5b505af1158015611867573d6000803e3d6000fd5b5050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663beabacc83385856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b15801561196857600080fd5b505af115801561197c573d6000803e3d6000fd5b505050506040513d602081101561199257600080fd5b8101908080519060200190929190505050508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b60003373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611ada576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f636865636b2055434f424e6f6465436f6e74726f6c6c6572416464726573730081525060200191505060405180910390fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e61683c84846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b158015611b9e57600080fd5b505af1158015611bb2573d6000803e3d6000fd5b505050506040513d6020811015611bc857600080fd5b8101908080519060200190929190505050507fcd34a93c3e2f87568ca969e3560fc377505bb2cf43dd0d73ddbf5e76cf11181e85858585604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200194505050505060405180910390a160019050949350505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ca15c873836040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611d5357600080fd5b505af1158015611d67573d6000803e3d6000fd5b505050506040513d6020811015611d7d57600080fd5b81019080805190602001909291905050509050919050565b611dd460405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b1515611ddf57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d547741f83836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015611eab57600080fd5b505af1158015611ebf573d6000803e3d6000fd5b505050505050565b60405180807f434f52504f524154494f4e5f43464f5f524f4c450000000000000000000000008152506014019050604051809103902081565b60003073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffff","ffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16638da5cb5b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611f9e57600080fd5b505af1158015611fb2573d6000803e3d6000fd5b505050506040513d6020811015611fc857600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614905090565b60008060008060008061203d60405180807f434f52504f524154494f4e5f43464f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b151561204857600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ed3e17a4886040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180826000191660001916815260200191505060a060405180830381600087803b1580156120e057600080fd5b505af11580156120f4573d6000803e3d6000fd5b505050506040513d60a081101561210a57600080fd5b810190808051906020019092919080519060200190929190805190602001909291908051906020019092919080519060200190929190505050509450945094509450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660405180807f7472616e7366657228616464726573732c616464726573732c6164647265737381526020017f2c75696e74323536290000000000000000000000000000000000000000000000815250602901905060405180910390207c01000000000000000000000000000000000000000000000000000000009004868686866040518563ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019450505050506000604051808303816000875af1925050509050801515612385576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260218152602001807f63616c6c20746f2055434f424e6f6465436f6e74726f6c6c6572206661696c6581526020017f640000000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167ffdfbe4af3307293d13b51b41ab7375f15a21fe3a579631fe1a39103c8ff39084898787876040518085600019166000191681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200194505050505060405180910390a3600195505050505050919050565b60003373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561255d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f706c6561736520636865636b2055434f424e6f6465436f6e74726f6c6c65724181526020017f646472657373000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b61259c60405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020846107fb565b15156125a757600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639278ea4284846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561266b57600080fd5b505af115801561267f573d6000803e3d6000fd5b505050506040513d602081101561269557600080fd5b8101908080519060200190929190505050507fe2178daad80300e8e61ab676f06723a5952d56d1a823308ce5f08b9012a1cdc0600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168484604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a16001905092915050565b6127b160405180807f434f52504f524154494f4e5f43454f5f524f4c4500000000000000000000000081525060140190506040518091039020336107fb565b15156127bc57600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f231a3b32944b1204fda4e59766427a14b806af1b951dc09f25cc498d0b93079681604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a1505600a165627a7a723058203da05d959e379195563ed01021879a7d232d87ec476f2ef68d4806b44ff991870029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"destination\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transferOut\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"checkRole\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_BRANCH_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_CEO_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"PROJECT_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"userAddress\",\"type\":\"address\"}],\"name\":\"grantRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"PROJECT_STAFF_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_MEMBERS_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_STAFF_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"roleId\",\"type\":\"bytes32\"},{\"name\":\"adminRoleId\",\"type\":\"bytes32\"},{\"name\":\"userAddress\",\"type\":\"address\"}],\"name\":\"setRoleAdmin\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"UNITED_PROJECT_MANAGER_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"CorporationControllerAddress\",\"type\":\"address\"}],\"name\":\"changeOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"original\",\"type\":\"address\"},{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transferReceive\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"}],\"name\":\"getRoleMemberCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"role\",\"type\":\"bytes32\"},{\"name\":\"userAddress\",\"type\":\"address\"}],\"name\":\"revokeRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"CORPORATION_CFO_ROLE\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"checkCorporationStorageSafety\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"key\",\"type\":\"bytes32\"}],\"name\":\"transferOutCallback\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"ceo\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"initCorporationController\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"u\",\"type\":\"address\"}],\"name\":\"changeUCOBNodeControllerAddress\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"corporationStorageAddress\",\"type\":\"address\"},{\"name\":\"ucobNodeControllerAddress\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"fallback\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_address\",\"type\":\"address\"}],\"name\":\"ChangeUCOBNodeControllerAddress\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_address\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"beneficiary\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"InitCorporationController\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"key\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"destination\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"TransferOut\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"original\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"TransferReceive\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"key\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"original\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"destination\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"TransferOutCallback\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_TRANSFEROUT = "transferOut";

    public static final String FUNC_CHECKROLE = "checkRole";

    public static final String FUNC_CORPORATION_BRANCH_MANAGER_ROLE = "CORPORATION_BRANCH_MANAGER_ROLE";

    public static final String FUNC_CORPORATION_CEO_ROLE = "CORPORATION_CEO_ROLE";

    public static final String FUNC_PROJECT_MANAGER_ROLE = "PROJECT_MANAGER_ROLE";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_PROJECT_STAFF_ROLE = "PROJECT_STAFF_ROLE";

    public static final String FUNC_CORPORATION_MEMBERS_ROLE = "CORPORATION_MEMBERS_ROLE";

    public static final String FUNC_CORPORATION_STAFF_ROLE = "CORPORATION_STAFF_ROLE";

    public static final String FUNC_SETROLEADMIN = "setRoleAdmin";

    public static final String FUNC_UNITED_PROJECT_MANAGER_ROLE = "UNITED_PROJECT_MANAGER_ROLE";

    public static final String FUNC_CHANGEOWNER = "changeOwner";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERRECEIVE = "transferReceive";

    public static final String FUNC_GETROLEMEMBERCOUNT = "getRoleMemberCount";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_CORPORATION_CFO_ROLE = "CORPORATION_CFO_ROLE";

    public static final String FUNC_CHECKCORPORATIONSTORAGESAFETY = "checkCorporationStorageSafety";

    public static final String FUNC_TRANSFEROUTCALLBACK = "transferOutCallback";

    public static final String FUNC_INITCORPORATIONCONTROLLER = "initCorporationController";

    public static final String FUNC_CHANGEUCOBNODECONTROLLERADDRESS = "changeUCOBNodeControllerAddress";

    public static final Event CHANGEUCOBNODECONTROLLERADDRESS_EVENT = new Event("ChangeUCOBNodeControllerAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event INITCORPORATIONCONTROLLER_EVENT = new Event("InitCorporationController", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFEROUT_EVENT = new Event("TransferOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFERRECEIVE_EVENT = new Event("TransferReceive", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFEROUTCALLBACK_EVENT = new Event("TransferOutCallback", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected CorporationControllerDemo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CorporationControllerDemo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CorporationControllerDemo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CorporationControllerDemo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> transferOut(String destination, String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFEROUT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(destination), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferOut(String destination, String to, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFEROUT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(destination), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferOutSeq(String destination, String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFEROUT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(destination), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getTransferOutInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFEROUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getTransferOutOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFEROUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> checkRole(byte[] role, String addr) {
        final Function function = new Function(FUNC_CHECKROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteCall<byte[]> PROJECT_MANAGER_ROLE() {
        final Function function = new Function(FUNC_PROJECT_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> grantRole(byte[] role, String userAddress) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void grantRole(byte[] role, String userAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String grantRoleSeq(byte[] role, String userAddress) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
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

    public RemoteCall<TransactionReceipt> setRoleAdmin(byte[] roleId, byte[] adminRoleId, String userAddress) {
        final Function function = new Function(
                FUNC_SETROLEADMIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(roleId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(adminRoleId), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setRoleAdmin(byte[] roleId, byte[] adminRoleId, String userAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETROLEADMIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(roleId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(adminRoleId), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setRoleAdminSeq(byte[] roleId, byte[] adminRoleId, String userAddress) {
        final Function function = new Function(
                FUNC_SETROLEADMIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(roleId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(adminRoleId), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<byte[], byte[], String> getSetRoleAdminInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETROLEADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<byte[], byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public RemoteCall<byte[]> UNITED_PROJECT_MANAGER_ROLE() {
        final Function function = new Function(FUNC_UNITED_PROJECT_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> changeOwner(String CorporationControllerAddress) {
        final Function function = new Function(
                FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(CorporationControllerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void changeOwner(String CorporationControllerAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(CorporationControllerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String changeOwnerSeq(String CorporationControllerAddress) {
        final Function function = new Function(
                FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(CorporationControllerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getChangeOwnerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> transfer(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String to, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferSeq(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> transferReceive(String original, String _from, String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(original), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferReceive(String original, String _from, String to, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(original), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferReceiveSeq(String original, String _from, String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(original), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, String, BigInteger> getTransferReceiveInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERRECEIVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public Tuple1<Boolean> getTransferReceiveOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFERRECEIVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<BigInteger> getRoleMemberCount(byte[] role) {
        final Function function = new Function(FUNC_GETROLEMEMBERCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> revokeRole(byte[] role, String userAddress) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void revokeRole(byte[] role, String userAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String revokeRoleSeq(byte[] role, String userAddress) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(userAddress)), 
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

    public RemoteCall<Boolean> checkCorporationStorageSafety() {
        final Function function = new Function(FUNC_CHECKCORPORATIONSTORAGESAFETY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> transferOutCallback(byte[] key) {
        final Function function = new Function(
                FUNC_TRANSFEROUTCALLBACK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferOutCallback(byte[] key, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFEROUTCALLBACK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferOutCallbackSeq(byte[] key) {
        final Function function = new Function(
                FUNC_TRANSFEROUTCALLBACK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(key)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<byte[]> getTransferOutCallbackInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFEROUTCALLBACK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getTransferOutCallbackOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFEROUTCALLBACK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> initCorporationController(String ceo, BigInteger value) {
        final Function function = new Function(
                FUNC_INITCORPORATIONCONTROLLER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(ceo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void initCorporationController(String ceo, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INITCORPORATIONCONTROLLER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(ceo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String initCorporationControllerSeq(String ceo, BigInteger value) {
        final Function function = new Function(
                FUNC_INITCORPORATIONCONTROLLER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(ceo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getInitCorporationControllerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_INITCORPORATIONCONTROLLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getInitCorporationControllerOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_INITCORPORATIONCONTROLLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> changeUCOBNodeControllerAddress(String u) {
        final Function function = new Function(
                FUNC_CHANGEUCOBNODECONTROLLERADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(u)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void changeUCOBNodeControllerAddress(String u, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEUCOBNODECONTROLLERADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(u)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String changeUCOBNodeControllerAddressSeq(String u) {
        final Function function = new Function(
                FUNC_CHANGEUCOBNODECONTROLLERADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(u)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getChangeUCOBNodeControllerAddressInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGEUCOBNODECONTROLLERADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public List<ChangeUCOBNodeControllerAddressEventResponse> getChangeUCOBNodeControllerAddressEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGEUCOBNODECONTROLLERADDRESS_EVENT, transactionReceipt);
        ArrayList<ChangeUCOBNodeControllerAddressEventResponse> responses = new ArrayList<ChangeUCOBNodeControllerAddressEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeUCOBNodeControllerAddressEventResponse typedResponse = new ChangeUCOBNodeControllerAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerChangeUCOBNodeControllerAddressEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CHANGEUCOBNODECONTROLLERADDRESS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerChangeUCOBNodeControllerAddressEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CHANGEUCOBNODECONTROLLERADDRESS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<InitCorporationControllerEventResponse> getInitCorporationControllerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INITCORPORATIONCONTROLLER_EVENT, transactionReceipt);
        ArrayList<InitCorporationControllerEventResponse> responses = new ArrayList<InitCorporationControllerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InitCorporationControllerEventResponse typedResponse = new InitCorporationControllerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerInitCorporationControllerEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(INITCORPORATIONCONTROLLER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerInitCorporationControllerEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(INITCORPORATIONCONTROLLER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferOutEventResponse> getTransferOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEROUT_EVENT, transactionReceipt);
        ArrayList<TransferOutEventResponse> responses = new ArrayList<TransferOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferOutEventResponse typedResponse = new TransferOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.key = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferOutEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEROUT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferOutEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEROUT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferReceiveEventResponse> getTransferReceiveEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERRECEIVE_EVENT, transactionReceipt);
        ArrayList<TransferReceiveEventResponse> responses = new ArrayList<TransferReceiveEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferReceiveEventResponse typedResponse = new TransferReceiveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.original = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._from = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferReceiveEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFERRECEIVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferReceiveEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFERRECEIVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferOutCallbackEventResponse> getTransferOutCallbackEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEROUTCALLBACK_EVENT, transactionReceipt);
        ArrayList<TransferOutCallbackEventResponse> responses = new ArrayList<TransferOutCallbackEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferOutCallbackEventResponse typedResponse = new TransferOutCallbackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.original = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.destination = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.key = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._from = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferOutCallbackEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEROUTCALLBACK_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferOutCallbackEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEROUTCALLBACK_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static CorporationControllerDemo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CorporationControllerDemo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CorporationControllerDemo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CorporationControllerDemo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CorporationControllerDemo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CorporationControllerDemo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CorporationControllerDemo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CorporationControllerDemo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CorporationControllerDemo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String corporationStorageAddress, String ucobNodeControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationStorageAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(ucobNodeControllerAddress)));
        return deployRemoteCall(CorporationControllerDemo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<CorporationControllerDemo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String corporationStorageAddress, String ucobNodeControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationStorageAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(ucobNodeControllerAddress)));
        return deployRemoteCall(CorporationControllerDemo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CorporationControllerDemo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String corporationStorageAddress, String ucobNodeControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationStorageAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(ucobNodeControllerAddress)));
        return deployRemoteCall(CorporationControllerDemo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CorporationControllerDemo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String corporationStorageAddress, String ucobNodeControllerAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(corporationStorageAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(ucobNodeControllerAddress)));
        return deployRemoteCall(CorporationControllerDemo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ChangeUCOBNodeControllerAddressEventResponse {
        public Log log;

        public String _address;
    }

    public static class InitCorporationControllerEventResponse {
        public Log log;

        public String _address;

        public String beneficiary;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public Log log;

        public String _from;

        public String to;

        public BigInteger value;
    }

    public static class TransferOutEventResponse {
        public Log log;

        public String destination;

        public String _from;

        public String to;

        public byte[] key;

        public BigInteger value;
    }

    public static class TransferReceiveEventResponse {
        public Log log;

        public String original;

        public String _from;

        public String to;

        public BigInteger value;
    }

    public static class TransferOutCallbackEventResponse {
        public Log log;

        public String original;

        public String destination;

        public byte[] key;

        public String _from;

        public String to;

        public BigInteger value;
    }
}
