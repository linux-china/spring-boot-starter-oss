package org.mvnsearch.boot;

import com.aliyun.oss.OSSClient;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mvnsearch.boot.oss.AliyunOssAutoConfiguration;
import org.mvnsearch.boot.oss.AliyunOssProperties;
import org.mvnsearch.boot.oss.ByteArrayDataSource;
import org.mvnsearch.boot.oss.FileStorageService;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.activation.DataSource;

/**
 * aliyun oss auto configuration test
 *
 * @author linux_china
 */
@Configuration
public class AliyunOssAutoConfigurationTest {
    private AnnotationConfigApplicationContext context;

    @Before
    public void setUp() {
        this.context = new AnnotationConfigApplicationContext(
                AliyunOssAutoConfigurationTest.class, AliyunOssAutoConfiguration.class);
    }

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
        properties.setSecret("xxxx");
        properties.setBucket("xxx-dev");
        return properties;
    }

    @Test
    public void clientExists() {
        Assert.assertEquals(1, this.context.getBeanNamesForType(OSSClient.class).length);
        Assert.assertEquals(1, this.context.getBeanNamesForType(FileStorageService.class).length);
    }

    @Test
    public void testSave() throws Exception {
        byte[] content = IOUtils.toByteArray(this.getClass().getResourceAsStream("/1.jpg"));
        ByteArrayDataSource bads = new ByteArrayDataSource(content, "image/jpeg");
        bads.setName("1.jpg");
        FileStorageService fileStorageService = context.getBean(FileStorageService.class);
        String newFileName = fileStorageService.save(bads);
        DataSource fileDS = fileStorageService.get(newFileName);
        Assert.assertNotNull(fileDS);
        fileStorageService.delete(newFileName);
    }

}
