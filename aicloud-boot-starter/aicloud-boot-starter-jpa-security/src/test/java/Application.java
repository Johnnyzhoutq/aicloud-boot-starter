

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

       return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/403.html");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/403.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/403.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
       });
    }*/
	/**   
	* @Title: Application.java  
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author ZhouTianQi   
	* @date 2017年1月2日 下午3:49:01 
	* @version V1.0   
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
