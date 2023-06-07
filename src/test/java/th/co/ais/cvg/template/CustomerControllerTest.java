package th.co.ais.cvg.template;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import th.co.ais.cvg.template.adapter.db.entity.Customer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CustomerControllerTest {

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("mock@gmail.com");
        customer.setName("mockMan");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer)
                .when()
                .post("/v1/customers")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("email", equalTo("mock@gmail.com"))
                .body("name", equalTo("mockMan"));
    }

    @Test
    public void testGetAllCustomers() {
        given()
                .when()
                .get("/v1/customers")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        // Add additional assertions as needed
    }

    @Test
    public void testGetCustomerById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/v1/customers/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        // Add additional assertions as needed
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("updated@gmail.com");
        customer.setName("updatedName");

        given()
                .pathParam("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer)
                .when()
                .patch("/v1/customers/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("email", equalTo("updated@gmail.com"))
                .body("name", equalTo("updatedName"));
    }

    @Test
    public void testEditCustomer() {
        Customer customer = new Customer();
        customer.setEmail("edited@gmail.com");
        customer.setName("editedName");

        given()
                .pathParam("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer)
                .when()
                .put("/v1/customers/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("email", equalTo("edited@gmail.com"))
                .body("name", equalTo("editedName"));
    }

    @Test
    public void testDeleteCustomer() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/v1/customers/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
