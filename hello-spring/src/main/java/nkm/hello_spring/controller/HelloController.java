package nkm.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String HelloMVC(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody // API
    public Human HelloAPI(@RequestParam(value = "name") String name){
        Human human = new Human();
        human.setName(name);
        return human;
    }

    public static class Human{
        private String name;
        public Human(){
        }
        public Human(String name){}

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
    }
}
