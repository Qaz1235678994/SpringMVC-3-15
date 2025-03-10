package core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import core.viewresolver.JsonViewResolver;

// 輔助使用實作介面的WebMvcConfigurer

@Configuration
// 啟動(對映XML的<mvc:annotation-driven />)
@EnableWebMvc
// 映射(對映XML的<context:component-scanbase-package="web.*.controller" />)
@ComponentScan("core.exception, web.*.controller")
public class SpringMvcConfig implements WebMvcConfigurer {

	// mapping bean
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		// 實力化一個 InternalResourceViewResolver ， 然後做set設定
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setContentType("text/html;charset=UTF-8");
//		registry.viewResolver(viewResolver);
//
//	}

	// 掛載讓他在網址顯示的不同 use static DefaultServlet
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/**")
				.addResourceLocations("/WEB-INF/");
	}

	// 實作11-2 檔案傳輸(限制檔案上限、單檔上限大小)
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSizePerFile(1000 * 1024 * 1024);
		resolver.setMaxUploadSize(9L * 1024 * 1024 * 1024);
		return resolver;
	}

	// 實作13-2 JSON專⽤的訊息轉換器
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setPrettyPrint(true);
		converters.add(messageConverter);
	}

	// 實作 15-6預設⾸⾴
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry
				.addViewController("/")
				.setViewName("index.html");

	}
}
