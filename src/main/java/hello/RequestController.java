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
                        @RequestParam("gitUser") String gitUser,
                        @RequestParam("gitToken") String gitToken,
                        @RequestParam(value = "default", required = false) String dockerToken,
                        Model model){
        String url = requestJenkins(orgRepo,uniquename,gitUser,gitToken,dockerToken);
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


    public String getStatus(String uniquename){
        System.out.println("Status requested for CI"+uniquename+"\n");

        try{

            String[] command = { "bash","src/main/resources/scripts/statusScript.sh"};
            String[] envp = new String[1];

            envp[0] = "CI_NAME=" + uniquename;

            Process process = Runtime.getRuntime().exec(command, envp);
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

    public String requestJenkins(String org, String uniquename, String gitUser, String gitToken, String dockerToken){
        System.out.println("SFCI Onboarding requested for "+org+"\n"+uniquename+"\n"+gitUser+"\n"+gitToken+"\n"+ dockerToken);
        try{

            String[] command = { "bash","src/main/resources/scripts/requestScript.sh"};
            String[] envp = new String[4];

            envp[0] = "CI_NAME=" + uniquename;
            envp[1] = "GIT_ORG=" + org;
            envp[2] = "GIT_USER=" + gitUser;
            envp[3] = "GIT_TOKEN=" + gitToken;

            Process process = Runtime.getRuntime().exec(command, envp);
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
