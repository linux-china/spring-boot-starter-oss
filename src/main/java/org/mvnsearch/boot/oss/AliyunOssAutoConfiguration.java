package org.mvnsearch.boot.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
    @Autowired
    private AliyunOssProperties properties;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(properties.getKey(), properties.getSecret());
    }
}
