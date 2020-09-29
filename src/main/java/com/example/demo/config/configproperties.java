package com.example.demo.config;


import org.apache.ibatis.javassist.ClassPath;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


//读取配置properties的属性，标签是下面2个，必须要用component，代表容器内才生效，prefix
//代表是读取那个标签

//在controler里面调用写法是
//@Autowired
//public configproperties cfyp;
//System.out.println("输出配置文件："+cfyp.getFirstname());
//2

@PropertySource(value={"classpath:myconfig.properties"})
@Component
@ConfigurationProperties(prefix = "person")
public class configproperties {
    public String firstname;
    public String lastname;
    public String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
