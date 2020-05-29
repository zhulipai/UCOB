pragma solidity ^0.4.25;

import "./CorporationControllerInterface.sol";
import "./CorporationStorageDemo.sol";
import "./RoleConstant.sol";

contract CorporationControllerDemo is CorporationControllerInterface,RoleConstant {
    
    /**
     * the storage contract of CorporationControlle
     */
    CorporationStorageDemo corporationStorage;
    
    /**
     * 
     * the address of UCOBNodeController.sol
     */
    address UCOBNodeControllerAddress;

    event ChangeUCOBNodeControllerAddress(address _address);
    event InitCorporationController(address _address,address beneficiary,uint256 value);
    event Transfer(address indexed _from, address indexed to, uint256 value);
	event TransferOut(bytes32 key,address indexed destination,address indexed _from, address indexed to, uint256 value);
    event TransferReceive(address original,address _from, address to, uint256 value);
    event TransferOutCallback(bytes32 key,address indexed original,address indexed destination,address _from,address to, uint256 value);
    
    /**
     * deploy this contract with CorporationStorageDemoAddress and UCOBNodeControllerAddress
     */
    constructor(address corporationStorageAddress,address ucobNodeControllerAddress) public {
        corporationStorage = CorporationStorageDemo(corporationStorageAddress);
        UCOBNodeControllerAddress = ucobNodeControllerAddress;
    }

    /**
     * can only be invoked by UCOBNodeController, so ceo can receive reserve in UCOB
     */
    function initCorporationController(address ceo,uint256 value) public returns (bool) {
    	require(UCOBNodeControllerAddress == msg.sender, "please check UCOBNodeControllerAddress");
    	require(checkRole(CORPORATION_CEO_ROLE,ceo));
        corporationStorage.initCorporationData(ceo,value);
        emit InitCorporationController(UCOBNodeControllerAddress,ceo,value);
        return true;
    }

    //Refer to "./CorporationControllerInterface.sol"
    function transfer(address to, uint256 value) public returns (bool) {
        corporationStorage.transfer(msg.sender,to,value);
        emit Transfer(msg.sender, to, value);
        return true;
    }

    //Refer to "./CorporationControllerInterface.sol"
    function transferOut(address destination,address to, uint256 value) public returns (bool) {
        bytes32 key = corporationStorage.transferOut(destination,msg.sender,to,value);
        emit TransferOut(key,destination,msg.sender,to,value);
        return true;
    }

    //Refer to "./CorporationControllerInterface.sol"
    function transferOutCallback(bytes32 key) public returns (bool) {
        require(checkRole(CORPORATION_CFO_ROLE,msg.sender));
        (address destination,address _from,address to, uint256 value,) = corporationStorage.transferOutCallback(key);
        bool success = UCOBNodeControllerAddress.call(bytes4(keccak256("transfer(address,address,address,uint256)")),destination,_from,to,value);
        require(success, "call to UCOBNodeController failed");
        emit TransferOutCallback(key,msg.sender,destination,_from,to,value);
        return true;
    }

    //Refer to "./CorporationControllerInterface.sol"
    function transferReceive(address original,address _from,address to, uint256 value) public returns (bool) {
        require(UCOBNodeControllerAddress == msg.sender, "check UCOBNodeControllerAddress");
        corporationStorage.transferReceive(to,value);
        emit TransferReceive(original,_from, to, value);
        return true;
    }

    /**
     * can only change corporationStorage Owner with role 'CORPORATION_CEO_ROLE'
     */
    function changeOwner(address CorporationControllerAddress) public {
        require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
        corporationStorage.transferOwnership(CorporationControllerAddress);
        corporationStorage.setUpRole(CORPORATION_CEO_ROLE,CorporationControllerAddress);
    	corporationStorage.renounceRole(CORPORATION_CEO_ROLE,address(this));
    }

    /**
     * can only change UCOBNodeControllerAddress with role 'CORPORATION_CEO_ROLE'
     */
    function changeUCOBNodeControllerAddress(address u) public {
        require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
        UCOBNodeControllerAddress = u;
        emit ChangeUCOBNodeControllerAddress(u);
    }

    /**
     * check if the Corporation Storage belongs to Corporation Controller
     */
    function checkCorporationStorageSafety() public view returns (bool) {
    	return corporationStorage.owner()==address(this);
    }

    /**
     * initial the first RoleAdmin or set a new RoleAdmin
     */
    function setRoleAdmin(bytes32 roleId,bytes32 adminRoleId,address userAddress) public {
        bool ini = corporationStorage.initialized();
    	if(ini==false&&checkCorporationStorageSafety()){
    		corporationStorage.initialize();
    		corporationStorage.setRoleAdmin(CORPORATION_CEO_ROLE,CORPORATION_CEO_ROLE);
            corporationStorage.setUpRole(CORPORATION_CEO_ROLE,msg.sender);
            corporationStorage.setUpRole(CORPORATION_CEO_ROLE,address(this));
    	}else{
    		require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
    		corporationStorage.setRoleAdmin(roleId,adminRoleId);
            corporationStorage.setUpRole(roleId,userAddress);
    	}
    }

    /**
     * get the member count of special role
     */
    function getRoleMemberCount(bytes32 role) public view returns (uint256) {
    	return corporationStorage.getRoleMemberCount(role);
    }

    /**
     * check is the address has the role
     */
    function checkRole(bytes32 role,address addr) public view returns (bool) {
    	require(corporationStorage.hasRole(role, addr), "you do not have right role");
        return true;
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function grantRole(bytes32 role,address userAddress) public {
    	require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
        corporationStorage.grantRole(role,userAddress);
    }

    //Refer to "./lib/access/AccessControl.sol"
    function revokeRole(bytes32 role,address userAddress) public {
    	require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
        corporationStorage.revokeRole(role,userAddress);
    }

    /**
     * only sender with 'CORPORATION_CEO_ROLE' can invoke UCOBNodeController.sol via this method.
     * such as 'startProposal()'\'voteProposal()'etc.
     */
    function() external {
    	require(checkRole(CORPORATION_CEO_ROLE,msg.sender));
    	address u = UCOBNodeControllerAddress;
    	assembly {
            calldatacopy(0, 0, calldatasize())
            let result := call(gas,u,0, 0, calldatasize(), 0, 0)
            returndatacopy(0, 0, returndatasize())
            switch result
            case 0 {revert(0, returndatasize())}
            default {return (0, returndatasize())}
        }
    }
}