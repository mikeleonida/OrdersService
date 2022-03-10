package com.appsdeveloperblog.estore.OrdersService;

import org.axonframework.config.Configuration;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

@EnableDiscoveryClient
@SpringBootApplication
public class OrdersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceApplication.class, args);
	}
	
	@Bean
	public DeadlineManager deadlineManager(Configuration configuration, 
			SpringTransactionManager transactionManager) {
	
		return SimpleDeadlineManager.builder()
				.scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                .transactionManager(transactionManager)
                .build();
	}

//	@Bean
//    XStream xstream(){
//        XStream xstream = new XStream();
//        // clear out existing permissions and set own ones
//        xstream.addPermission(NoTypePermission.NONE);
//        // allow any type from the same package
//        xstream.allowTypesByWildcard(new String[] {
//                "com.appsdeveloperblog.estore.core.**",
//                "com.appsdeveloperblog.estore.OrdersService.**",
//                "com.eventdriven.estore.**",
//                "org.axonframework.**",
//                "java.**",
//                "com.thoughtworks.xstream.**"
//        });
//
//        return xstream;
//    }

//    @Bean
//    @Primary
//    public Serializer serializer(XStream xStream) {
//        return XStreamSerializer.builder().xStream(xStream).build();
//    }
}
