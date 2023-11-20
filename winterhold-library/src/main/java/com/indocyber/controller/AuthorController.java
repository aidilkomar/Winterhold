package com.indocyber.controller;

import com.indocyber.dto.author.AuthorGridDto;
import com.indocyber.dto.author.UpsertAuthorDto;
import com.indocyber.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
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

    @GetMapping
    public String homePage(){
        return "/home";
    }

    @GetMapping("/indexAuthor")
    public String listAuthor(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "") String name,
                             Model model) {

        if(name == null){
            name = "";
        }

        List<AuthorGridDto> authorGridDtoList = authorService.getAuthorGrid(page, name);
        long totalPages = authorService.getTotalPages();

        model.addAttribute("grid", authorGridDtoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);
        return "/author/index-author";
    }

    @GetMapping("/authorForm")
    public String authorForm(@RequestParam(value = "authorId", required = false) Integer id, Model model){
        if(id != null){
            UpsertAuthorDto dto = authorService.getUpdateAuthor(id);
            System.out.println(dto);
            model.addAttribute("author", dto);
            return "author/form-author";
        } else {
            UpsertAuthorDto dto = new UpsertAuthorDto();
            model.addAttribute("author", dto);
        }
        return "author/form-author";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("author") UpsertAuthorDto dto,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "author/form-author";
        } else {
            authorService.saveAuthor(dto);
            return "redirect:/author/indexAuthor";
        }
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam(value = "authorId", required = true) int id, Model model){
        authorService.deleteAuthor(id);
        return "redirect:/author/indexAuthor";
    }
}
