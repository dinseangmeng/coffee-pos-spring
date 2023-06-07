package com.gic23.coffee_pos.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gic23.coffee_pos.entity.gender;
import com.gic23.coffee_pos.entity.user;
import com.gic23.coffee_pos.entity.user_history_recode;
import com.gic23.coffee_pos.service.implement.genderServiceImp;
import com.gic23.coffee_pos.service.implement.invoiceServiceImp;
import com.gic23.coffee_pos.service.implement.userHisRecServiceImp;
import com.gic23.coffee_pos.service.implement.userServiceImp;
import com.gic23.coffee_pos.util.FileDownloadUtil;
import com.gic23.coffee_pos.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("v1/cp/user")
@Slf4j
public class userController {

    @Autowired
    private userServiceImp userService;
    @Autowired
    private userHisRecServiceImp userHisRecService;
    @Autowired
    private invoiceServiceImp invoiceService;
    @Autowired
    genderServiceImp genderService;
    // @GetMapping
    // public ResponseUtil list() {
    // return new ResponseUtil("successfull", "list successfull",
    // userService.list());

    // }

    // @PostMapping("/register")
    // public ResponseUtil register(@RequestBody user user) {
    // String rawPassword = user.getPassword();
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // String hashedPassword = encoder.encode(rawPassword);
    // user.setPassword(hashedPassword);
    // return new ResponseUtil("Success", "Register succeefull",
    // userService.register(user));
    // }

    @GetMapping("/cashier")
    @ResponseBody
    public ResponseEntity<ResponseUtil> listCasheir() {
        return ResponseEntity.ok().body(new ResponseUtil("Success", "List casheir",
                userService.listCasheir()));
    }

    @GetMapping("/view")
    public String ListCashierView(Model model) {
        List<user> cashiers = userService.listCasheir();
        model.addAttribute("cashiers", cashiers);
        return "admin/ListCasheir/index";
    }

    @PostMapping("/switch-status/{id}")
    @ResponseBody
    public ResponseEntity<ResponseUtil> switchstatus(@PathVariable Integer id) {

        try {
            user existedUser = userService.findById(id);
            existedUser.setStatus(!existedUser.getStatus());
            userService.save(existedUser);
        } catch (Exception err) {
            return ResponseEntity.ok().body(new ResponseUtil("Error", "User not exist", null));
        }

        return ResponseEntity.ok().body(new ResponseUtil("Success", "Status Change", null));
    }

    @GetMapping("/view/{id}")
    public String ListCashierViewByID(@PathVariable Integer id, Model model) {
        LocalDate currentDate = LocalDate.now();
        List<user> cashiers = userService.listCasheir();
        user cashier = userService.findById(id);
        // user_history_recode lastLoginRecord =
        // userHisRecService.findLastLoginOfUser(cashier.getId());
        log.info("not work");
        Long n_order = invoiceService.countBycashierId(id);
        int age = Period.between(cashier.getDate_of_birth(),
                currentDate).getYears();

        model.addAttribute("cashiers", cashiers);
        model.addAttribute("userDetail", cashier);
        // model.addAttribute("lastLoginRecord", lastLoginRecord);
        model.addAttribute("n_order", n_order);
        model.addAttribute("age", age);
        model.addAttribute("lastInvoiceCode",
                cashier.getInvoices().size() > 0
                        ? cashier.getInvoices().get(cashier.getInvoices().size() - 1).getInvoiceCode()
                        : "");
        return "admin/ListCasheir/index";
    }

    @GetMapping("/view/new")
    public String saveUserView(Model model) {
        List<gender> genders = genderService.list();
        model.addAttribute("genders", genders);
        return "admin/NewCasheir/index";
    }

    @GetMapping("/view/new/{id}")
    public String saveUserView(@PathVariable Integer id, Model model) {
        List<gender> genders = genderService.list();
        user casheir = userService.findById(id);
        model.addAttribute("genders", genders);
        model.addAttribute("casheir", casheir);
        model.addAttribute("IsCasheir", casheir == null ? false : true);
        return "admin/NewCasheir/index";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("casheir") user data,
            @RequestParam("file") MultipartFile file,
            Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");

            data.setAvatar("/user/default.png");
            user savedUser = userService.save(data);
            savedUser.setUid("DMS000" + savedUser.getId());
            return "redirect:/v1/cp/user/view";
        }
        try {
            // Get the filename and sanitize it
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

            // Generate a unique filename using the current timestamp in milliseconds
            String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

            // Define the destination directory to save the file
            String uploadDir = "src/main/resources/static/images/user";

            // Create the destination directory if it does not exist
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the path where the file will be saved
            Path filePath = Path.of(uploadDir, uniqueFilename);

            // Copy the file to the destination directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the filename to the model attribute to display it on the success page
            model.addAttribute("filename", uniqueFilename);
            data.setAvatar("/user/" + uniqueFilename);
            user savedUser = userService.save(data);
            savedUser.setUid("DMS000" + savedUser.getId());
            return "redirect:/v1/cp/user/view"; // The name of the HTML file (uploadSuccess.html)
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload the file");
            return "upload"; // The name of the HTML file (upload.html)
        }
        // model.addAttribute("genders", genders);

    }
}
