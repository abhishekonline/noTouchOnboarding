package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


@Controller
public class RequestController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("request", new Request());
        return "request";
    }

    @GetMapping("/getstatus")
    public String getStatus(Model model) {
        model.addAttribute("status", new Status());
        return "getStatus";
    }

    @RequestMapping(value = "/requestJenkins")
    String getIdByValue(@RequestParam("org") String orgRepo,
                        @RequestParam("ciname") String uniquename,
                        @RequestParam("gitToken") String gitToken,
                        @RequestParam(value = "default", required = false) String dockerToken,
                        Model model){
        String url = requestJenkins(orgRepo,uniquename,gitToken,dockerToken);
        model.addAttribute("url", url);

        System.out.println("url is "+url);
        return "jenkins";
    }

    @RequestMapping(value = "/status")
    String getIdByValue(@RequestParam("ciname") String url, Model model){
        String status = getStatus(url);
        model.addAttribute("status", status);
        model.addAttribute("url", url);
        System.out.println("url is "+url + " and its status is " +status);
        return "status";
    }


    public String getStatus(String org){
        try{

            String[] command = { "bash","src/main/resources/scripts/statusScript.sh"};
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            process.waitFor();
            int exitStatus = process.exitValue();

            //Script status non zero, means it is processing
            if(exitStatus != 0){
                return "status is pending";
            }else{
            //Script status  zero, means it will spit jenkins url
                String s;
                while ((s = reader.readLine()) != null) {
                    System.out.println("Script output: " + s);
                    return s;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //calculate status
        return "pending";
    }

    public String requestJenkins(String org, String uniquename, String gitToken, String dockerToken){
        System.out.println(org+"\n"+uniquename+"\n"+gitToken+"\n"+ dockerToken);
        try{

            String[] command = { "bash","src/main/resources/scripts/requestScript.sh"};
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println("Script output: " + s);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        //request url
        return "Request placed";
    }
}