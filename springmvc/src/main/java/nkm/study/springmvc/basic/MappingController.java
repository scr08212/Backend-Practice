package nkm.study.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @GetMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "helloBasic";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("mappingPath={}", userId);
        return "mappingPath";
    }

    @GetMapping(value = "/mapping-header", headers = {"mode=debug"})
    public String mappingHeader(){
        log.info("mappingHeader");
        return "mappingHeader";
    }

    @PostMapping(value="/mapping-consume", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume(){
        log.info("mappingConsume");
        return "mappingConsume";
    }

    @PostMapping(value="/mapping-produces", produces= MediaType.TEXT_HTML_VALUE)
    public String MappingProduces(){
        log.info("mappingProduces");
        return "mappingProduces";
    }
}
