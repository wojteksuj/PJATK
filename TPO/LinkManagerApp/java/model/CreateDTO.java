import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.s31087tpo10.constraint.CorrectPassword;
import org.example.s31087tpo10.constraint.UniqueUrl;

public class CreateDTO {
    @NotNull
    @Size(min = 5, max = 20, message = "{name.size}")
    private String name;
    @org.hibernate.validator.constraints.URL(message = "{url.invalid}")
    @Pattern(regexp = "^https://.+", message = "{https.invalid}")
    @UniqueUrl
    private String targetUrl;
    @CorrectPassword
    private String password;

    public CreateDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
