package konkon.controller;

import konkon.model.User;
import konkon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
  @Autowired
  private UserService userService;
  @GetMapping("/create")
  public String showForm(Model model){
    model.addAttribute("user", new User());
    return "create";
  }
  @PostMapping("/save")
  public String save(@Validated @Valid @ModelAttribute User user, BindingResult result, Model model ){
    if(result.hasFieldErrors()){
      return "create";
    }else{
      userService.save(user);
      model.addAttribute("user",user);
      return "result";
    }
  }


}
