# nova

## Prerequisites
* [JDK 11](https://download.oracle.com/otn/java/jdk/11.0.12+8/f411702ca7704a54a79ead0c2e0942a3/jdk-11.0.12_windows-x64_bin.exe?AuthParam=1627899861_e6c06c4f863a6a207f03ce3681d4a1eb)
* [MySQL 8](https://cdn.mysql.com//Downloads/MySQLInstaller/mysql-installer-community-8.0.26.0.msi) 
* [ProtoBuf Compiler](https://github.com/protocolbuffers/protobuf/releases/)
* [Intellij IDEA Ultimate](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows)
* [Zookeeper](https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.8.0/apache-zookeeper-3.8.0-bin.tar.gz) 


## Setup development environment

### Append environment variable
* Append ***${PROTOBUF_COMPILER_HOME}/bin*** into the ***PATH*** environment variable.

### Create database and load data(if not do, will use the nova_qa database settings in the *application.propertes*, so it's **Optional**)
* Create database and user.
  ```
  create user 'nova_test'@'%' identified by 'nova_test'; 

  create database nova_test character set utf8 collate utf8_general_ci;  

  grant all privileges on nova_test.* to 'nova_test'@'%';  

  flush privileges;

* Import database scripts and sample data.
  ```
  cd nova;
  cat nova_doc/dbscripts/*.sql > /tmp/all.sql;
  mysql -unova_test -pnova_test nova_test < /tmp/all.sql;

### Start zookeeper
* 
  ```
  cd ${ZOOKEEPER_HOME};
  cp conf/zoo_sample.cfg conf/zoo.cfg;
  ```
* In Windows:
  ```
  bin/zkServer.cmd start
  ```
* In Linux/macOS:
  ```
  bin/zkServer.sh start
  ```
### Import project into IDE and Debug/Run
* Select: ***File -> New -> Project from Existing Sources...***, then select ***nova/nova_parent/pom.xml***, then click "OK".
* Compile all ProtoBuf files, to generate ProtoBuf Java classes:
  ```
  cd nova/nova_msg/src/main/resources
  protoc --java_out=../java *.proto
* Install the Lombok plugin through IDE menu "File->Settings->Plugins" to support @Getter and @Setter etc. annotations from Lombok.
* Append the below line into idea.properties through IDE menu "Help->Edit Custom Properties..." to suppress the exception is ProtoBuf class file exceeds the default size.
  ```
  idea.max.intellisense.filesize=99999
  ```
* select: *Run -> Debug... -> NovaPecApplication*.
* select: *Run -> Debug... -> NovaApiApplication*.
* could access the **Nova API Endpoint Document** link: *http://localhost:8000/messages.proto.html*.

