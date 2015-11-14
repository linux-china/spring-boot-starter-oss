package org.mvnsearch.boot.oss;

import com.aliyun.oss.OSSClient;
import org.mvnsearch.boot.oss.impl.FileStorageServiceOssImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss auto configuration
 *
 * @author linux_china
 */
@Configuration
@ConditionalOnClass(OSSClient.class)
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunOssAutoConfiguration {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private AliyunOssProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public OSSClient ossClient() {
        return new OSSClient(properties.getKey(), properties.getSecret());
    }

    @Bean
    @ConditionalOnMissingBean
    public FileStorageService fileStorageService() {
        FileStorageServiceOssImpl fileStorageServiceOss = new FileStorageServiceOssImpl();
        fileStorageServiceOss.setAccessKey(properties.getKey());
        fileStorageServiceOss.setAccessSecret(properties.getSecret());
        fileStorageServiceOss.setBucketName(properties.getBucket());
        return fileStorageServiceOss;
    }

    @Bean
    public AliyunOssHealthIndicator aliyunOssHealthIndicator() {
        return new AliyunOssHealthIndicator();
    }

    @Bean
    public AliyunOssMetrics aliyunOssMetrics() {
        return new AliyunOssMetrics();
    }

    @Bean
    public AliyunOssEndpoint aliyunOssEndpoint() {
        return new AliyunOssEndpoint();
    }
}
