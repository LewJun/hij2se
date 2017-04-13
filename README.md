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


## 