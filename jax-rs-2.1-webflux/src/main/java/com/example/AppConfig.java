package com.example;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.reactor.server.ReactorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
public class AppConfig {
    @Bean
    public Server rsServer(Bus bus, PeopleRestService service) {
        final JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.getProperties(true).put("useStreamingSubscriber", true);
        bean.setBus(bus);
        bean.setAddress("/");
        bean.setServiceBean(service);
        bean.setProvider(new JacksonJsonProvider());
        new ReactorCustomizer().customize(bean);
        return bean.create();
    }
}
