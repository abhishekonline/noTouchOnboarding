package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("request", new Request());
        return "request";
    }


    @RequestMapping(value = "/requestJenkins")
    String getIdByValue(@RequestParam("orgRepo") String orgRepo,
                        @RequestParam("gitToken") String gitToken,
                        @RequestParam("dockerToken") String dockerToken,
                        Model model){
        String url = requestJenkins(orgRepo,gitToken,dockerToken);
        model.addAttribute("url", url);

        System.out.println("url is "+url);
        //return getStatus(url);
        return "jenkins";
    }

    @RequestMapping(value = "/status")
    String getIdByValue(@RequestParam("url") String url, Model model){
        String status = getStatus(url);
        model.addAttribute("status", status);
        model.addAttribute("url", url);
        System.out.println("url is "+url + " and its status is " +status);
        //return getStatus(url);
        return "status";
    }

    public String getStatus(String url){

        //calculate status
        return "pending";
    }

    public String requestJenkins(String orgRepo, String gitToken, String dockerToken){
        System.out.println(orgRepo+"\n"+gitToken+"\n"+ dockerToken);
        //request url
        return "fakeJenkinsUrl";
    }
}