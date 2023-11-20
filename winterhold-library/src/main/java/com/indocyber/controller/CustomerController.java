package com.indocyber.controller;


import com.indocyber.dto.author.UpsertAuthorDto;
import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.dto.customer.UpsertCustomerDto;
import com.indocyber.entity.Author;
import com.indocyber.entity.Customer;
import com.indocyber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        // StringTrimmerEditor whitespace - leading and trailing
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true means trim to null

        //Preprocess every String form data
        // remove leading and trailing whitespace
        // If String only has whitespace, trim it to null
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("indexCustomer")
    public String listCustomer(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "") String name,
                               Model model){
        List<CustomerGridDto> customerGridDtoList = customerService.getCustomerGrid(page);
        long totalPages = customerService.getTotalPages();
        List<Customer> customerList = customerService.findAllCustomer();

        model.addAttribute("customerList", customerList);
        model.addAttribute("grid", customerGridDtoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);

        return "customer/index-customer";
    }

    @GetMapping("customerForm")
    public String customerForm(@RequestParam(value = "customerId", required = false) String id, Model model){
        if(id != null){
            UpsertCustomerDto dto = customerService.getUpdateCustomer(id);
            model.addAttribute("customer", dto);
            return "customer/form-customer";
        } else {
            UpsertCustomerDto dto = new UpsertCustomerDto();
            model.addAttribute("customer", dto);
        }
        return "customer/form-customer";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("customer") UpsertCustomerDto dto,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "customer/form-customer";
        } else {
            System.out.println(dto.getMembershipNumber());
            customerService.saveCustomer(dto);
            return "redirect:/customers/indexCustomer";
        }
    }

    @GetMapping("deleteCustomer")
    public String deleteCustomer(@RequestParam(value = "customerId", required = true) String id, Model model){
        customerService.deleteCustomer(id);
        return "redirect:/customers/indexCustomer";
    }

    @GetMapping("/extend")
    public String extendCustomer(@RequestParam(value = "customerId", required = true) String id, Model model){

        customerService.saveCustomerExtend(id);
        return "redirect:/customers/indexCustomer";
    }
}
