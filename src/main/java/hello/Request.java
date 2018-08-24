package hello;

public class Request {

    private String orgRepo;
    private String gitToken;
    private String dockerToken;

    public String getDockerToken() {
        return dockerToken;
    }

    public void setDockerToken(String dockerToken) {
        this.dockerToken = dockerToken;
    }

    public String getOrgRepo() {
        return orgRepo;
    }

    public void setOrgRepo(String orgRepo) {
        this.orgRepo = orgRepo;
    }

    public String getGitToken() {
        return gitToken;
    }

    public void setGitToken(String gitToken) {
        this.gitToken = gitToken;
    }

}