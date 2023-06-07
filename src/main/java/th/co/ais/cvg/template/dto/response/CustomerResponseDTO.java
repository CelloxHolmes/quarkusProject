package th.co.ais.cvg.template.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;

    // Constructors, getters, and setters

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
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
}