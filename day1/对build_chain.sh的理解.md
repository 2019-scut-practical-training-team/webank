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

   1. 先设置好ip参数

      ```bash
      # 检查是否有指定的输出目录，没有的话默认输出在当前的目录
      output_dir="$(pwd)/${output_dir}"
      
      # 检查是否有设置ip参数
      [ -z $use_ip_param ] && help 'ERROR: Please set -l or -f option.'     
      
      if [ "${use_ip_param}" == "true" ];then              # 以参数的方式设置ip参数
          ip_array=(${ip_param//,/ })
      elif [ "${use_ip_param}" == "false" ];then            # 以文件的方式设置ip参数
          if ! parse_ip_config $ip_file ;then               # 利用 parse_ip_config 函数转换保存了ip参数的文件
              echo "Parse $ip_file error!"
              exit 1
          fi
      else                               
          help 
      fi
      ```

      

   2. 在保证`output_dir`为空的前提下下载*fisco-bcos*，并检查版本是否兼容

      ```bash
      if [ -z ${docker_mode} ];then
          if [[ -z ${bin_path} && -z ${OS} ]];then
              # 省略中间的代码
              echo "Binary check passed."
          fi
      fi
      ```

      

   3. 如果证书为空或者证书文件不存在，就新创建一个，否则将证书文件复制到当前目录下

      ```bash
      if [ -z ${CertConfig} ] || [ ! -e ${CertConfig} ];then
          # CertConfig="${output_dir}/cert.cnf"
          generate_cert_conf "cert.cnf"
      else 
         cp ${CertConfig} .
      fi
      ```

      

   4. 如果采用输入参数的方式输入ip参数时，还要转换机构和群组的信息

      ```bas
      if [ "${use_ip_param}" == "true" ];then
          for i in $(seq 0 ${#ip_array[*]});do
              agency_array[i]="agency"
              group_array[i]=1
          done
      fi
      ```

      

   5. 准备机构的证书

      ```bash
      echo "=============================================================="
      if [ ! -e "$ca_file" ]; then
          echo "Generating CA key..."
          # 省略中间的代码
          ca_file="${output_dir}/cert/ca.key"
      fi
      ```

      

   6. 给每个ip下的每个节点生成它的密钥

      ```bash
      echo "=============================================================="
      echo "Generating keys ..."
      nodeid_list=""
      ip_list=""
      count=0
      server_count=0
      groups=
      ip_node_counts=
      groups_count=
      for line in ${ip_array[*]};do
          # 省略中间的代码
      done 
      ```

      

   7. 给每个ip的每个节点生成配置信息

      ```bash
      ip_node_counts=()
      echo "=============================================================="
      echo "Generating configurations..."
      cd ${current_dir}
      server_count=0
      for line in ${ip_array[*]};do
          # 省略中间代码
          ((++server_count))
      done 
      rm ${output_dir}/${logfile}
      if [ "${use_ip_param}" == "false" ];then
      # 省略中间代码
      fi
      
      }
      ```

      

4. 最后执行`print_result()`函数，将执行脚本后的结果打印到终端上。

   ```bash
   print_result()
   {
   echo "================================================================"
   LOG_INFO "Execute the following command to get FISCO-BCOS console"
   echo " bash <(curl -s https://raw.githubusercontent.com/FISCO-BCOS/console/master/tools/download_console.sh)"
   echo "================================================================"
   [ -z ${docker_mode} ] && LOG_INFO "FISCO-BCOS Path   : $bin_path"
   [ ! -z ${docker_mode} ] && LOG_INFO "Docker tag        : latest"
   [ ! -z $ip_file ] && LOG_INFO "IP List File      : $ip_file"
   # [ ! -z $ip_file ] && LOG_INFO -e "Agencies/groups : ${#agency_array[@]}/${#groups[@]}"
   LOG_INFO "Start Port        : ${port_start[*]}"
   LOG_INFO "Server IP         : ${ip_array[*]}"
   LOG_INFO "State Type        : ${state_type}"
   LOG_INFO "RPC listen IP     : ${listen_ip}"
   [ ! -z ${pkcs12_passwd} ] && LOG_INFO "SDK PKCS12 Passwd : ${pkcs12_passwd}"
   LOG_INFO "Output Dir        : ${output_dir}"
   LOG_INFO "CA Key Path       : $ca_file"
   [ ! -z $guomi_mode ] && LOG_INFO "Guomi mode        : $guomi_mode"
   echo "================================================================"
   LOG_INFO "All completed. Files in ${output_dir}"
   }
   ```

   