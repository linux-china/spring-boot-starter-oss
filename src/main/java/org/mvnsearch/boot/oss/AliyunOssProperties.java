package org.mvnsearch.boot.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aliyun oss properties
 *
 * @author linux_china
 */
@ConfigurationProperties(
        prefix = "spring.oss"
)
public class AliyunOssProperties {
    private String key;
    private String secret;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
