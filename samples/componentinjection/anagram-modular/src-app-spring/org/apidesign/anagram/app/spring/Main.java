package org.apidesign.anagram.app.spring;

import org.apidesign.anagram.api.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Main {
    private Main() { }
    
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("org/apidesign/anagram/app/spring/conf.xml");
        
        UI ui = (UI)context.getBean("ui", UI.class);
        ui.display();
    }
    
    
}
