package th.co.ais.cvg.template.controller;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import th.co.ais.cvg.template.adapter.db.entity.Customer;
import th.co.ais.cvg.template.dto.request.CustomerRequestDTO;
import th.co.ais.cvg.template.dto.response.CustomerResponseDTO;
import th.co.ais.cvg.template.service.CustomerService;

import java.util.List;

@ApplicationScoped
@Path("/v1/")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @Transactional
    @POST
    @Path("customers")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response createCustomer(@Context HttpHeaders headers, CustomerRequestDTO customerDto) throws Exception {
        Customer customer = new Customer(customerDto.getName(), customerDto.getEmail());
        Log.info("POST request received for creating a customer");
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerResponseDTO responseDto = new CustomerResponseDTO(createdCustomer.getId(), createdCustomer.getName(), createdCustomer.getEmail());
        return Response.ok(responseDto).build();
    }

    @Transactional
    @GET
    @Path("customers")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllCustomers() {
        Log.info("GET request received for fetching all customers");
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getCustomerById(@PathParam("id") String id) {
        Log.info("GET request received for fetching customer with ID: " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            CustomerResponseDTO responseDto = new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getEmail());
            return Response.ok(responseDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PATCH
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateCustomer(@PathParam("id") String id, CustomerRequestDTO customerDto) {
        Customer customer = new Customer(customerDto.getName(), customerDto.getEmail());
        Log.info("PATCH request received for updating customer with ID: " + id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            CustomerResponseDTO responseDto = new CustomerResponseDTO(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getEmail());
            return Response.ok(responseDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response editCustomer(@PathParam("id") String id, CustomerRequestDTO customerDto) {
        Customer customer = new Customer(customerDto.getName(), customerDto.getEmail());
        Log.info("PUT request received for updating customer with ID: " + id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            CustomerResponseDTO responseDto = new CustomerResponseDTO(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getEmail());
            return Response.ok(responseDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deleteCustomer(@PathParam("id") String id) {
        Log.info("DELETE request received for deleting customer with ID: " + id);
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
