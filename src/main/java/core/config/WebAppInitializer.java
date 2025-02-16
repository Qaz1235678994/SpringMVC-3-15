package core.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 實作15章 部屬描述類別
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 部屬描述檔 15-2
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// 匿名宣告一個class物件
		return new Class<?>[] { SpringConfig.class };
	}

	// 部屬描述檔 15-3
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { SpringMvcConfig.class };
	}

	// 部屬描述檔 15-4
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 部屬描述檔 15-5 15-6  註冊過濾器&設定UTF-8格式
	// <REQUEST-CHARACTER-ENCODING>UTF-8</REQUEST-CHARACTER-ENCODING>
	// <RESPONSE-CHARACTER-ENCODING>UTF-8</RESPONSE-CHARACTER-ENCODING>
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	     characterEncodingFilter.setEncoding("UTF-8");
	     
	     OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
	     return new Filter[] { characterEncodingFilter, openSessionInViewFilter };
	}
	
	// 部屬描述檔 檔案上傳設定 <multipart-config />
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
