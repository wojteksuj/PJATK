package org.example.s31087tpo10.model;

public class ResponseDTO {
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private int visits;

    public ResponseDTO(String id, String name, String targetUrl, String redirectUrl, int visits) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
        this.redirectUrl = redirectUrl;
        this.visits = visits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public int getVisits() {
        return visits;
    }
}
