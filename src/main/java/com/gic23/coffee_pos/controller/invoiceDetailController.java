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

import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.entity.invoice_detail;
import com.gic23.coffee_pos.entity.size;
import com.gic23.coffee_pos.service.implement.drinkFoodSizeServiceImp;
import com.gic23.coffee_pos.service.implement.drinkFoodToppingServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceDetailServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceServiceImp;
import com.gic23.coffee_pos.util.DoubleRound;

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
    @Autowired
    private invoiceServiceImp invoiceService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<invoice_detail> GetByID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(invoiceDetailService.getById(id));
    }

    @GetMapping("/total/{id}")
    @ResponseBody
    public ResponseEntity<Double> getTotal(@PathVariable Integer id) {
        return ResponseEntity.ok().body(invoiceDetailService.getTotalPriceByInvoiceId(id));
    }

    @PostMapping("/new")
    public String New(@ModelAttribute("invoice_detail") invoice_detail data,
            @RequestParam("invoiceCode") String invoiceCode, @RequestParam("typeid") Integer typeid,
            @RequestParam("categoryCode") String categoryCode) {
        Double sizePrice = null;
        Double toppingPrice = null;
        Double unit_price = null;
        Double amount = null;
        invoice existedInvoice = invoiceService.getById(data.getInvoiceId());

        if (data.getToppingId() == -1) {
            sizePrice = drinkFoodSizeService.getById(data.getSizeId()).getPrice();
            unit_price = DoubleRound.roundUp(sizePrice, 2);
            amount = unit_price * data.getQuantity();
            amount = amount - DoubleRound.roundUp((amount * (data.getDiscount() / 100)), 2);
            data.setUnitPrice(unit_price);
            data.setAmount(amount);
            data.setToppingId(null);
            invoice_detail savedInvoice = invoiceDetailService.save(data);

            existedInvoice
                    .setSub_totalPrice(invoiceDetailService.getTotalPriceByInvoiceId(savedInvoice.getInvoiceId()));
            invoiceService.Save(existedInvoice);

            return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode + "/" + typeid
                    : "redirect:/v1/invoice/view/" + invoiceCode + "/" + typeid + "/" + categoryCode;
        }

        if (data.getSizeId() != null) {

            sizePrice = drinkFoodSizeService.getById(data.getSizeId()).getPrice();

        }
        if (data.getToppingId() != null) {

            toppingPrice = drinkFoodToppingService.getById(data.getToppingId()).getPrice();

        }

        if (toppingPrice != null && sizePrice != null) {
            unit_price = DoubleRound.roundUp(sizePrice + toppingPrice, 2);
            amount = unit_price * data.getQuantity();
            amount = amount - DoubleRound.roundUp((amount * (data.getDiscount() / 100)), 2);
            data.setUnitPrice(unit_price);
            data.setAmount(amount);
            data.setToppingId(null);
        } else {
            unit_price = DoubleRound.roundUp(sizePrice, 2);
            amount = unit_price * data.getQuantity();
            amount = amount - DoubleRound.roundUp((amount * (data.getDiscount() / 100)), 2);
            data.setUnitPrice(unit_price);
            data.setAmount(amount);
            data.setToppingId(null);

        }
        data.setUnitPrice(unit_price);
        invoice_detail savedInvoice = invoiceDetailService.save(data);

        existedInvoice.setSub_totalPrice(invoiceDetailService.getTotalPriceByInvoiceId(savedInvoice.getInvoiceId()));
        invoiceService.Save(existedInvoice);

        return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode + "/" + typeid
                : "redirect:/v1/invoice/view/" + invoiceCode + "/" + typeid + "/" + categoryCode;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @RequestParam("invoiceCode") String invoiceCode,
            @RequestParam("categoryCode") String categoryCode) {

        invoiceDetailService.deleteById(id);
        return categoryCode.isBlank() ? "redirect:/v1/invoice/view/" + invoiceCode
                : "redirect:/v1/invoice/view/" + invoiceCode + "/" + categoryCode;
    }

}
