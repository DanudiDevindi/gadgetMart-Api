package com.bolton.gadgetmart_main;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	
	 @Bean
	    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
	        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	        servlet.setApplicationContext(applicationContext);
	        servlet.setTransformWsdlLocations(true);
	        return new ServletRegistrationBean(servlet, "/service/*");
	    }

	    @Bean(name = "itemDetailsWsdl")
	    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
	        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	        wsdl11Definition.setPortTypeName("ItemDetailsPort");
	        wsdl11Definition.setLocationUri("/service/item-details");
	        wsdl11Definition.setTargetNamespace("http://localhost:8080/xml/items");
	        wsdl11Definition.setSchema(countriesSchema);
	        return wsdl11Definition;
	    }

	    @Bean
	    public FilterRegistrationBean corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("http://localhost:3000");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        config.addAllowedOriginPattern("http://localhost:3000");
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return bean;
	    }

	    @Bean
	    public XsdSchema countriesSchema() {
	        return new SimpleXsdSchema(new ClassPathResource("item.xsd"));
	    }

}
