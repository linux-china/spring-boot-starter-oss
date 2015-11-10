spring-boot-start-oss
===================================
Spring boot with aliyun oss support

### usage
in pom.xml add following dependency:

      <dependency>
           <groupId>org.mvnsearch.boot</groupId>
           <artifactId>spring-boot-starter-oss</artifactId>
           <version>1.0.0-SNAPSHOT</version>
      </dependency>
      
in application.properties file, please add following keys:

     spring.oss.key=xxxx
     spring.oss.secret=yyyy
     spring.oss.bucket=xxx-dev
     
in your code you can use OssClient directly:

     @Autowired
     OSSClient ossClient;

you can use FileStorageService API to operate file:

     @Autowired
     FileStorageService fileStorageService;

### Spring XML Configuration
If you want to setup bean in Spring xml file, please use following code:

     <bean id="ossClient" class="com.aliyun.oss.OSSClient">
          <constructor-arg value="access_key"/>
          <constructor-arg value="access_secret"/>
     </bean>
          
     <bean id="fileStorageService" class="org.mvnsearch.boot.oss.impl.FileStorageServiceOssImpl">
          <property name="accessKey" value="access_key"/>
          <property name="accessSecret" value="access_key"/>
          <property name="bucketName" value="bucket_name"/>
     </bean>
 