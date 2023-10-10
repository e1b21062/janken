package oit.is.z1976.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String jankenmain(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    String setname = loginUser;
    int index = 1;
    model.addAttribute("setname", setname);
    model.addAttribute("index", index);
    return "janken.html";
  }

  @GetMapping("gu")
  public String jankengu(ModelMap model) {

    String ihand = "Gu";
    String cphand = "Gu";
    String result = "Draw";
    model.addAttribute("ihand", ihand);
    model.addAttribute("cphand", cphand);
    model.addAttribute("result", result);
    return "janken.html";
  }

  @GetMapping("choki")
  public String jankenchoki(ModelMap model) {

    String ihand = "Choki";
    String cphand = "Gu";
    String result = "You Lose";
    model.addAttribute("ihand", ihand);
    model.addAttribute("cphand", cphand);
    model.addAttribute("result", result);
    return "janken.html";
  }

  @GetMapping("pa")
  public String jankenPa(ModelMap model) {

    String ihand = "Pa";
    String cphand = "Gu";
    String result = "You Win!";
    model.addAttribute("ihand", ihand);
    model.addAttribute("cphand", cphand);
    model.addAttribute("result", result);
    return "janken.html";
  }

}
