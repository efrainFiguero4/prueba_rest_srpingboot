package com.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class PruebaApplication {
	
	@Component
    @Order(Ordered.HIGHEST_PRECEDENCE)

    public class SimpleCORSFilter implements Filter {

        @Override
        public void init(FilterConfig fc) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpServletRequest request = (HttpServletRequest) req;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, resp);
            }
        }

        @Override
        public void destroy() {
        }

    }

	public static void main(String[] args) {

		SpringApplication.run(PruebaApplication.class, args);

	}
}
