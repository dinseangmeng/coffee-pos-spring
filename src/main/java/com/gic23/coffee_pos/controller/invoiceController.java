package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.category;
import com.gic23.coffee_pos.entity.drink_food;
import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.dto.CategoryWithDrinkCount;
import com.gic23.coffee_pos.service.implement.categoryServiceImp;
import com.gic23.coffee_pos.service.implement.drinkFoodServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceServiceImp;

import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/invoice")
@Slf4j
public class invoiceController {

    private final invoiceServiceImp invoiceService;
    private final drinkFoodServiceImp drinkFoodService;
    private final categoryServiceImp categoryService;

    @Autowired
    public invoiceController(invoiceServiceImp invoiceService, drinkFoodServiceImp drinkFoodService,
            categoryServiceImp categoryService) {
        this.invoiceService = invoiceService;
        this.drinkFoodService = drinkFoodService;
        this.categoryService = categoryService;
    }

    @GetMapping("/view/{invoiceCode}")
    public String invoiceView(@PathVariable String invoiceCode, Model model) {
        invoice invoice = invoiceService.getByInvoiceCode(invoiceCode);
        List<drink_food> drinkFoods = drinkFoodService.list();
        List<CategoryWithDrinkCount> categorys = categoryService.list();
        Long countProduct = drinkFoodService.countAll();
        // log.info("Obj {}", categorys);
        model.addAttribute("categoryCode", "");
        model.addAttribute("n_product", countProduct);
        model.addAttribute("invoiceCode", invoiceCode);
        model.addAttribute("invoice", invoice);
        model.addAttribute("isProductEmpty", drinkFoods.size() > 0 ? false : true);
        model.addAttribute("products", drinkFoods);
        model.addAttribute("TextCategory", "All");
        model.addAttribute("categorys", categorys);
        return "main/invoice/index";
    }

    @GetMapping("/view/{invoiceCode}/{categoryCode}")
    public String invoiceView(@PathVariable String invoiceCode, @PathVariable String categoryCode, Model model) {
        invoice invoice = invoiceService.getByInvoiceCode(invoiceCode);
        List<drink_food> drinkFoods = drinkFoodService.findByCategoryId(categoryCode);
        List<CategoryWithDrinkCount> categorys = categoryService.list();
        Long countProduct = drinkFoodService.countAll();
        category category = categoryService.getByCode(categoryCode);
        // log.info("Obj {}", categorys);

        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("n_product", countProduct);
        model.addAttribute("invoiceCode", invoiceCode);
        model.addAttribute("invoice", invoice);
        model.addAttribute("isProductEmpty", drinkFoods.size() > 0 ? false : true);
        model.addAttribute("products", drinkFoods);
        model.addAttribute("TextCategory", category != null ? category.getName() : "");
        model.addAttribute("categorys", categorys);
        return "main/invoice/index";
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<invoice> listInvoice() {
        return ResponseEntity.ok().body(invoiceService.getByInvoiceCode("ORD000002"));
    }

    @GetMapping("/{invoiceCode}")
    @ResponseBody
    public ResponseEntity<invoice> getInvoiceById(@PathVariable String invoiceCode) {
        log.info(invoiceCode);
        return ResponseEntity.ok().body(invoiceService.getByInvoiceCode(invoiceCode));
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<Integer> newOrder(@RequestParam("table") Integer table) {

        return ResponseEntity.ok().body(table);
    }

}
