package oit.is.z1976.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1976.kaizi.janken.model.Entry;
import oit.is.z1976.kaizi.janken.model.User;
import oit.is.z1976.kaizi.janken.model.UserMapper;
import oit.is.z1976.kaizi.janken.model.Match;
import oit.is.z1976.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry room;
  private UserMapper UserMapper;
  private MatchMapper MatchMapper;

  @GetMapping("/janken")
  @Transactional
  public String jankenmain(ModelMap model, Principal prin) {
    this.room.addUser(prin.getName());
    model.addAttribute("room", this.room);
    // ArrayList<User> users7 = UserMapper.selectAllUser();
    // model.addAttribute("users7", users7);
    ArrayList<Match> match = MatchMapper.selectAllMatch();
    model.addAttribute("match", match);
    return "janken.html";
  }

  @GetMapping("/match")
  @Transactional
  public String sample45(@RequestParam Integer param1, ModelMap model, Principal prin) {
    model.addAttribute("user1", prin.getName());
    User user2 = UserMapper.selectById(param1);
    model.addAttribute("user2", user2);
    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam Integer id, @RequestParam String hand, ModelMap model, Principal prin) {
    String cphand = "Gu";
    String result = "Init";
    if (hand == "Gu") {
      result = "Draw";
    } else if (hand == "Choki") {
      result = "You Lose";
    } else if (hand == "Pa") {
      result = "You Win!";
    }

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
