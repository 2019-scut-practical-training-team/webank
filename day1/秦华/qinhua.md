#### 1、查看区块高度 ####
打开控制台  ./start.sh  
查看区块高度 getBlockNumber  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png)
#### 2、获取区块数据 ####
getBlockByNumber 0  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/2.png)
#### 3、部署HelloWorld智能合约 ####
deploy HelloWorld  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/3.png)
#### 4、使用查看getDeployLog ####
getDeployLog  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/4.png)
#### 5、调用智能合约 ####
call HelloWorld ... get  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/5.png)
#### 6、再次查看区块高度 ####
getBlockNumber  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/6.png)
#### 7、获取区块数据 ####
getBlockByNumber 18  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/7.png)
#### 8、按CNS方式部署HelloWorld智能合约 ####
deployByCNS HelloWorld.sol 3.0  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/8.png)
#### 9、再次查看区块高度 ####
getBlockNumber  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/9.png)
#### 10、获取区块数据 ####
getBlockByNumber 20  
![](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%A7%A6%E5%8D%8E/image/10.png)