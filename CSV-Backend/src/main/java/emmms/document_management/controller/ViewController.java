package emmms.document_management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = {"/", "/files"})
    public String index() {
        return "forward:/index.html";
    }
}