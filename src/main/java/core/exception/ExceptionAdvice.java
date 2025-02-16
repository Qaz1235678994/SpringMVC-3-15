package core.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 實作18-5 例外處理類別

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger logger = LogManager.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String handlException(Exception e) {
		logger.error(e.getMessage(), e);
		return "WEB-INF/error.jsp";
	}
}
