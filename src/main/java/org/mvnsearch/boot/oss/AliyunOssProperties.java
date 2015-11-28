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
    /**
     * aliyun access key
     */
    private String key;
    /**
     * aliyun access secret
     */
    private String secret;
    /**
     * default bucket
     */
    private String bucket;
    /**
     * endpoint
     */
    private String endpoint = "http://oss.aliyuncs.com";

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
