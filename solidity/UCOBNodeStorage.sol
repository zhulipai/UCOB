pragma solidity ^0.4.25;

import "./lib/access/AccessControl.sol";
import "./lib/access/Ownable.sol";
import "./lib/math/SafeMath.sol";

contract UCOBNodeStorage is AccessControl,Ownable {

    //the status of this contract, will be 'true' after initialization
    bool public initialized;

    //accumulative total supply
    uint256 public totalSupply;

    //accumulative total burn
    uint256 public totalBurn;

    /**
     * balance of each corporation
     * 
     * (corporation contract address) => (corporation balance);
     */
    mapping(address=>uint256) private balances;
    
    /**
     * united project has been approved, which means all corporation will attend.
     * united project => bool
     */
    mapping(address=>bool) private unitedProjectMap;

    //initialized
    function initialize() public onlyOwner() {
        initialized = true;
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function setRoleAdmin(bytes32 roleId, bytes32 adminRoleId) public onlyOwner() {
        _setRoleAdmin(roleId, adminRoleId);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function setUpRole(bytes32 roleId,address addr) public onlyOwner() {
        _setupRole(roleId, addr); 
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function grantRole(bytes32 role, address account) public onlyOwner() {
        super.grantRole(role,account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function revokeRole(uint role, address account) public onlyOwner() {
        super.revokeRole(bytes32(role),account);
    }
    
    //Refer to "./lib/access/AccessControl.sol"
    function renounceRole(bytes32 role, address account) public onlyOwner() {
        super.renounceRole(role,account);
    }

    /**
     * add a new united project.
     * when approved after voting, the project can be added.
     */
    function addUnitedProject(address projectAddress) public onlyOwner() {
        unitedProjectMap[projectAddress]=true;
    }
    
    /**
     * remove a new united project.
     */
    function removeUnitedProject(address projectAddress) public onlyOwner() {
        unitedProjectMap[projectAddress]=false;
    }

    /**
     * check if it is an united project
     */
    function checkUnitedProject(address projectAddress) public view returns(bool) {
        return unitedProjectMap[projectAddress];
    }
    
    /**
     * get the balance of one address
     * 
     */
    function balanceOf(address addr) public view returns (uint256 balance) {
        return balances[addr];
    }
    
    /**
     * add value to one address
     * 
     */
    function add(address addr,uint256 value) public onlyOwner() {
    	balances[addr] = SafeMath.add(balances[addr],value);
    }
    
    /**
     * sub value to one address
     * in the library, will check if 'b <= a'
     */
    function sub(address addr,uint256 value) public onlyOwner() {
    	balances[addr] = SafeMath.sub(balances[addr],value);
    }
    
    /**
     * add total supply
     * 
     */
    function supply(uint256 value) public onlyOwner() {
    	totalSupply = SafeMath.add(totalSupply,value);
    }
    
    /**
     * add total burn
     * 
     */
    function burn(uint256 value) public onlyOwner() {
    	totalBurn = SafeMath.add(totalBurn,value);
    }
}