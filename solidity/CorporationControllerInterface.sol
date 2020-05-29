pragma solidity ^0.4.25;

interface CorporationControllerInterface {
    
    /**
     * initial the corporation controller contract,
     * send the {_address} initial {value}.
     */
    function initCorporationController(address _address,uint256 value) external returns (bool);
    
    /**
     * transfer value in inner corporation
     * 
     */
    function transfer(address to, uint256 value) external returns (bool);
    
    /**
     * transfer value between two corporations.
     * {destination} is another Corporation-Controller-Contract-Address, outer address
     * {to} is another user address in another corporation, inner address
     * {value} is amount to be transfer
     * 
     * !!!NOTE!!!
     * when the user ask a 'transferOut' request, the event in this method will be listened in SDK,
     * for now, value is still in inner.
     * and when 'transferOutCallback' method is called by CFO_ROLE, the cross-transfer is actually finished.
     */
    function transferOut(address destination,address to, uint256 value) external returns (bool);
    
    /**
     * will invoke UCOBNodeControllerContract.transfer() method to do the real transfer
     * 
     */
    function transferOutCallback(bytes32 key) external returns (bool);
    
    /**
     * will invoked by 'UCOBNodeControllerContract.transfer()'' when receive value from another corporation
     * 
     */
    function transferReceive(address original,address _from,address to, uint256 value) external returns (bool);
    
}