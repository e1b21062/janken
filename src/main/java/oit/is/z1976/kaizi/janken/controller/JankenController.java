package oit.is.z1976.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1976.kaizi.janken.model.Entry;
import oit.is.z1976.kaizi.janken.model.User;
import oit.is.z1976.kaizi.janken.model.UserMapper;
import oit.is.z1976.kaizi.janken.model.Match;
import oit.is.z1976.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  UserMapper UserMapper;
  @Autowired
  private Entry room;
  @Autowired
  MatchMapper MatchMapper;

  @GetMapping("/janken")
  public String jankenmain(ModelMap model, Principal prin) {
    this.room.addUser(prin.getName());
    model.addAttribute("room", this.room);
    ArrayList<User> users = UserMapper.selectAllUser();
    model.addAttribute("users", users);
    ArrayList<Match> match = MatchMapper.selectAllMatch();
    model.addAttribute("match", match);
    System.out.println(match);
    return "janken.html";
  }

  @GetMapping("/match")
  public String sample45(@RequestParam Integer id, ModelMap model, Principal prin) {
    model.addAttribute("user1", prin.getName());
    User user2 = UserMapper.selectById(id);
    model.addAttribute("user2", user2);
    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam Integer id, @RequestParam String hand, ModelMap model, Principal prin) {
    String cphand = "Gu";
    String result = "Init";
    if (hand.equals("Gu")) {
      result = "Draw";
    } else if (hand.equals("Choki")) {
      result = "You Lose";
    } else if (hand.equals("Pa")) {
      result = "You Win!";
    }

    model.addAttribute("user1", prin.getName());

    model.addAttribute("ihand", hand);
    model.addAttribute("cphand", cphand);
    model.addAttribute("result", result);
    Match match3 = new Match();
    match3.setUser1(UserMapper.selectByNameToUser2(prin.getName()));
    match3.setUser2(1);
    match3.setUser1Hand(hand);
    match3.setUser2Hand(cphand);
    MatchMapper.insertMatch(match3);
    model.addAttribute("match3", match3);
    return "match.html";
  }
}
