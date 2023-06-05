package com.gic23.coffee_pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.invoice_detail;
import com.gic23.coffee_pos.entity.size;
import com.gic23.coffee_pos.service.implement.drinkFoodSizeServiceImp;
import com.gic23.coffee_pos.service.implement.drinkFoodToppingServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceDetailServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/invoiceDetail")
@Slf4j
public class invoiceDetailController {

    @Autowired
    private invoiceDetailServiceImp invoiceDetailService;
    @Autowired
    private drinkFoodSizeServiceImp drinkFoodSizeService;
    @Autowired
    private drinkFoodToppingServiceImp drinkFoodToppingService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<invoice_detail> GetByID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(invoiceDetailService.getById(id));
    }

    @PostMapping("/new")
    public String New(@ModelAttribute("invoice_detail") invoice_detail data,
            @RequestParam("invoiceCode") String invoiceCode, @RequestParam("categoryCode") String categoryCode) {
        Double sizePrice = null;
        Double toppingPrice = null;
        Double unit_price = null;
        if (data.getToppingId() == -1) {
            sizePrice = drinkFoodSizeService.getById(data.getSizeId()).getPrice();
            unit_price = (sizePrice) * data.getQuantity();
            data.setUnitPrice(unit_price);
            data.setToppingId(null);
            invoice_detail savedInvoice = invoiceDetailService.save(data);
            return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode
                    : "redirect:/v1/invoice/view/" + invoiceCode + "/" + categoryCode;
        }

        if (data.getSizeId() != null) {
            sizePrice = drinkFoodSizeService.getById(data.getSizeId()).getPrice();
        }
        if (data.getToppingId() != null) {
            toppingPrice = drinkFoodToppingService.getById(data.getSizeId()).getPrice();
        }
        if (toppingPrice != null && sizePrice != null) {
            unit_price = (sizePrice + toppingPrice) * data.getQuantity();
        } else {
            unit_price = (sizePrice) * data.getQuantity();
        }
        data.setUnitPrice(unit_price);
        invoice_detail savedInvoice = invoiceDetailService.save(data);
        return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode
                : "redirect:/v1/invoice/view/" + invoiceCode + "/" + categoryCode;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @RequestParam("invoiceCode") String invoiceCode,
            @RequestParam("categoryCode") String categoryCode) {

        invoiceDetailService.deleteById(id);
        return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode
                : "redirect:/v1/invoice/view/" + invoiceCode + "/" + categoryCode;
    }

}
