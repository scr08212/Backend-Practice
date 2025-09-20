package nkm.study.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data", "Hello World");

        return modelAndView;
    }

    @RequestMapping("/response-view-v1")
    public String responseViewV2(Model model){
        model.addAttribute("data", "Hello World");

        return "response/hello";
    }

    // not recommended
    @RequestMapping("response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "Hello World");
    }
}
