package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//读取配置yml的属性，标签是下面2个，必须要用component，代表容器内才生效，prefix
//代表是读取那个标签

//在controler里面调用写法是
//@Autowired
//public configyml cfyml;
//System.out.println("输出配置文件："+cfyml.getPort());

@Component
@ConfigurationProperties(prefix = "http")
public class configyml {
    public String datasource;

    public String port;

    public String name;
    public String force;

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
}
