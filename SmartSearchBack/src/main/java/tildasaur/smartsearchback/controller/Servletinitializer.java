/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tildasaur.smartsearchback.controller;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tildasaur.smartsearchback.SmartSearchBack;

/**
 *
 * @author timof
 */
public class Servletinitializer extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return application.sources(SmartSearchBack.class);
    }
}
