package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO 14.6.1(1): Add the @Controller annotation, along with @RequestMapping("user") to configure routes into the controller.
@Controller
@RequestMapping("user")
public class UserController {
    // TODO 14.6.3(1): In the UserController, create a handler method displayAddUserForm() to render the form.
    //  This handler should correspond to the path /user/add, and for now, it can just return the path to the
    //  add.html template.

    @GetMapping("/add") // since request to display a page
    private String displayAddUserForm() {
                return "/user/add";
    }

    // TODO 14.6.4(1): Within the UserController, create a handler method with this signature. This will use
    //  model binding to create a new user object, user, and pass it into your handler method.

    @PostMapping("/add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (!user.getPassword().equals(verify)){
            // TODO 14.6.5(2): If the form is re-rendered when a password is not verified, we should let the
            //  user know that their form was not properly submitted. Use model.addAttribute to add an error
            //  attribute, letting the user know that their passwords should match.
            model.addAttribute("error", "Please make sure your passwords match!");

            // TODO 14.6.5(3): If we send a user back to re-populate the form, it would be nice to not clear their previous submission. We won’t need to save the password entries in this fashion.
            // TODO 14.6.5(3)A: In the form submission handler, add the username and email fields of the submitted user as model attributes.
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());

            return "/user/add";
        } else {
            return "/user/index";
        }

    }

}
