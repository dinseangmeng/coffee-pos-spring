package com.gic23.coffee_pos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gic23.coffee_pos.entity.invoice;
import com.gic23.coffee_pos.entity.tables;
import com.gic23.coffee_pos.service.implement.tableServiceImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("v1/tables")
@Slf4j
public class tableController {
    @Autowired
    private tableServiceImp tableService;

    @GetMapping("")
    public String TabelView(Model model) {
        List<tables> test = tableService.listTable();
        // log.info("Obj {}", test);
        model.addAttribute("tables", test);
        return "main/table/index";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> Test(Model model, @PathVariable("id") Integer id) {
        Optional<tables> data = tableService.getById(id);
        List<invoice> notPaidInvoice = new ArrayList<>();
        for (invoice item : data.get().getInvoices()) {
            if (item.getStatus().getId() == 2)
                continue;
            log.info("Data test{}", item.getInvoiceCode());
            notPaidInvoice.add(item);
        }
        // log.info("Test {}", notPaidInvoice);
        if (notPaidInvoice.size() > 0) {
            invoice lastInvoice = notPaidInvoice.get((notPaidInvoice.size() - 1));
            List<invoice> invoices = new ArrayList<>(Arrays.asList(
                    lastInvoice));
            data.get().setInvoices(invoices);
        } else {
            data.get().setInvoices(new ArrayList<invoice>());
        }
        return ResponseEntity.ok().body(data);
    }

    @PostMapping("/free/{id}")
    public String FreeTable(@PathVariable("id") Integer id) {
        tables existTable = tableService.getById(id).get();
        existTable.setStatusId(3);
        tableService.save(existTable);
        return "redirect:/v1/tables";
    }

}
