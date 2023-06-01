package com.gic23.coffee_pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        log.info("Obj {}", test);
        model.addAttribute("tables", test);
        return "main/table/table_selection";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<List<tables>> TableList() {
        return ResponseEntity.ok().body(tableService.listTable());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Optional<tables>> Test(Model model, @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(tableService.getById(id));
    }
}
