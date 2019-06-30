pragma solidity ^0.4.25;
import "./DataProcess.sol";
import "./OrderContract.sol";

contract Market is DataProcess{

    struct Pet {
        string petName;
        string petId;
        string petType;
        uint16 petPrice;
        uint8 petStatus;
        string petImg;
        string petIntro;
        address Owner;
    }
    //全局变量声明：管理员地址，order合约地址，在售宠物，订单列表
    address private adminAddress;
    address orderAddress;
    OrderContract OD = OrderContract(orderAddress);
    uint petIdNum=1;
    Pet[] private petOnSell;



    //构造函数,设置合约部署人为管理员，管理员身份为2
    constructor() public {
        adminAddress = msg.sender;
        userIden[adminAddress] = 2;
    }

    //修饰符，判断身份是不是管理员
    modifier isAdmin(address _caller) {
        require (_caller == adminAddress);
        _;
    }

    //映射：
    //判断是否已创建过宠物
    mapping (address => uint8) createdPet;
    //地址与余额映射
    mapping (address => uint) Balance;
    //判断是否老用户
    mapping (address => uint8) oldUser;
    //用户地址到宠物列表的映射
    mapping (address => Pet[]) addressToPetList;
    //宠物id到用户地址的映射
    mapping (string => address) petIdToOwner;
    //用户地址与身份的映射
    mapping(address => uint8) userIden;


    //设置order合约地址，用于调用其中的函数
    function setOrderAddress(address _orderAddress) public isAdmin(msg.sender) {
        orderAddress = _orderAddress;
    }

    //内部调用函数：
    //初始化用户余额以及是否创建过宠物参数
    function initUser() private {
        createdPet[msg.sender] = 0;
        Balance[msg.sender] = 10000;
    }
    //为用户配置一个宠物列表
    function setAddressToPetList() private {
        Pet[] storage tempPetList;
        addressToPetList[msg.sender] = tempPetList;
    }
    //设置宠物主人
    function setPetOwner(string _id,address _owner) private {
        petIdToOwner[_id] = _owner;
    }

    //支付对应金额，内部调用
    function pay(address _from, address _to, uint16 _price) private {
        require(_to != 0x0);
        require(Balance[_from] >= _price);
        require(Balance[_to] + _price > Balance[_to]); //_value不能为负值
        uint previousBalances = Balance[_from] + Balance[_to];
        Balance[_from] -= _price;
        Balance[_to] += _price;
        assert(Balance[_from] + Balance[_to] == previousBalances);
    }
    //管理员使用的转账函数，用于退货方面的操作
    function payByAdmin(address _from,address _to, uint16 _price, address _caller) public isAdmin(_caller){
        require(_to != 0x0);
        require(Balance[_from] >= _price);
        require(Balance[_to] + _price > Balance[_to]); //_value不能为负值
        uint previousBalances = Balance[_from] + Balance[_to];
        Balance[_from] -= _price;
        Balance[_to] += _price;
        assert(Balance[_from] + Balance[_to] == previousBalances);
    }
    //转交宠物所有权，用于购买宠物和退货时使用
    function changePetOwner(address _from, address _to, string _petId, address _caller) public isAdmin(_caller) {
        require(_from != 0x0);
        require(petIdToOwner[_petId] == _from);
        setPetOwner(_petId, _to);
        uint petIndex;
        //将宠物从卖家的宠物列表删除，加入买家的宠物列表
        for(uint i = 0;i < addressToPetList[_from].length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(addressToPetList[_from][i].petId))){
                addressToPetList[_to].push(addressToPetList[_from][i]);
                delete addressToPetList[_from][i];
                petIndex = i;
            }
        }
        //手动将卖家的宠物列表移位，并将列表长度减一
        for(i = petIndex;i < addressToPetList[_from].length; i++){
            addressToPetList[_from][i] = addressToPetList[_from][i+1];
        }
        delete addressToPetList[_from][addressToPetList[_from].length-1];
        addressToPetList[_from].length--;
    }




    //用户相关：
    //创建用户函数，初始化余额和是否创建过宠物参数
    function createUser() public {
        require(oldUser[msg.sender] == 0);
        require(msg.sender != adminAddress);
        initUser();
        oldUser[msg.sender] = 1;
        userIden[msg.sender] = 1;
    }
    //获取用户身份
    function getUserIden() public view returns (uint8){
        return userIden[msg.sender];
    }
    //获取调用者的余额
    function getBalanceOfMe() public view returns (uint) {
        return Balance[msg.sender];
    }
    //通过地址获取余额
    function getBalace(address _address) public view returns (uint) {
        return Balance[_address];
    }




    //宠物相关：
    //返回用户对应的宠物列表
    function getPetListFromAddress() public view returns (string) {
        string memory result;
        for(uint i=0;i<addressToPetList[msg.sender].length;i++){
            result = strConcat(result,addressToPetList[msg.sender][i].petName);
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petId);
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petType);
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(addressToPetList[msg.sender][i].petPrice));
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(addressToPetList[msg.sender][i].petStatus));
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petImg);
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petIntro);
            result = strConcat(result,",");
        }
        return result;
    }
    //展示所有在售宠物
    function showPetOnSell() public view returns(string){
        string memory result;
        for(uint i=0;i<petOnSell.length;i++){
            result = strConcat(result,petOnSell[i].petName);
            result = strConcat(result,",");
            result = strConcat(result,petOnSell[i].petId);
            result = strConcat(result,",");
            result = strConcat(result,petOnSell[i].petType);
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(petOnSell[i].petPrice));
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(petOnSell[i].petStatus));
            result = strConcat(result,",");
            result = strConcat(result,petOnSell[i].petImg);
            result = strConcat(result,",");
            result = strConcat(result,petOnSell[i].petIntro);
            result = strConcat(result,",");
        }
        return result;
    }

    //创建宠物，若没有创建过就先创建宠物列表再创建宠物
    function createPet(string _name,  string _type, uint16 _price, string _img, string _intro) public {
        //判断是否已创建过宠物
        if(createdPet[msg.sender] == 0){
            setAddressToPetList();
            createdPet[msg.sender] = 1;
        }
        //这里创建了一个新的宠物，并放入用户对应的宠物列表
        addressToPetList[msg.sender].push(Pet(_name, getIntToString(petIdNum), _type, _price, 0, _img, _intro, msg.sender));
        setPetOwner(getIntToString(petIdNum),msg.sender);
    }
    //购买宠物
    function buyPet(string _petId, uint16 _price, string _time, address _seller) public {
        pay(msg.sender, _seller, _price);
        changePetOwner(_seller, msg.sender, _petId, adminAddress);
        OD.createOrder( _seller, _time, _petId, _price, adminAddress);
    }

    //出售宠物
    function sellPet(string _petId) public {
        require(petIdToOwner[_petId] == msg.sender);
        Pet storage tempPet;
        for(uint i=0;i<addressToPetList[msg.sender].length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(addressToPetList[msg.sender][i].petId))){
                addressToPetList[msg.sender][i].petStatus=1;
                tempPet = addressToPetList[msg.sender][i];
            }
        }
        petOnSell.push(tempPet);
    }
    //取消出售宠物
    function cancelSellPet(string _petId) public {
        require(petIdToOwner[_petId] == msg.sender);
        uint index;
        for(uint i=0;i<addressToPetList[msg.sender].length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(addressToPetList[msg.sender][i].petId))){
                addressToPetList[msg.sender][i].petStatus=0;
            }
        }
        for(i=0;i<petOnSell.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petOnSell[i].petId))){
                delete petOnSell[i];
                index = i;
                break;
            }
        }
        for(i=index;i<petOnSell.length-1;i++){
            petOnSell[i] = petOnSell[i+1];
        }
        delete petOnSell[petOnSell.length-1];
        petOnSell.length--;
    }
    //修改宠物信息
    function changePetInfo(string _name, string _id, string _type, uint16 _price, string _img, string _intro) public view {
        require(petIdToOwner[_id] == msg.sender);
        for(uint i=0;i<addressToPetList[msg.sender].length;i++){
            if(keccak256(abi.encodePacked(addressToPetList[msg.sender][i].petId)) == keccak256(abi.encodePacked(_id))){
                _name = addressToPetList[msg.sender][i].petName;
                _type = addressToPetList[msg.sender][i].petType;
                _price = addressToPetList[msg.sender][i].petPrice;
                _img = addressToPetList[msg.sender][i].petImg;
                _intro = addressToPetList[msg.sender][i].petIntro;
                break;
            }
        }
    }
    
}
