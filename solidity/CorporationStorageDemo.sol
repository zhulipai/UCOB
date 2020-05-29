pragma solidity ^0.4.25;

import "./CorporationStorageInterface.sol";
import "./lib/access/AccessControl.sol";
import "./lib/access/Ownable.sol";
import "./lib/math/SafeMath.sol";

contract CorporationStorageDemo is CorporationStorageInterface,AccessControl,Ownable {

    //accumulative total supply
	uint256 public totalSupply;

    //accumulative total amount that transfer to the other corporation
	uint256 public totalTransferOut;
	
	/**
     * whether this contract is initialized
     */
    bool public initialized;

	struct TransferOutReceipt {
		address destination;//the other corporation contract address
		address _from;//from which user address
		address to;//to which user address
		uint256 value;//the amount
		bool status;//the transfer's status
	}

    /**
     * transfer among corporations

     * receipt id => TransferOutReceipt
     */
	mapping(bytes32=>TransferOutReceipt) transferOutMap;

    /**
     * balance in corporation
     */
	mapping(address=>uint256) balances;

    //initialized
    function initialize() public onlyOwner() {
        initialized = true;
    }

    /**
     * initial the ceo balance with reserve
     */
    function initCorporationData(address ceo,uint256 value) public onlyOwner() returns (bool) {
        balances[ceo] = SafeMath.add(balances[ceo],value);
        totalSupply = SafeMath.add(totalSupply,value);
        return true;
    }

    //Refer to "./lib/access/AccessControl.sol"
    function grantRole(bytes32 role, address account) public onlyOwner() {
        super.grantRole(role,account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function revokeRole(bytes32 role, address account) public onlyOwner() {
        super.revokeRole(role,account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function renounceRole(bytes32 role, address account) public onlyOwner() {
        super.renounceRole(role,account);
    }

	//Refer to "./CorporationStorageInterface.sol"
    function transfer(address _from,address to, uint256 value) public onlyOwner() returns (bool)  {
        require(balances[_from] >= value);
        balances[_from] = SafeMath.sub(balances[_from],value);
        balances[to] = SafeMath.add(balances[to],value);
        return true;
    }

    //Refer to "./CorporationStorageInterface.sol"
    function transferOut(address destination,address _from,address to, uint256 value) public onlyOwner() returns (bytes32) {
        balances[_from] = SafeMath.sub(balances[_from],value);
        bytes32 key =random();
        TransferOutReceipt storage receipt = transferOutMap[key];
        receipt.destination = destination;
        receipt._from = _from;
        receipt.to = to;
        receipt.value = value;
        receipt.status = false;
        return key;
    }
    
    function random() private view returns (bytes32){
        return keccak256(abi.encode(block.difficulty, block.coinbase,now));
    }

    //Refer to "./CorporationStorageInterface.sol"
    function transferOutCallback(bytes32 key) public onlyOwner() returns (address,address,address,uint256,bool) {
    	TransferOutReceipt storage receipt = transferOutMap[key];
    	require(!receipt.status,"receipt status is true");
    	receipt.status = true;
    	totalTransferOut = SafeMath.add(totalTransferOut,receipt.value);
    	return (receipt.destination,receipt._from,receipt.to,receipt.value,receipt.status);
    }

    //Refer to "./CorporationStorageInterface.sol"
    function transferReceive(address to, uint256 value) public onlyOwner() returns (bool) {
        balances[to] = SafeMath.add(balances[to],value);
        totalSupply = SafeMath.add(totalSupply,value);
        return true;
    }

    //Refer to "./lib/access/AccessControl.sol"
    function setRoleAdmin(bytes32 roleId, bytes32 adminRoleId) public onlyOwner() {
        _setRoleAdmin(roleId, adminRoleId);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function setUpRole(bytes32 roleId,address addr) public onlyOwner() {
        _setupRole(roleId, addr); 
    }

    /**
     * check the balance
     */
    function balanceOf(address addr) public view returns (uint256 balance) {
        return balances[addr];
    }
}