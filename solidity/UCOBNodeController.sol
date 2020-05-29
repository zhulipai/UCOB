pragma solidity ^0.4.25;

import "./UCOBNodeStorage.sol";
import "./ProposalController.sol";
import "./RoleConstant.sol";

contract UCOBNodeController is ProposalController, RoleConstant {
    
    /**
     * the storage contract of UCOBNodeController
     */
    UCOBNodeStorage private ucobNodeStorage;
    
    /**
     * the signature of method
     */
    bytes4 public constant INITCORPORATIONCONTROLLER = bytes4(keccak256("initCorporationController(address,uint256)"));
    bytes4 public constant TRANSFERRECEIVE = bytes4(keccak256("transferReceive(address,address,address,uint256)"));
    bytes4 public constant ADDMANAGER = bytes4(keccak256("addManager(address,address)"));
    bytes4 public constant INITPROJECT = bytes4(keccak256("initProject()"));

    event ChangeUCOBNodeStorageOwner(bytes32 proposalId,address old,address _new);
    event InitRoleAdmin(address initializer,address ucobNodeController,address initialCorporation,bytes32 roleId,bytes32 adminRoleId);
    event SetRoleAdmin(address msg_sender,address firstAdminAddress,bytes32 proposalId,bytes32 roleId,bytes32 adminRoleId);
    event AddUnitedProject(bytes32 proposalId,address projectContractAddress);
    event AddUnitedProjectManager(address indexed projectContractAddress,address managerAddress);
    event ActiveCorporation(bytes32 proposalId,address indexed corporationContractAddress,address beneficiary,uint256 value);
    event Issue(bytes32 proposalId,address indexed corporationContractAddress,address beneficiary,uint256 value);
    event Transfer(address indexed original, address _from, address indexed destination, address indexed to, uint256 value);
    
    /**
     * initial UCOBNodeStorage contract
     */
    constructor(address UCOBNodeStorageAddress) public {
        ucobNodeStorage = UCOBNodeStorage(UCOBNodeStorageAddress);
    }

    /**
     *
     * at the very first,we should deploy a corporationContract contract, and set its address as the first member corporation in UCOB.
     * this method can only be invoked once, and then never work again.
     */
    function initRoleAdmin(address corporationContractAddress) public {
        bool ini = ucobNodeStorage.initialized();
        require(ini==false&&checkUCOBNodeStorageSafety(),"initialized already");
        ucobNodeStorage.initialize();
        ucobNodeStorage.setRoleAdmin(CORPORATION_MEMBERS_ROLE,CORPORATION_MEMBERS_ROLE);
        ucobNodeStorage.setUpRole(CORPORATION_MEMBERS_ROLE,corporationContractAddress);
        ucobNodeStorage.setUpRole(CORPORATION_MEMBERS_ROLE,address(this));
        emit InitRoleAdmin(msg.sender,address(this),corporationContractAddress,CORPORATION_MEMBERS_ROLE,CORPORATION_MEMBERS_ROLE);
    }

    /**
     * we can use this method add another member corporation to UCOB. of course, we should firstly pass the corresponding proposal.
     * when we add 'CORPORATION_MEMBERS_ROLE', then {roleId} and {adminRoleId} should be both set as 'CORPORATION_MEMBERS_ROLE'
     *
     * and we can also set other Role and RoleAdmin, such as when {roleId} is 'UNITED_PROJECT_MANAGER_ROLE' and {adminRoleId} is 'CORPORATION_MEMBERS_ROLE'.
     * or more other role as you wish.
     */
    function setRoleAdmin(bytes32 roleId,bytes32 adminRoleId,bytes32 proposalId) public {
        require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
        // 	require(roleId!=CORPORATION_MEMBERS_ROLE);
        (,address firstAdminAddress,,,,,bool expired) = getProposal(proposalId);
        require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
        ucobNodeStorage.setRoleAdmin(roleId,adminRoleId);
        ucobNodeStorage.setUpRole(adminRoleId,firstAdminAddress);
        ucobNodeStorage.setUpRole(adminRoleId,address(this));
        emit SetRoleAdmin(msg.sender,firstAdminAddress,proposalId,roleId,adminRoleId);
    }

    /**
     * only members in united corporation can start a proposal
     * 
     * {bytes32 proposalId}, id of a proposal
     * {address proposer}, an user address(outer address), who start this proposer,usually be ceo of a corporation, will be the account receive the balance value
     * {address proposerContract}, a contract address(inner account), can represent a corporation contract or an UCOBNodeController contract
     * {bytes32 message}, 
     * {uint256 value}, required reserves, after approved, this value will be send to the corporation account in this contract and send to proposer in corporation contract.
     */
    function startProposal(bytes32 proposalId,address proposer,address proposerContract,bytes32 message,uint256 value) public returns (bool) {
        require(checkRole(CORPORATION_MEMBERS_ROLE,msg.sender));
        return super.initProposal(proposalId,proposer,proposerContract,message,ProposalTypeEnum.Corporation,value);
    }

    /**
     * only members has 'CORPORATION_MEMBERS_ROLE' role can vote proposal
     */
    function voteProposal(bytes32 proposalId) public returns (bool) {
       require(checkRole(CORPORATION_MEMBERS_ROLE,msg.sender));
       return super.vote(proposalId);
    }

    /**
     * after voting, the ownership of UCOBNodeStorage contract can be transfer to the new UCOBNodeController contract.
     */
    function changeUCOBNodeStorageOwner(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(,address UCOBNodeControllerAddress,,,,,bool expired) = getProposal(proposalId);
    	require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
    	ucobNodeStorage.transferOwnership(UCOBNodeControllerAddress);
    	ucobNodeStorage.setUpRole(CORPORATION_MEMBERS_ROLE,UCOBNodeControllerAddress);
    	ucobNodeStorage.renounceRole(CORPORATION_MEMBERS_ROLE,address(this));
    	emit ChangeUCOBNodeStorageOwner(proposalId,address(this),UCOBNodeControllerAddress);
    }

    /**
     * check if this UCOBNodecontroller contract own the storage contract.
     */
    function checkUCOBNodeStorageSafety() public view returns (bool) {
    	return ucobNodeStorage.owner()==address(this);
    }

    /**
     * 
     * get the total members count by role.
     */
    function getRoleMemberCount(bytes32 role) public view returns (uint256) {
        uint256 i = ucobNodeStorage.getRoleMemberCount(role);
        require(i > 0,"member count is zero");
        //why minus 1? because this contract,like 'UCOBNodeControllerAddress' is also a role member,
        //but when count voter turnout，we don't need count it, all we need is 'corporationcontroller address'
    	i = i-1;
    	return i;
    }

    /**
     * 
     * check if the address has corresponding role.
     */
    function checkRole(bytes32 role,address addr) public view returns(bool) {
    	require(ucobNodeStorage.hasRole(role, addr), "you do not have right");
        return true;
    }

    /**
     * grant a corporationContractAddress as 'CORPORATION_MEMBERS_ROLE'
     * 
     * first, we should start a proposal, so that each corporation in UCOB can vote if each of them agree a new member.
     * second, after pass the proposal, wen can grant the corporation as 'CORPORATION_MEMBERS_ROLE'
     */
    function grantRole(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(,address corporationContractAddress,,,,,bool expired) = getProposal(proposalId);
    	require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
        ucobNodeStorage.grantRole(CORPORATION_MEMBERS_ROLE,corporationContractAddress);
    }

    /**
     * same as grantRole(), but after passing proposal, we will revoke a corporation.
     * 
     * NOTE!!!
     * maybe the corporation has some token in "balances", but we can freeze it by this method.
     */
    function revokeRole(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(,address corporationContractAddress,,,,,bool expired) = getProposal(proposalId);
    	require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
        ucobNodeStorage.revokeRole(CORPORATION_MEMBERS_ROLE,corporationContractAddress);
    }


	/**
	 * add a new united project.
	 * because the united project means every members in UCOB will attend,
	 * so before it begins, they can vote if they wish to start the united project.
	 * 
	 */
	
    function addUnitedProject(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(,address projectContractAddress,,,,,bool expired) = getProposal(proposalId);
    	require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
        ucobNodeStorage.addUnitedProject(projectContractAddress);
        //TODO we can regard 'projectContractAddress' as account in 'balances', so we can transfer token into it. but for this stage, we just initialize it.
        bool success =projectContractAddress.call(INITPROJECT);
        require(success, "call to projectAddress failed");
        emit AddUnitedProject(proposalId,projectContractAddress);
    }

    /**
     * each member in UCOB can add or change united-project manager by this method,
     * they can not set manager dirtectly in United-Project-Contract,
     * because in United-Project-Contract, we do not know who is Corporation member in UCOB.
     */
    function addUnitedProjectManager(address projectContractAddress,address managerAddress) public {
    	require(checkRole(CORPORATION_MEMBERS_ROLE,msg.sender));
    	require(ucobNodeStorage.checkUnitedProject(projectContractAddress));
        bool success = projectContractAddress.call(ADDMANAGER,msg.sender,managerAddress);
        require(success, "call to projectContractAddress failed");
        emit AddUnitedProjectManager(projectContractAddress,managerAddress);
    }

    /**
     * after we grant 'CORPORATION_MEMBERS_ROLE' to a corporation member,
     * if the corporation has deposit reserve in UCOB, then we can active it according this method.
     * 
     * 1.initialize it in 'UCOBNodeStorage.balances',
     * 2.initialize it in 'corporationContract.balances'.
     */
    function activeCorporation(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(address beneficiary,address corporationContractAddress,,,,uint256 value,) = getProposal(proposalId);
    	require(ucobNodeStorage.balanceOf(corporationContractAddress) == 0,"corporation is existed");
    	ucobNodeStorage.add(corporationContractAddress,value);
    	ucobNodeStorage.supply(value);
    	bool success = corporationContractAddress.call(INITCORPORATIONCONTROLLER,beneficiary,value);
    	require(success, "call to corporationContractAddress failed");
    	emit ActiveCorporation(proposalId,corporationContractAddress,beneficiary,value);
    }
    
    
	/**
	 * a member corporation can provide liquidity by send more reserve to UCOB,
	 * 
	 */
    function issue(bytes32 proposalId) public {
    	require(proposalPassed(proposalId,getRoleMemberCount(CORPORATION_MEMBERS_ROLE),ProposalTypeEnum.Corporation));
    	(address beneficiary,address corporationContractAddress,,,,uint256 value,bool expired) = getProposal(proposalId);
    	require(!expired,"proposal is expired");
        setProposalExpired(proposalId);
    	ucobNodeStorage.add(corporationContractAddress,value);
        ucobNodeStorage.supply(value);
    	address ucob = address(this);
    	bool success = corporationContractAddress.call(TRANSFERRECEIVE,ucob,corporationContractAddress,beneficiary,value);
    	require(success, "call to corporationContractAddress failed");
    	emit Issue(proposalId,corporationContractAddress,beneficiary,value);
    }

    /**
     * 
     * get the balance of one corporation
     */
    function balanceOf(address addr) public view returns (uint256 balance) {
        return ucobNodeStorage.balanceOf(addr);
    }
    
    /**
     * 
     * get accumulative total supply
     */
    function getTotalSupply() public view returns (uint256 balance) {
        return ucobNodeStorage.totalSupply();
    }
    
    /**
     * 
     * get accumulative total burn
     */
    function getTotalBurn() public view returns (uint256 balance) {
        return ucobNodeStorage.totalBurn();
    }
    
    /**
     * TODO we can degisn logic "how and when" to burn the value.
     * such as, if one corporation want to get reserve back and quit UCOB.
     * 
     * !!!follow is a demo!!!
     */
    function burn(address addr,uint256 value) private returns (bool) {
        ucobNodeStorage.sub(addr,value);
        ucobNodeStorage.burn(value);
    	return true;
    }

    /**
     * transfer between two corporations.
     * 
     */
    function transfer(address destination,address _from, address to, uint256 value) public returns (bool) {
    	address sender = msg.sender;
    	require(checkRole(CORPORATION_MEMBERS_ROLE,sender)&&checkRole(CORPORATION_MEMBERS_ROLE,destination));
    	require(sender!=destination,"you can not send to yourself");
        ucobNodeStorage.sub(sender,value);
        ucobNodeStorage.add(destination,value);
        bool success = destination.call(TRANSFERRECEIVE,sender,_from,to,value);
        require(success,"call to transferReceive failed");
        emit Transfer(sender,_from,destination,to,value); 
        return true;
    }
}