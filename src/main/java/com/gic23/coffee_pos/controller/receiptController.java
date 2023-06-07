package com.gic23.coffee_pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.entity.reciept;
import com.gic23.coffee_pos.entity.tables;
import com.gic23.coffee_pos.service.implement.invoiceServiceImp;
import com.gic23.coffee_pos.service.implement.recieptServiceImp;
import com.gic23.coffee_pos.service.implement.tableServiceImp;
import com.gic23.coffee_pos.util.DoubleRound;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/receipt")
@Slf4j
public class receiptController {

    @Autowired
    private recieptServiceImp recieptService;
    @Autowired
    private invoiceServiceImp invoiceService;
    @Autowired
    private tableServiceImp tableService;

    @GetMapping("/{receiptId}")
    @ResponseBody
    public ResponseEntity<reciept> getByID(@PathVariable Integer receiptId) {
        return ResponseEntity.ok().body(recieptService.findById(receiptId));
    }

    @GetMapping("/view/{receiptId}")
    public String receiptView(@PathVariable Integer receiptId, Model model) {
        try {
            reciept data = recieptService.findById(receiptId);
            model.addAttribute("receipt", data);
            return "main/receipt/index";

        } catch (Exception err) {
            return "404_not_found";
        }

    }

    @PostMapping("/new")
    public String newReceipt(Model model, @RequestParam("changeMoney") Double changeMoney,
            @RequestParam("receiveMoney") Double receiveMoney, @RequestParam("invoiceId") Integer invoiceId,
            @RequestParam("receiptId") Integer receiptId) {
        if (receiptId != -1) {

            return "redirect:/v1/receipt/view/" + receiptId;
        }
        try {
            log.info("Test {}", invoiceId);
            invoice invoiceData = invoiceService.getById(invoiceId);
            tables table = invoiceData.getTables();
            table.setStatusId(1);
            tableService.save(table);
            invoiceData.setTotalPrice(
                    DoubleRound.roundUp(invoiceData.getSub_totalPrice()
                            - (invoiceData.getSub_totalPrice() * invoiceData.getDiscount()) / 100, 2));
            invoiceData.setStatusId(2);
            invoiceService.Save(invoiceData);

            reciept saved = recieptService.save(
                    reciept
                            .builder()
                            .changeMoney(DoubleRound.roundUp(changeMoney, 2))
                            .receiveMoney(receiveMoney)
                            .orderId(invoiceData.getId())
                            .build());
            return "redirect:/v1/receipt/view/" + saved.getId();
        } catch (Exception err) {
            return "error_page";
        }

    }

}
