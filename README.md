# hij2se
搭建基于maven的j2se项目

[TOC]

## 使用mvn命令创建j2se项目

```
mvn archetype:generate -DgroupId=com.lewjun -DartifactId=hij2se -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -X -DarchetypeCatalog=local
```

# hij2sespring3
在hij2se的基础上整合spring3
### 添加spring依赖

``` xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
    <exclusions>
        <!-- Exclude Commons Logging in favor of SLF4j -->
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
```

### 添加spring.xml
在src/main/resources下新建spring.xml
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">
    
</beans>
``` 

### 测试是否成功
```
ApplicationContext context = null;
@Before
public void initSpringContext() {
    context = new ClassPathXmlApplicationContext("spring/spring.xml");
    LOGGER.info("context={}", context);
}
```
如果没有报错误，说明加入spring正确

## 如何给一个Date属性注入值
```
public class Emp {
    private Date hiredate;
    ...
}
<bean id="emp" class="com.lewjun.bean.Emp">
<property name="hiredate" value="1988-07-02"></property>
</bean>
```
得到一个错误：
Could not instantiate bean class [java.util.Date]: Constructor threw exception; nested exception is java.lang.IllegalArgumentException

### java将一个字符串转换成为Date类型
```
String dateStr = "1988-07-02";
String pattern="yyyy-MM-dd";
SimpleDateFormat sdf = new SimpleDateFormat(pattern);
Date date = sdf.parse(dateStr);
System.out.println(date);
```

* 解决方式1 Factory bean
声明一个dateFormat bean，然后在Emp bean中，引用dateFormat 作为一个factory bean。factory method 将调用SimpleDateFormat.parse()来自动将String转换为Date。

``` xml
<bean id="dateFormat" class="java.text.SimpleDateFormat">
    <constructor-arg value="yyyy-MM-dd" />
</bean>

<bean id="emp" class="com.lewjun.bean.Emp">
    <property name="hiredate">
        <bean factory-bean="dateFormat" factory-method="parse">
            <constructor-arg value="1988-07-02" />
        </bean>
    </property>
</bean>
```

* 解决方式2 CustomDateEditor

``` xml
<bean id="customEditorConfigurer"
    class="org.springframework.beans.factory.config.CustomEditorConfigurer">
    <property name="customEditors">
        <map>
            <entry key="java.util.Date">
                <bean class="org.springframework.beans.propertyeditors.CustomDateEditor">
                    <constructor-arg index="0">
                        <bean class="java.text.SimpleDateFormat">
                            <constructor-arg>
                                <value>yyyy-MM-dd</value>
                            </constructor-arg>
                        </bean>
                    </constructor-arg>
                    <constructor-arg index="1">
                        <value>false</value>
                    </constructor-arg>
                </bean>
            </entry>
        </map>
    </property>
</bean>
```


## context:component-scan自动注入

### spring.xml加入context schema

``` xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.2.xsd">
```

### 配置context:component-scan

``` xml
<!-- 自动扫描(自动注入)，扫描com.lewjun.service这个包以及它的子包的所有使用@Service注解标注的类 -->
<context:component-scan base-package="com.lewjun.service" />

<!-- 自动扫描(自动注入)，扫描com.lewjun这个包以及它的子包的所有使用@Service, @Repository注解标注的类 -->
    <context:component-scan base-package="com.lewjun" />
    
<!-- 自动扫描(自动注入)，扫描com.lewjun.service, com.lewjun.dao这个包以及它的子包的所有使用@Service, @Repository注解标注的类 -->
<context:component-scan base-package="com.lewjun.service, com.lewjun.dao" />
```

### 使用@Autowired

``` java
EmpService empService = context.getBean(EmpService.class);
LOGGER.info("{}",empService);
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;
}

@Test
public void testAutowired() {
    EmpService empService = context.getBean(EmpService.class);
    LOGGER.info("{}", empService);
   empService.save(new Emp());
}
```

## 使用@Value访问properties文件的属性值

developer.properties

``` properties
# \u59D3\u540D
developer.name=LewJun
# \u5E74\u9F84
developer.age=25
# \u751F\u65E5
developer.birthday=1988-07-02
```

Developer.java

``` java
@Component
public class Developer {

    @Value("${developer.name}")
    private String  name;

    @Value("${developer.age}")
    private Integer age;

    @Value("${developer.birthday}")
    private Date    birthday;
    // getter and setter
}
```

spring.xml

* 配置方式1：

``` xml
<!-- 引入配置文件 -->
<bean id="propertyConfigurer"
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <!-- <property name="location" value="classpath:developer.properties" /> -->
    <property name="locations">
        <list>
            <value>classpath:developer.properties</value>
        </list>
    </property>
</bean>
```

* 配置方式2：

```
<context:property-placeholder location="classpath:developer.properties"/>
```

使用：

``` java
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    Developer developer;
}
```

## 使用Spring提供的Junit测试框架进行单元测试
### 添加spring-test依赖

``` xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>${spring.version}</version>
</dependency>
```

### SpringJunitTest

``` java
@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = { "classpath:spring/spring.xml" })
public class SpringJunitTest {
    @Autowired
    private EmpService empService;

    @Test
    public void testEmpServiceSave() {
        empService.save(new Emp());
    }
}

```


# hij2sespring3mybatis
在hij2sespring3的基础上整合mybatis
## 添加mysql驱动依赖

``` xml
<!-- mysql 驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.37</version>
</dependency>
```

## 添加jdbc.properties属性值
添加src/main/resources/jdbc.properties

``` properties
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://192.168.1.189:3306/scott
jdbc.username=root
jdbc.password=sunnysoft

#\u5B9A\u4E49\u521D\u59CB\u8FDE\u63A5\u6570  
jdbc.initialSize=0
#\u5B9A\u4E49\u6700\u5927\u8FDE\u63A5\u6570  
jdbc.maxActive=20
#\u5B9A\u4E49\u6700\u5927\u7A7A\u95F2  
jdbc.maxIdle=20
#\u5B9A\u4E49\u6700\u5C0F\u7A7A\u95F2  
jdbc.minIdle=1
#\u5B9A\u4E49\u6700\u957F\u7B49\u5F85\u65F6\u95F4  
jdbc.maxWait=60000
```

## 添加数据库表
添加dbscript/1.0/scott.sql
``` sql
/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : scott

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2016-09-05 00:05:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bonus`
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus` (
  `ID` int(11) NOT NULL auto_increment,
  `EMPNO` int(11) NOT NULL COMMENT '雇员编号',
  `SAL` decimal(7,2) NOT NULL COMMENT '雇员工资',
  `COMM` decimal(7,2) NOT NULL COMMENT '雇员奖金',
  `PAYTIME` date NOT NULL COMMENT '支付时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES ('1', '7839', '10000.00', '5000.00', '2016-09-12');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `DEPTNO` int(4) NOT NULL COMMENT '部门编号',
  `DNAME` varchar(14) NOT NULL COMMENT '部门名称',
  `LOC` varchar(13) NOT NULL COMMENT '部门所在位置',
  PRIMARY KEY  (`DEPTNO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES ('20', 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES ('30', 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES ('40', 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `EMPNO` int(4) NOT NULL auto_increment COMMENT '雇员编号',
  `ENAME` varchar(10) default NULL COMMENT '雇员姓名',
  `JOB` varchar(9) default NULL COMMENT '雇员职位',
  `MGR` int(4) default NULL COMMENT '领导编号',
  `HIREDATE` date default NULL COMMENT '雇佣日期',
  `DEPTNO` int(4) default NULL COMMENT '所在部门编号',
  PRIMARY KEY  (`EMPNO`)
) ENGINE=MyISAM AUTO_INCREMENT=7935 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('7369', 'SMITH', 'CLERK', '7902', '1980-12-17', '20');
INSERT INTO `emp` VALUES ('7499', 'ALLEN', 'SALESMAN', '7698', '1981-02-20', '30');
INSERT INTO `emp` VALUES ('7521', 'WARD', 'SALESMAN', '7698', '1981-02-22', '30');
INSERT INTO `emp` VALUES ('7566', 'JONES', 'MANAGER', '7839', '1981-04-02', '20');
INSERT INTO `emp` VALUES ('7654', 'MARTIN', 'SALESMAN', '7698', '1981-09-28', '30');
INSERT INTO `emp` VALUES ('7698', 'BLAKE', 'MANAGER', '7839', '1981-05-01', '30');
INSERT INTO `emp` VALUES ('7782', 'CLARK', 'MANAGER', '7839', '1981-06-09', '10');
INSERT INTO `emp` VALUES ('7839', 'KING', 'PRESIDENT', null, '1981-11-17', '10');
INSERT INTO `emp` VALUES ('7844', 'TURNER', 'SALESMAN', '7698', '1981-09-08', '30');
INSERT INTO `emp` VALUES ('7900', 'JAMES', 'CLERK', '7698', '1981-12-03', '30');
INSERT INTO `emp` VALUES ('7902', 'FORD', 'ANALYST', '7566', '1981-12-03', '20');
INSERT INTO `emp` VALUES ('7934', 'MILLER', 'CLERK', '7782', '1982-01-23', '10');

-- ----------------------------
-- Table structure for `salgrade`
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade` (
  `GRADE` int(11) NOT NULL COMMENT '工资等级',
  `LOSAL` int(11) NOT NULL COMMENT '最低工资',
  `HISAL` int(11) NOT NULL COMMENT '最高工资',
  PRIMARY KEY  (`GRADE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salgrade
-- ----------------------------
INSERT INTO `salgrade` VALUES ('1', '700', '1200');
INSERT INTO `salgrade` VALUES ('2', '1201', '1400');
INSERT INTO `salgrade` VALUES ('3', '1401', '2000');
INSERT INTO `salgrade` VALUES ('4', '2001', '3000');
INSERT INTO `salgrade` VALUES ('5', '3001', '9999');
```


## 添加mybatis依赖

``` xml
<!-- mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.3.1</version>
</dependency>

<!-- mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.3.0</version>
</dependency>

<!-- 用来在spring配置文件中配置数据库相关信息 -->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
```

## spring整合mybatis
添加src/main/resources/spring/spring-mybatis.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-3.2.xsd">
    <!-- 引入jdbc.properties配置文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close">
        <property name="driverClassName" value="${jdbc.driverClassName}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        <!-- 初始化连接大小 -->
        <property name="initialSize" value="${jdbc.initialSize}" />
        <!-- 连接池最大数量 -->
        <property name="maxActive" value="${jdbc.maxActive}" />
        <!-- 连接池最大空闲 -->
        <property name="maxIdle" value="${jdbc.maxIdle}" />
        <!-- 连接池最小空闲 -->
        <property name="minIdle" value="${jdbc.minIdle}" />
        <!-- 获取连接最大等待时间 -->
        <property name="maxWait" value="${jdbc.maxWait}" />
    </bean>
</beans>

```

## import spring-mybatis.xml
在spring.xml文件中导入spring-mybatis.xml
```
<import resource="./spring-mybatis.xml"/>
```

## 检验是否正确
run com.lewjun.SpringJunitTest.testEmpServiceSave()


### 发现异常 Could not resolve placeholder
出现了一个异常：
```
Invalid bean definition with name 'dataSource' defined in class path resource [spring/spring-mybatis.xml]: Could not resolve placeholder 'jdbc.driverClassName' in string value "${jdbc.driverClassName}"
```
分析：这个异常也就是说不能读取到jdbc.properties中的属性，而实际上jdbc.properties的路径是正确的。之前的developer.properties都是正确的没有报错。当我把之前有关
developer相关的信息（Developer.java的使用）全部去掉，这会儿重新run一下，又可以了。说明Spring导入多个独立的 .properties配置文件会出现如上的问题
具体原因：
> Spring容器采用反射扫描的发现机制，在探测到Spring容器中有一个 org.springframework.beans.factory.config.PropertyPlaceholderConfigurer的 Bean就会停止对剩余PropertyPlaceholderConfigurer的扫描（Spring 3.1已经使用PropertySourcesPlaceholderConfigurer替代 PropertyPlaceholderConfigurer了）。
> 换句话说，即Spring容器仅允许最多定义一个PropertyPlaceholderConfigurer(或)，其余的会被Spring忽略掉（其实Spring如果提供一个警告就好了）。
拿上来的例子来说，如果A和B模块是单独运行的，由于Spring容器都只有一个PropertyPlaceholderConfigurer， 因此属性文件会被正常加载并替换掉。如果A和B两模块集成后运行，Spring容器中就有两个 PropertyPlaceholderConfigurer Bean了，这时就看谁先谁后了， 先的保留，后的忽略！因此，只加载到了一个属性文件，因而造成无法正确进行属性替换的问题

### spring3解决Could not resolve placeholder
方案1：将所有的properties的内容合并在一个文件形成一个conf.properties
方案2：将所有的properties文件放在一个目录下，例如conf目录，在使用的时候：`<context:property-placeholder location="classpath:conf/*.properties"/>`

我使用方案2来解决这个问题，只在spring.xml中一次使用`<context:property-placeholder location="classpath:conf/*.properties"/>`就加入了所有的properties文件内容

## 保存一条数据（insert emp）

### 添加EmpMapper

``` java
@Repository
public interface EmpMapper {
    public void insert(Emp emp);
}
```

### 添加EmpService

``` java
public interface EmpService {
    public void insert(Emp emp);
}
```

### 添加EmpServiceImpl

``` java
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    /** 
     * @see com.lewjun.service.EmpService#save(com.lewjun.bean.Emp)
     */
    @Override
    public void insert(Emp emp) {
        System.out.println("insert");
        empMapper.insert(emp);
    }

}

```

### 添加EmpMapper.xml
mybatis/mapper/com.lewjun.mapper/EmpMapper.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.lewjun.mapper.EmpMapper">
    <resultMap id="BaseResultMap" type="com.lewjun.bean.Emp">
        <id column="EMPNO" property="empno" jdbcType="INTEGER" />
        <result column="ENAME" property="ename" jdbcType="VARCHAR" />
        <result column="JOB" property="job" jdbcType="VARCHAR" />
        <result column="MGR" property="mgr" jdbcType="INTEGER" />
        <result column="HIREDATE" property="hiredate" jdbcType="DATE" />
        <result column="DEPTNO" property="deptno" jdbcType="INTEGER" />
    </resultMap>

    <sql id="Base_Column_List">
        EMPNO, ENAME, JOB, MGR, HIREDATE, DEPTNO
    </sql>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap"
        parameterType="java.lang.Integer">
        select
        <include refid="Base_Column_List" />
        from emp
        where EMPNO = #{empno,jdbcType=INTEGER}
    </select>

    <insert id="insert" parameterType="com.lewjun.bean.Emp">
        insert into emp (EMPNO, ENAME, JOB, MGR, HIREDATE, DEPTNO)
        values (#{empno,jdbcType=INTEGER}, #{ename,jdbcType=VARCHAR},
        #{job,jdbcType=VARCHAR}, #{mgr,jdbcType=INTEGER}, #{hiredate,jdbcType=DATE}, #{deptno,jdbcType=INTEGER})
    </insert>
</mapper>
```


### spring和MyBatis完美整合
#### spring加载mapper.xml文件

``` xml
<!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <!-- 自动扫描mapping.xml文件 -->
    <property name="mapperLocations" value="classpath:mybatis/mapper/com.lewjun.mapper/*Mapper.xml"></property>
</bean>
```

#### spring加载mapper.java文件

``` xml
<!-- DAO接口所在包名，Spring会自动查找其下的类 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.lewjun.mapper" />
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
</bean>
```

### run com.lewjun.SpringJunitTest.testEmpServiceInsert()

#### ClassNotFoundException DaoSupport
Caused by: java.lang.ClassNotFoundException: org.springframework.dao.support.DaoSupport

解决方案：添加spring-jdbc依赖

``` xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>
```

再次运行
```
org.mybatis.spring.SqlSessionUtils.getSqlSession(SqlSessionUtils.java:99)  Creating a new SqlSession
org.mybatis.spring.SqlSessionUtils.registerSessionHolder(SqlSessionUtils.java:150)  SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4bf53e] was not registered for synchronization because synchronization is not active
org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:110)  Fetching JDBC Connection from DataSource
org.mybatis.spring.transaction.SpringManagedTransaction.openConnection(SpringManagedTransaction.java:89)  JDBC Connection [jdbc:mysql://192.168.1.189:3306/scott, UserName=root@192.168.1.41, MySQL Connector Java] will not be managed by Spring
org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:145)  ==>  Preparing: insert into emp (EMPNO, ENAME, JOB, MGR, HIREDATE, DEPTNO) values (?, ?, ?, ?, ?, ?) 
org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:145)  ==> Parameters: null, null, null, null, null, null
org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:145)  <==    Updates: 1
org.mybatis.spring.SqlSessionUtils.closeSqlSession(SqlSessionUtils.java:193)  Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4bf53e]
org.springframework.jdbc.datasource.DataSourceUtils.doReleaseConnection(DataSourceUtils.java:327)  Returning JDBC Connection to DataSource
```

通过上面的日志可以看到，emp已经成功保存到数据库里了。









