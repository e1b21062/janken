package oit.is.z1976.kaizi.janken.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1976.kaizi.janken.model.Entry;
import oit.is.z1976.kaizi.janken.model.User;
import oit.is.z1976.kaizi.janken.model.UserMapper;
import oit.is.z1976.kaizi.janken.model.Match;
import oit.is.z1976.kaizi.janken.model.MatchMapper;
import oit.is.z1976.kaizi.janken.model.MatchInfo;
import oit.is.z1976.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1976.kaizi.janken.service.AsyncKekka;

@Controller
public class JankenController {

  private final Logger logger = LoggerFactory.getLogger(JankenController.class);

  @Autowired
  UserMapper UserMapper;

  @Autowired
  private Entry room;

  @Autowired
  MatchMapper MatchMapper;

  @Autowired
  MatchInfoMapper MatchInfoMapper;

  @Autowired
  AsyncKekka AsyncKekka;

  @GetMapping("/janken")
  public String jankenmain(ModelMap model, Principal prin) {
    this.room.addUser(prin.getName());
    model.addAttribute("room", this.room);
    ArrayList<User> users = UserMapper.selectAllUser();
    model.addAttribute("users", users);
    ArrayList<MatchInfo> matchinfo = MatchInfoMapper.selectMatchInfoisActive();
    model.addAttribute("matchinfo", matchinfo);
    ArrayList<Match> match = MatchMapper.selectAllMatch();
    model.addAttribute("match", match);
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

    model.addAttribute("user1", prin.getName());

    MatchInfo matchinfo = new MatchInfo();
    int id1 = UserMapper.selectByNameToUser2(prin.getName());
    matchinfo.setUser1(id1);
    matchinfo.setUser2(id);
    matchinfo.setUser1Hand(hand);
    matchinfo.setIsActive(true);
    MatchInfo matchinfo2 = MatchInfoMapper.selectByIdIsActive(id1);

    if (matchinfo2 == null) {
      MatchInfoMapper.insertMatchInfoById(matchinfo);
    } else {
      System.out.println(MatchInfoMapper.selectByidHand(id));

      Match match = new Match();
      match.setUser1(id1);
      match.setUser2(id);
      match.setUser1Hand(hand);
      match.setUser2Hand(MatchInfoMapper.selectByidHand(id1));
      match.setIsActive(true);
      MatchMapper.insertMatch(match);
    }

    return "wait.html";
  }

  @GetMapping("step9")
  public SseEmitter sample59() {
    final SseEmitter sseEmitter = new SseEmitter();

    this.AsyncKekka.asyncmatch(sseEmitter);

    return sseEmitter;
  }
}
