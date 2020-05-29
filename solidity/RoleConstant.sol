pragma solidity ^0.4.25;

contract RoleConstant {
	bytes32 public constant CORPORATION_MEMBERS_ROLE = keccak256("CORPORATION_MEMBERS_ROLE");//members corporation in UCOB.
	bytes32 public constant UNITED_PROJECT_MANAGER_ROLE = keccak256("UNITED_PROJECT_MANAGER_ROLE");//manager in united project,all members in UCOB will attend the united project.
    bytes32 public constant PROJECT_MANAGER_ROLE = keccak256("PROJECT_MANAGER_ROLE");//manager in project, which the project belongs to one corporation
    bytes32 public constant PROJECT_STAFF_ROLE = keccak256("PROJECT_STAFF_ROLE");//staff in project,this project belongs to one corporation
    bytes32 public constant CORPORATION_CEO_ROLE = keccak256("CORPORATION_CEO_ROLE");//CEO of corporation
    bytes32 public constant CORPORATION_CFO_ROLE = keccak256("CORPORATION_CFO_ROLE");//CFO of corporation
    bytes32 public constant CORPORATION_BRANCH_MANAGER_ROLE = keccak256("CORPORATION_BRANCH_MANAGER_ROLE");//branch manager of corporation
    bytes32 public constant CORPORATION_STAFF_ROLE = keccak256("CORPORATION_STAFF_ROLE");//staff in one corporation
}
