pragma solidity ^0.4.25;

import "./lib/math/SafeMath.sol";

contract ProposalController  {
    /**
     * different type correspond to different level, and have different Vote ratio
     * for example, '{Corporation} proposal type' only can be voted by corporation members,
     */
    enum ProposalTypeEnum {
        Corporation,
        Project,
        Proposal
    }

    /**
     * shows the details of each proposal
     */
    struct VoteDetails{
        address beneficiary;//an user address(outer address), who start this proposer,usually be ceo of a corporation, will be the account receive the balance value
        address proposerContract;//a contract address(inner account), can represent a corporation contract or an UCOBNodeController contract
        mapping(address => bool) voters;//records of voted address, this address could be corporation contract address, once voted, can not vote repetition
        uint256 voteCount;//total votes
        bytes32 message;//cutline of this proposal
        ProposalTypeEnum proposalType;//the type, different type correspond to different vote ratio
        uint256 value;//required reserves,after approved, this value will be send to the corporation account in this contract and send to proposer in corporation contract.
        bool expired;//to indicate if this proposal is expired
    }

    /**
     * 
     * proposalId => VoteDetails
     */
    mapping(bytes32 => VoteDetails) proposals;
    
    event InitProposal(address indexed proposer,bytes32 proposalId);
    event Vote(address indexed voter,bytes32 proposalId);
    
    /**
     * when start a new proposal. can not cover former 
     */
    function initProposal(bytes32 proposalId,address beneficiary,address proposerContract,bytes32 message,ProposalTypeEnum proposalType,uint256 value) internal returns (bool) {
        require(proposals[proposalId].voteCount==0,"proposal exist");
        proposals[proposalId].beneficiary = beneficiary;
        proposals[proposalId].proposerContract = proposerContract;
        proposals[proposalId].message = message;
        proposals[proposalId].proposalType = proposalType;
        proposals[proposalId].value = value;
        emit InitProposal(msg.sender,proposalId);
        return true;
    }

    /**
     * vote proposal, can not vote repetition
     */
    function vote(bytes32 proposalId) internal returns (bool) {
        VoteDetails storage voteDetails = proposals[proposalId];
        require(voteDetails.voters[msg.sender]==false,"you have already voted");
        voteDetails.voters[msg.sender]=true;
        voteDetails.voteCount += 1;
        emit Vote(msg.sender,proposalId);
        return true;
    }

    /**
     * check if the proposal has been passed.
     * different level, have different Vote ratio
     */
    function proposalPassed(bytes32 proposalId,uint256 members,ProposalTypeEnum proposalType ) internal view returns (bool) {
        uint256 voteCount = proposals[proposalId].voteCount;
        if(voteCount == 0){
            return false;
        }
        if(proposalType==ProposalTypeEnum.Corporation){
            //'endorse/total' should more than 2/3
            require(SafeMath.div(SafeMath.mul(voteCount,10),members)>=6,"Insufficient votes");
        }else if(proposalType==ProposalTypeEnum.Project){
            //'endorse/total' should more than 1/2
            require(SafeMath.div(SafeMath.mul(voteCount,10),members)>=5,"Insufficient votes");
        }else if(proposalType==ProposalTypeEnum.Proposal){
            //'endorse/total' should more than 1/3
            require(SafeMath.div(SafeMath.mul(voteCount,10),members)>=3,"Insufficient votes");
        }
        return true;
    }
    
    /**
     * set the proposal expired
     */
    function setProposalExpired(bytes32 proposalId) internal {
        proposals[proposalId].expired = true;
    }
    
    /**
     * get detail of the proposal.
     */
    function getProposal(bytes32 proposalId) public view returns (address,address,uint256,bytes32,ProposalTypeEnum,uint256,bool){
        VoteDetails storage voteDetails = proposals[proposalId];
        return (voteDetails.beneficiary,voteDetails.proposerContract,voteDetails.voteCount,voteDetails.message,voteDetails.proposalType,voteDetails.value,voteDetails.expired);
    }
}