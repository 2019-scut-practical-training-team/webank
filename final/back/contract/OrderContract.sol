pragma solidity ^0.4.25;
import "./DataProcess.sol";
//import "./Market.sol";

interface Market {
    function changePetOwner(address _from, address _to, string _petId, address _caller) external;
    function payByAdmin(address _from,address _to, uint16 _price, address _caller) external;
}

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
        string returnReason;
    }
    //声明变量：market合约地址，管理员地址，订单列表，订单id
    Market MK;
    address private adminAddress;
    Order[] public orderList;
    uint orderIdNum=1;



    //构造函数 设置管理员地址
    constructor() public {
        adminAddress = msg.sender;
    }
    //修饰符，身份是否管理员
    modifier isAdmin(address _caller) {
        require (_caller == adminAddress);
        _;
    }
    
    
    //内部管理员函数：
    //设置market合约地址
    function setMarketAddress(address _mkAddress) public isAdmin(msg.sender){
        MK = Market(_mkAddress);
    }


    //创建一个新订单，market管理员调用
    function createOrder(address _buyer, address _seller, string _time, string _petId, uint16 _petPrice, address _caller) public isAdmin(_caller) {
        orderList.push(Order(getIntToString(orderIdNum), _buyer, _seller, _time, _petId, _petPrice, 0, ""));
        orderIdNum++;
    }


    //订单相关：
    //管理员部分：
    //返回所有订单（管理员查看
    function adminGetOrderList() public isAdmin(msg.sender) returns (string) {
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
            result = strConcat(result,getIntToString(uint(orderList[i].petPrice)));
            result = strConcat(result,",");
            result = strConcat(result,getIntToString(uint(orderList[i].orderStatus)));
            result = strConcat(result,",");

            //buyerAddress.push(orderList[i].orderBuyer);
            //sellerAddress.push(orderList[i].orderSeller);
        }
        return (result);
    }
    //管理员获得请求仲裁的订单
    function adminGetReturnOrderList() public isAdmin(msg.sender) returns (string) {
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
                result = strConcat(result,orderList[i].returnReason);
                result = strConcat(result,",");

                //buyerAddress.push(orderList[i].orderBuyer);
                //sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result);
    }
    
    //退货相关：
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
    function userGetOrderList() public view returns (string) {
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

                //buyerAddress.push(orderList[i].orderBuyer);
                //sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result);
    }
    //申请退货
    function applyForReturn(string _orderId, string _reason) public {
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                //判断申请者为买方且订单状态为可退货
                require(orderList[i].orderBuyer == msg.sender && orderList[i].orderStatus == 0);
                orderList[i].orderStatus = 1;
                orderList[i].returnReason = _reason;
                break;
            }
        }
        
    }
}
