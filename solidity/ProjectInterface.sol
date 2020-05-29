pragma solidity ^0.4.25;

interface ProjectInterface {
    /**
     * every project instance should implement this method, so it can be initialized by UCOBNodeController.sol
     */
    function initProject() external returns (bool);
    
    /**
     * every project instance should implement this method, so UCOBNodeController.sol can add an united project manager
     */
    function addManager(address corporationAddress,address ManagerAddress) external returns (bool);
}