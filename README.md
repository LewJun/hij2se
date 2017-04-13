# hij2se
搭建基于maven的j2se项目

[TOC]

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
```xml
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
```
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

```
<!-- 自动扫描(自动注入)，扫描com.lewjun.service这个包以及它的子包的所有使用@Service注解标注的类 -->
<context:component-scan base-package="com.lewjun.service" />

<!-- 自动扫描(自动注入)，扫描com.lewjun这个包以及它的子包的所有使用@Service, @Repository注解标注的类 -->
    <context:component-scan base-package="com.lewjun" />
    
<!-- 自动扫描(自动注入)，扫描com.lewjun.service, com.lewjun.dao这个包以及它的子包的所有使用@Service, @Repository注解标注的类 -->
<context:component-scan base-package="com.lewjun.service, com.lewjun.dao" />

EmpService empService = context.getBean(EmpService.class);
LOGGER.info("{}",empService);
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpDao empDao;
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
```
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
```
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    Developer developer;
}
```









