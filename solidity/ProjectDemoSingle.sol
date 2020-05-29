pragma solidity ^0.4.25;

import "./ProjectInterface.sol";
import "./RoleConstant.sol";
import "./lib/access/AccessControl.sol";

contract ProjectDemoSingle is ProjectInterface,AccessControl,RoleConstant {

    /**
	 * the contract address that start this project,
	 * the address will be useful if this project is a single project that belongs to one corporation
	 */
    address CorporationControllerAddress;
    
    /**
     * business data, in this demo it's 'key => value'
     */
    mapping(bytes32=>bytes32) BusinessDataDemo;
    
    /**
     * deploy this project-contract with CorporationControllerAddress that owned it.
     * set 'PROJECT_MANAGER_ROLE' as the adminRole both 'PROJECT_STAFF_ROLE' and 'PROJECT_MANAGER_ROLE'
     * set 'msg.sender' as the first manager.
     */
    constructor(address corporationControllerAddress) public {
        CorporationControllerAddress = corporationControllerAddress;
        _setRoleAdmin(PROJECT_MANAGER_ROLE, PROJECT_MANAGER_ROLE);
        _setRoleAdmin(PROJECT_STAFF_ROLE, PROJECT_MANAGER_ROLE);
        _setupRole(PROJECT_MANAGER_ROLE, msg.sender);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function grantRole(bytes32 role, address account) public {
        super.grantRole(role,account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function revokeRole(bytes32 role, address account) public {
        super.revokeRole(role,account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function renounceRole(bytes32 role, address account) public {
        super.renounceRole(role,account);
    }

    // default method
    function initProject() external returns (bool) {
        return true;
    }

    /**
     * add a project manager
     */
    function addManager(address corporationAddress,address managerAddress) external returns (bool) {
        require(corporationAddress==CorporationControllerAddress,"manager can only come from the corporation that owned this project-contract");
        grantRole(PROJECT_MANAGER_ROLE,managerAddress);
        return true;
    }

    /**
     * a demo business action, store data
     */
    function set(bytes32 key,bytes32 value) external {
        require(hasRole(PROJECT_STAFF_ROLE,msg.sender),"user has no right");
        BusinessDataDemo[key] = value;
    }

    /**
     * a demo business action, get data
     */
    function get(bytes32 key) external view returns (bytes32) {
        return BusinessDataDemo[key] ;
    }
}