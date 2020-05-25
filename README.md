### 工具说明
kafka队列数据转发工具

#### 转发网关数据：
消费生产环境的网关输出的kafka队列的数据  
将数据转发到另外环境的kafka队列，一般是转发到测试环境的网关输出队列  
如果开启了SN过滤，则会解析了队列中的消息内容，根据SN进行判断  

##### 过滤SN
只将./config/deviceSN.txt 文件中配置的SN转发到目标队列  
- 一个设备号一行
- 设备号应该是全数字的
- 不会对SN进行其他任何校验

	
#### 转发其他队列数据：
不进行数据解析，直接从源读取byte[] 转发到目的地


### 配置说明
**./config/system.properties**

#### 源头kafka配置
```
source.kafka.bootstrap.servers=192.168.1.224:9091,192.168.1.224:9092,192.168.1.224:9093
source.kafka.group.id=transponder
# latest | earliest
source.kafka.auto.offset.reset=earliest
source.kafka.auto.commit.interval.ms=1000
source.kafka.topic=st.topic.yery.test
```
#### 目的地kafka配置
```
dest.kafka.bootstrap.servers=192.168.1.224:9091,192.168.1.224:9092,192.168.1.224:9093
dest.kafka.topic=st.topic.wwh.test
```

#### 转发类型

##### 转发网关数据
转发网关数据，反序列化为对象
- transfert.type=gateway 

 开启SN 过滤
 - transfert.gateway.deviceSn.filter=enable

##### 转发其他队列数据
转发其他队列数据，不解析，直接转发
- transfert.type=normal  



