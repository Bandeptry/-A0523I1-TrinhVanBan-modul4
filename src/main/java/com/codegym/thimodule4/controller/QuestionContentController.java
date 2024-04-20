package com.codegym.thimodule4.controller;

import com.codegym.thimodule4.model.QuestionContent;
import com.codegym.thimodule4.model.QuestionType;
import com.codegym.thimodule4.service.IQuestionContentService;
import com.codegym.thimodule4.service.IQuestionTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class QuestionContentController {
    @Autowired
    private IQuestionContentService questionContentService;
    @Autowired
    private IQuestionTypeService questionTypeService;
@GetMapping("")
public String showListPage(@RequestParam(value = "nameSearch", defaultValue = "") String name,
                           @RequestParam(value = "page", defaultValue = "0")int page,
                           @RequestParam(value = "selectQuestion", defaultValue = "") String question, Model model){
    if(page < 0){
        page = 0;
    }
    Page<QuestionContent> questionContents = questionContentService.findAllAndSearch(name, PageRequest.of(page, 2));
//    Page<QuestionContent> questionContents1 = questionContentService.findAllSearchTitle(name, question, PageRequest.of(page, 2));
    List<QuestionType> questionTypes = questionTypeService.showList();
    if(questionContents.isEmpty()){
        //xử lí rỗng
    }
    model.addAttribute("nameSearch", name);
    model.addAttribute("selectQuestion",question);
    if(!name.isEmpty()){
        if(!questionContents.isEmpty()){
            model.addAttribute("message", "Danh sách có câu hỏi: "+name);
        }else{
            model.addAttribute("error", name+" Câu hỏi tồn tại trong danh sách");
        }
    }
    if(!question.isEmpty()){
        model.addAttribute("message", "Danh sách có câu hỏi: "+question);
    }
    model.addAttribute("questionContents", questionContents);
//    model.addAttribute("questionContents1", questionContents1);
    model.addAttribute("questionTypes", questionTypes);
    return "list";
}
    @GetMapping("/create")
    public String showPageCreate(Model model){
        QuestionContent questionContent = new QuestionContent();
        model.addAttribute("questionContent", questionContent);
        model.addAttribute("questionTypeService", questionTypeService.showList());
        return "create";
    }
    @PostMapping("/create")
    public ModelAndView createContent(@Valid @ModelAttribute QuestionContent questionContent,
                                      BindingResult bindingResult, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if (bindingResult.hasErrors()){
            modelAndView = new ModelAndView("/create", "questionTypeService", questionTypeService.showList());
            modelAndView.getModelMap().addAttribute("message", "Thông tin không chính xác ‼️");
            return modelAndView;
        }
        questionContentService.create(questionContent);
        attributes.addFlashAttribute("message", "Thêm mới thành công ✅");
        return modelAndView;
    }
    @GetMapping("delete")
    public String deleteById(@RequestParam("id") Integer id, RedirectAttributes attributes){
        questionContentService.removeById(id);
        attributes.addFlashAttribute("message", "Xóa thành công ✅");
        return "redirect:/";
    }
    @GetMapping ("{id}/detail")
    public String detailById(@PathVariable(name = "id") Integer id, Model model){
        QuestionContent questionContent = questionContentService.detail(id);
        model.addAttribute("questionContent", questionContent);
        return "detail";
    }
    @GetMapping("{id}/update")
    public String showPageUpdate(@PathVariable(name = "id") Integer id, Model model){
        QuestionContent questionContent = questionContentService.detail(id);
        List<QuestionType> questionTypes = questionTypeService.showList();
        model.addAttribute("id", id);
        model.addAttribute("questionContent", questionContent);
        model.addAttribute("questionTypeService", questionTypes);
        return "update";
    }
    @PostMapping("/update")
    public String updateContent(@Validated @ModelAttribute QuestionContent questionContent,
                                BindingResult bindingResult, RedirectAttributes attributes, Model model){
        if(bindingResult.hasErrors()){
            List<QuestionType> questionTypes = questionTypeService.showList();
            model.addAttribute("questionTypeService", questionTypes);
            model.addAttribute("message", "Thông tin không chính xác ‼️");
            return "/update";
        }
        questionContentService.create(questionContent);
        attributes.addFlashAttribute("message", "Sửa thông tin thành công ✅");
        return "redirect:/";
    }
}
