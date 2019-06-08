# 对build_chain.sh的理解

### build_chain.sh的内容

- 开头定义了一些变量，用来保存在运行时需要的参数
- 中间定义了许多函数，其中一些比较重要的函数解释如下：
  - `help()`：在控制台输入 -h 时，在控制台打印的帮助菜单
  - `LOG_WARN()`：在控制台输出警告信息（红色）
  - `LOG_INFO()`：在控制台输出提示信息（黄色）
  - `parse_params()`：在运行此脚本时，如果后面有输入参数，将会修改对应的配置的值（脚本开头处的变量）
  - `print_result()`：在输出运行脚本（bash build_chain.sh)之后的结果。
  - `fail_message()`：打印错误信息，并设置运行脚本失败
  - `check_env()`：检查环境是否符合
  - `gen_chain_cert()`：生成链的证书
  - `gen_agency_cert()`：生成机构的证书
  - `gen_node_cert()`：生成节点的证书
  - `generate_config_ini()`：生成节点的配置文件
  - `generate_node_scripts()`：生成节点的的运行和停止脚本（start.sh和stop.sh)
  - `generate_server_scripts()`：生成管理所有节点的服务器的运行和停止脚本（start_all.sh和stop_all.sh)
  - `parse_ip_config()`：处理记录ip信息的文件，并将信息记录下来（记录到ip_array、agency_array、group_array）
  - `main()`：主函数，大部分的逻辑在此实现





### build_chain.sh执行的主要流程

1. 先执行检查当前环境是否符合运行要求的函数：`check_env()`

   ```bash
   check_env() {
       # 先检查是否有安装openssl，以及它们的版本是多少？如果没有安装openssl的话，提示安装并中断脚本的执行。
       [ ! -z "$(openssl version | grep 1.0.2)" ] || [ ! -z "$(openssl version | grep 1.1)" ] || [ ! -z "$(openssl version | grep reSSL)" ] || {
           echo "please install openssl!"
           #echo "download openssl from https://www.openssl.org."
           echo "use \"openssl version\" command to check."
           exit $EXIT_CODE
       }
       
       # 将openssl的路径加入到系统环境变量中
       if [ ! -z "$(openssl version | grep reSSL)" ];then
           export PATH="/usr/local/opt/openssl/bin:$PATH"
       fi
       
       # 记录当前操作系统是什么。
       if [ "$(uname)" == "Darwin" ];then
           OS="macOS"
       elif [ "$(uname -s)" == " Linux " ];then
           OS="Linux"
       fi
   }
   ```

   

2. 然后执行函数：`parse_params()`，此函数主要将启动脚本时候的参数进行记录

3. 然后执行`main()`函数

   1. 

4. 最后执行`print_result()`函数，将执行脚本后的结果打印到终端上。