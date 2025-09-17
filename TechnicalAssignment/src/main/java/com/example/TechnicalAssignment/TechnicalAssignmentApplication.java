package com.example.TechnicalAssignment;

import com.example.TechnicalAssignment.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TechnicalAssignmentApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TechnicalAssignmentApplication.class, args);
        //this will create a box where objects are already created
        OrderService service= context.getBean(OrderService.class);
        service.show();
	}

}
