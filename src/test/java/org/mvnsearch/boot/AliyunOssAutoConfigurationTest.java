package org.mvnsearch.boot;

import com.aliyun.oss.OSSClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mvnsearch.boot.oss.AliyunOssAutoConfiguration;
import org.mvnsearch.boot.oss.AliyunOssProperties;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss auto configuration test
 *
 * @author linux_china
 */
@Configuration
public class AliyunOssAutoConfigurationTest {
    private AnnotationConfigApplicationContext context;

    @After
    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Bean
    public AliyunOssProperties properties() {
        AliyunOssProperties properties = new AliyunOssProperties();
        properties.setKey("xxx");
        properties.setSecret("xxx");
        return properties;
    }

    @Test
    public void clientExists() {
        this.context = new AnnotationConfigApplicationContext(
                AliyunOssAutoConfigurationTest.class, AliyunOssAutoConfiguration.class);
        Assert.assertEquals(1, this.context.getBeanNamesForType(OSSClient.class).length);
    }

}
