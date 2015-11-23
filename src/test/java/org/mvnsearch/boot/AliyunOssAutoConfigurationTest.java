package org.mvnsearch.boot;

import com.aliyun.oss.OSSClient;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvnsearch.boot.oss.AliyunOssAutoConfiguration;
import org.mvnsearch.boot.oss.AliyunOssProperties;
import org.mvnsearch.boot.oss.ByteArrayDataSource;
import org.mvnsearch.boot.oss.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.DataSource;

/**
 * aliyun oss auto configuration test
 *
 * @author linux_china
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OssTestApplication.class)
public class AliyunOssAutoConfigurationTest {
    @Autowired
    private ApplicationContext context;

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
