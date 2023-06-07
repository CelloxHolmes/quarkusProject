package th.co.ais.cvg.template.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    private String email;
    private String name;

    // Constructors, getters, and setters

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}