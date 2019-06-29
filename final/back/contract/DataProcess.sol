pragma solidity ^0.4.19;

contract DataProcess {
    function strConcat(string _a, string _b) public view returns (string){
        bytes memory _ba = bytes(_a);
        bytes memory _bb = bytes(_b);
        string memory ret = new string(_ba.length + _bb.length);
        bytes memory bret = bytes(ret);
        uint k = 0;
        for (uint i = 0; i < _ba.length; i++) {
            bret[k++] = _ba[i];
        }
        for (i = 0; i < _bb.length; i++) {
            bret[k++] = _bb[i];
        }
        return string(ret);
   }
   
   function getIntToString(int _input) public view returns (string){
      string[10] memory numToString = ["0","1","2","3","4","5","6","7","8","9"];
      string memory output;
      int temp = _input;
      output = numToString[uint(temp - (temp/10)*10)];
      temp = temp/10;
      for(;temp>0;){
          output = strConcat(numToString[uint(temp - (temp/10)*10)] , output);
          temp=temp / 10;
      }
      return output;
   }
}