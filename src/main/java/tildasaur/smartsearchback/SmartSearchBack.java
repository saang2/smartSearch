/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tildasaur.smartsearchback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tildasaur.smartsearchback.controller.MainController;

/**
 *
 * @author timof
 */
@SpringBootApplication()
@ComponentScan(basePackageClasses = MainController.class)
public class SmartSearchBack {

    public static void main(String[] args) {
        SpringApplication.run(SmartSearchBack.class, args);
    }
}
