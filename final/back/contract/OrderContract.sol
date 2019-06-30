pragma solidity ^0.4.25;
import "./DataProcess.sol";
import "./Market.sol";

contract OrderContract is DataProcess{
    //订单结构体
    struct Order {
        string orderId;
        address orderBuyer;
        address orderSeller;
        string orderTime;
        string petId;
        uint16 petPrice;
        uint8 orderStatus;
        string returnResult;
    }
    //声明变量：market合约地址，管理员地址，订单列表，订单id
    address marketAddress;
    Market MK = Market(marketAddress);
    address private adminAddress;
    Order[] public orderList;
    uint orderIdNum=1;

    //构造函数 管理员地址
    constructor() public {
        adminAddress = msg.sender;
    }
    //修饰符，身份是否管理员
    modifier isAdmin(address _caller) {
        require (_caller == adminAddress);
        _;
    }
    //设置market合约地址
    function setMarketAddress(address _mkAddress) public isAdmin(msg.sender){
        marketAddress = _mkAddress;
    }


    //创建一个新订单，market调用
    function createOrder(address _seller, string _time, string _petId, uint16 _petPrice, address _caller) public isAdmin(_caller) {
        orderList.push(Order(getIntToString(orderIdNum), _caller, _seller, _time, _petId, _petPrice, 0, ""));
        orderIdNum++;
    }


    //订单相关：
    //管理员部分：
    //返回所有订单（管理员查看
    function adminGetOrderList() public isAdmin(msg.sender) returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i=0;i<orderList.length;i++){
            result = strConcat(result,orderList[i].orderId);
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].orderTime);
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].petId);
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(uint(orderList[i].petPrice)));
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(uint(orderList[i].orderStatus)));
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].returnResult);
            result = strConcat(result,",");

            buyerAddress.push(orderList[i].orderBuyer);
            sellerAddress.push(orderList[i].orderSeller);
        }
        return (result, buyerAddress, sellerAddress);
    }
    //管理员获得请求仲裁的订单
    function adminGetReturnOrderList() public isAdmin(msg.sender) returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i = 0;i < orderList.length;i++){
            if(orderList[i].orderStatus == 1){
                result = strConcat(result,orderList[i].orderId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].orderTime);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petId);
                result = strConcat(result,",");
                result = strConcat(result,getIntToString(uint(orderList[i].petPrice)));
                result = strConcat(result,",");
                result = strConcat(result,getIntToString(uint(orderList[i].orderStatus)));
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].returnResult);
                result = strConcat(result,",");

                buyerAddress.push(orderList[i].orderBuyer);
                sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result, buyerAddress, sellerAddress);
    }
    //管理员接受退货申请
    function acceptReturn(string _orderId) public isAdmin(msg.sender) {
        uint index;
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                index = i;
                break;
            }
        }
        orderList[index].orderStatus = 2;
        MK.changePetOwner(orderList[index].orderBuyer, orderList[index].orderSeller, orderList[index].petId, msg.sender);
        MK.payByAdmin(orderList[index].orderBuyer,orderList[index].orderSeller,orderList[index].petPrice, msg.sender);
    }
    //管理员拒绝退货申请
    function rejectReturn(string _orderId) public isAdmin(msg.sender) {
        uint index;
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                index = i;
                break;
            }
        }
        orderList[index].orderStatus = 3;
    }


    //用户部分：
    //用户获得自己的订单列表
    function userGetOrderList() public view returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i = 0;i < orderList.length;i++){
            if(orderList[i].orderBuyer == msg.sender || orderList[i].orderSeller == msg.sender){
                result = strConcat(result,orderList[i].orderId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].orderTime);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petId);
                result = strConcat(result,",");
                result = strConcat(result,getIntToString(uint(orderList[i].petPrice)));
                result = strConcat(result,",");
                result = strConcat(result,getIntToString(uint(orderList[i].orderStatus)));
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].returnResult);
                result = strConcat(result,",");

                buyerAddress.push(orderList[i].orderBuyer);
                sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result, buyerAddress, sellerAddress);
    }
    //申请退货
    function applyForReturn(string _orderId) public {
        uint index;
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                index = i;
                break;
            }
        }
        //判断申请者为买方且订单状态为可退货
        require(orderList[index].orderBuyer == msg.sender && orderList[index].orderStatus == 0);
        orderList[index].orderStatus = 1;
    }
}