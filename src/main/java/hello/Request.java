package hello;

public class Request {

    private String org;
    private String gitToken;
    private String dockerToken;

    public String getDockerToken() {
        return dockerToken;
    }

    public void setDockerToken(String dockerToken) {
        this.dockerToken = dockerToken;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String orgRepo) {
        this.org = org;
    }

    public String getGitToken() {
        return gitToken;
    }

    public void setGitToken(String gitToken) {
        this.gitToken = gitToken;
    }

}