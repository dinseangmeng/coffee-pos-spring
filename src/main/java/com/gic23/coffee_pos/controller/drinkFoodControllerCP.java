package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.drink_food;
import com.gic23.coffee_pos.entity.invoice_detail;
import com.gic23.coffee_pos.service.implement.drinkFoodServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceDetailServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/cp/drinkFood")
@Slf4j
public class drinkFoodControllerCP {

    @Autowired
    private drinkFoodServiceImp drinkFoodService;
    @Autowired
    private invoiceDetailServiceImp invoiceDetailService;

    @GetMapping("/view/{typeId}")
    public String ListProductView(@PathVariable Integer typeId, Model model) {
        List<drink_food> products = drinkFoodService.findBytypeId(typeId);
        model.addAttribute("typeId", typeId);
        model.addAttribute("products", products);
        return "admin/ListDrinkFood/index";
    }

    @GetMapping("/view/{typeId}/{productId}")
    public String ProductDetail(@PathVariable Integer typeId, @PathVariable Integer productId, Model model) {
        List<drink_food> products = drinkFoodService.findBytypeId(typeId);
        try {
            List<invoice_detail> invoiceDetail = invoiceDetailService.listByproductId(productId);
            drink_food product = drinkFoodService.findById(productId);
            Long n_order = invoiceDetailService.countByproductId(productId);
            invoice_detail lastOrder = invoiceDetail.size() > 0 ? invoiceDetail.get(invoiceDetail.size() - 1) : null;
            log.info("It run here");
            model.addAttribute("products", products);
            model.addAttribute("productDetail", product);
            model.addAttribute("n_order", n_order);
            model.addAttribute("lastOrder", lastOrder);
            model.addAttribute("typeId", typeId);
            return "admin/ListDrinkFood/index";
        } catch (Exception err) {
            return "404_not_found";
        }
    }
}
