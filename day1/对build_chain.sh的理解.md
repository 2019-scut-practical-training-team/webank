# 对build_chain.sh的理解

### build_chain.sh的内容

- 开头定义了一些变量，用来保存在运行时需要的参数，例如：
  *  `ca_file`：CA 私钥
  * `ip_param`：ip有关的信息
  * `output_dir`：输出目录
  * 等等
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
   `check_env() {
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

   ```bash
   while getopts "f:l:o:p:e:t:v:icszhgTFdC:S" option;do
       case $option in
       f) ip_file=$OPTARG
          use_ip_param="false"
       ;;
       l) ip_param=$OPTARG
          use_ip_param="true"
       ;;
       
       #省略中间的代码
       
       esac
   ```

   ​        每次执行循环时，getopts都会检查下一个命令选项，如果这些选项出现在option中，则表示是合法选项，`$OPTARG`就是将选项后面的参数（或者描述信息DESCPRITION）保存在这个变量当中。我们实际操作中运行了以下命令：

   ```bash
   bash build_chain.sh -l "127.0.0.1:4" -p 30300,20200,8545 
   ```

   ​        将ip信息存在`ip_param`中，起4个节点，并设置了四个端口

   

3. 然后执行`main()`函数

   ​        在联盟链的准入机制中，证书是各参与方相互认证身份的重要凭证。FISCO BCOS的证书结构中有4种角色，分别是：联盟链委员会（CA）、联盟链成员机构（agency）、联盟链参与方（node和SDK）。`main()`函数内进行了这几种相关证书的生成。由于操作中未使用docker和国密，所以不做解释，主要步骤如下：

   

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
              
              #省略中间的代码
              
              # 若是未通过-v操作项指定版本，使用上述默认版本。
              Download_Link="https://github.com/FISCO-BCOS/FISCO-BCOS/releases/download/v${fisco_version}/${package_name}"
              
              LOG_INFO "Downloading fisco-bcos binary from ${Download_Link} ..." 
              
              # 解开对应打包文件，并对相关路径和版本进行检查。
              curl -LO ${Download_Link}
              tar -zxf ${package_name} && mv fisco-bcos ${bin_path} && rm ${package_name}
             
             #省略中间的代码
             
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

      

   5. 联盟链委员会初始化根证书ca.crt

      ```bash
      gen_chain_cert() {
      #省略中间代码
      
      #openssl genrsa 命令可以生成一个RSA私钥，-out说明生成的私钥文件，可从中提取公钥,通过以下命令在本地生成私钥ca.key，私钥的长度为2048。
      openssl genrsa -out $chaindir/ca.key 2048
      
      #自签生成根证书ca.crt，其中-x509的意思是，FISCO BCOS使用x509协议的证书格式。
      openssl req -new -x509 -days 3650 -subj "/CN=$name/O=fisco-bcos/OU=chain" -key $chaindir/ca.key -out $chaindir/ca.crt
      mv cert.cnf $chaindir
      }
      
      #省略中间代码
      
      gen_chain_cert "" ${output_dir}/chain >${output_dir}/${logfile} 2>&1 || fail_message "openssl error!"
      ```

   6. 联盟链成员机构获取机构证书agency.crt

      ```bash
      gen_agency_cert() {
      
      #省略中间代码
      
      #本地生成私钥agency.key，私钥长度为2048。
      openssl genrsa -out $agencydir/agency.key 2048
      
      #机构首先在本地使用机构私钥agency.key生成证书请求文件agency.csr。
      openssl req -new -sha256 -subj "/CN=$name/O=fisco-bcos/OU=agency" -key $agencydir/agency.key -config $chain/cert.cnf -out $agencydir/agency.csr
      
      #将证书请求文件agency.csr发送至联盟链委员会，联盟链委员会使用ca.key对证书请求文件agency.csr进行签发，得到联盟链成员机构证书agency.crt，联盟链委员会将联盟链成员机构证书agency.crt发送至对应成员。
      openssl x509 -req -days 3650 -sha256 -CA $chain/ca.crt -CAkey $chain/ca.key -CAcreateserial\-in $agencydir/agency.csr -out $agencydir/agency.crt  -extensions v4_req -extfile $chain/cert.cnf
              
      }
      
      #省略中间代码
      
      gen_agency_cert "" ${output_dir}/cert ${output_dir}/cert/agency >${output_dir}/${logfile} 2>&1
      ```

      

   7. 给每个ip下的每个节点生成它的密钥和node.crt

      ```bash
      gen_node_cert() {
      #检验agency相关信息
      ...
      gen_cert_secp256k1 "$agpath" "$ndpath" "$node" node
      ...
      }
      
      ...
      
      gen_cert_secp256k1() {
      ...
      #生成EC私钥
      openssl ecparam -out $certpath/${type}.param -name secp256k1
      
      #genpkey命令用于产生各种密钥（RSA、DSA、DH、EC等）的私钥值。
      openssl genpkey -paramfile $certpath/${type}.param -out $certpath/${type}.key
      
      #pkey是一个公钥或私钥的处理命令，可以用于打印和转换不同的表单和组件
      openssl pkey -in $certpath/${type}.key -pubout -out $certpath/${type}.pubkey
      
      #由node.key生成证书请求文件node.csr
      openssl req -new -sha256 -subj "/CN=${name}/O=fisco-bcos/OU=${type}" -key $certpath/${type}.key -config $capath/cert.cnf -out $certpath/${type}.csr
      
      #将证书请求文件node.csr发送至联盟链成员机构,联盟链成员机构使用agency.key对证书请求文件node.csr进行签发，得到节点证书node.crt.
      openssl x509 -req -days 3650 -sha256 -in $certpath/${type}.csr -CAkey $capath/agency.key -CA $capath/agency.crt\-force_pubkey $certpath/${type}.pubkey -out $certpath/${type}.crt -CAcreateserial -extensions v3_req -extfile $capath/cert.cnf
      
      #不清楚在干嘛
      openssl ec -in $certpath/${type}.key -outform DER | tail -c +8 | head -c 32 | xxd -p -c 32 | cat >$certpath/${type}.private
      
      }
      
      ...
      
      gen_node_cert "" ${output_dir}/cert/${agency_array[${server_count}]} ${node_dir} >${output_dir}/${logfile} 2>&1
      ```

      

   8. 给每个ip的每个节点生成配置信息

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

   