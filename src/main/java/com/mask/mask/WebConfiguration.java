package com.mask.mask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
//@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Value("${document-customer}")
    String documentCustomer;

    @Value("${document-claim}")
    String documentClaim;

    @Value("${document-user}")
    String userProfile;

    @Value("${document-policy}")
    String documentPolicy;

    @Value("${document-wording}")
    String documentWording;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/document-customer/**")
                .addResourceLocations("file:"+documentCustomer);
        registry.addResourceHandler("/document-claim/**")
                .addResourceLocations("file:"+documentClaim);
        registry.addResourceHandler("/user-profile/**")
                .addResourceLocations("file:"+userProfile);
        registry.addResourceHandler("/document-policy/**")
                .addResourceLocations("file:"+documentPolicy);
        registry.addResourceHandler("/document-wording/**")
                .addResourceLocations("file:"+documentWording);
        super.addResourceHandlers(registry);
    }
}
