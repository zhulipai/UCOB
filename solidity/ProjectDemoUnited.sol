pragma solidity ^0.4.25;

import "./ProjectInterface.sol";

contract ProjectDemoUnited is ProjectInterface{

    /**
     * the contract address of current UCOBNodeController,
     * the address will be useful if this project is an united project
     */
	address UCOBNodeControllerAddress;

    /**
     * means this united project have been approved by UCOBNodeController.
     */
    bool approved;

    /**
     * (a member corporation assign a manager address to the united project) => (a member corporation address)
     * (inner address) => (outer address)
     */
    mapping(address=>address) ProjectManagerAddress; 
    
    /**
     * users who will attend project 
     */
    mapping(address=>bool) ProjectUserAddress;

    /**
     * business data, in this demo it's 'key => value'
     */
    mapping(bytes32=>bytes32) BusinessDataDemo;

    event AddProjectUserEvent(address manager,address user);
    event RemoveProjectUserEvent(address manager,address user);
    event InitProject(address indexed _address);
    event AddManager(address indexed _address,address indexed corporationContractAddress,address managerAddress);

    modifier onlyProjectManager() {
        require(ProjectManagerAddress[msg.sender]!=address(0),"Manager has no right");
        _;
    }

    modifier onlyApproved() {
    	require(approved,"Project is not approved");
        _;
    }

    modifier onlyProjectUser() {
        require(ProjectUserAddress[msg.sender],"User has no right");
        _;
    }

    /**
     * deploy project contract with 'UCOBNodeControllerAddress'
     */
    constructor(address ucobNodeControllerAddress) public {
        UCOBNodeControllerAddress = ucobNodeControllerAddress;
    }

    /**
     * initial the project, must be approved by UCOBNodeController.
     */
    function initProject() external returns (bool) {
        require(UCOBNodeControllerAddress==msg.sender);
        require(!approved);
        approved = true;
        emit InitProject(msg.sender);
        return true;
    }

    /**
     * add united project manager to the project, can only invoke by UCOBNodeController
     */
    function addManager(address corporationContractAddress,address managerAddress) external returns (bool) {
        require(UCOBNodeControllerAddress==msg.sender);
        ProjectManagerAddress[managerAddress]=corporationContractAddress;
        emit AddManager(msg.sender,corporationContractAddress,managerAddress);
        return true;
    }

    /**
     * return current project status
     */
    function checkProjectStatus() public view returns (bool,address) {
        return (approved,UCOBNodeControllerAddress);
    }

    /**
     * check if the project has been approved
     */
    function checkApproved() external view returns (bool) {
    	return approved;
    }

    /**
     * check if the address is manager
     */
    function checkManager(address addr) external view returns (bool) {
    	return ProjectManagerAddress[addr]!=address(0);
    }

    /**
     * only manager can add user to this project. 
     */
    function addProjectUser(address addr) public onlyProjectManager() {
    	ProjectUserAddress[addr] = true;
    	emit AddProjectUserEvent(msg.sender,addr);
    }

    /**
     * only manager can remove user from this project
     */
    function removeProjectUser(address addr) public onlyProjectManager() {
    	ProjectUserAddress[addr] = false;
    	emit RemoveProjectUserEvent(msg.sender,addr);
    }

    /**
     * a demo business action, store data
     */
    function set(bytes32 key,bytes32 value) external onlyApproved() onlyProjectUser() {
    	BusinessDataDemo[key] = value;
    }

    /**
     * a demo business action, get data
     */
    function get(bytes32 key) external view returns (bytes32) {
    	return BusinessDataDemo[key] ;
    }
}