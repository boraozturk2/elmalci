package com.bozturk.idle.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.ForgotEmailDTO;
import com.bozturk.idle.dto.UserPasswordDTO;
import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserPrincipal;
import com.bozturk.idle.model.UserToken;
import com.bozturk.idle.service.UserService;

@Controller
public class UserPasswordController extends MainController {

    // region Constants

    public static final String USER_CHANGEPASSWORD_VIEW = "users/password";
    public static final String USER_FORGOTPASSWORD_VIEW = "users/forgot";
    public static final String FLASH_MESSAGE_KEY_FEEDBACK = "feedbackMessage";

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserPasswordController.class);

    
    @RequestMapping(value = "/forgotpassword", method = GET)
    public ModelAndView sendForgotEmail(Model model) {
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("forgotEmailDTO", new ForgotEmailDTO());
		modelAndView.setViewName("forgotpassword");
		addMissingObjects(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/forgotpassword", method = POST)
    public ModelAndView sendForgotEmail(@Valid ForgotEmailDTO forgotEmailDTO,
                                  BindingResult result, Model model) {
    	
    	ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(forgotEmailDTO.getEmail());
      
        UserToken userToken = userService.createUserToken(user);
          //     fmMailService.sendResetPasswordMail(user.get(), userToken.getToken());
        modelAndView.addObject("forgotEmailDTO", new ForgotEmailDTO());
		modelAndView.setViewName("forgotpassword");
		addMissingObjects(modelAndView);
        return modelAndView;
    }
    
    @RequestMapping(value = "/changepassword", method = GET)
    public ModelAndView changePasswordFromEmail(@RequestParam String token, @RequestParam String email) {
    	
    	User user = userService.findUserByEmail(email);
    	
    	ModelAndView modelAndView = new ModelAndView();
        UserPasswordDTO userPasswordDTO = new UserPasswordDTO(user.getId(), token);
        modelAndView.addObject("userPasswordDTO", userPasswordDTO);
		modelAndView.setViewName("changepassword");
		addMissingObjects(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/changepassword", method = POST)
    public ModelAndView changePassword(@Valid @ModelAttribute("userPasswordDTO")
                                               UserPasswordDTO userPasswordDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        
        User user = userService.findUserById(userPasswordDTO.getUserId());
        Optional<UserToken> userToken = userService.getUserToken(userPasswordDTO.getVerificationToken());
        
        if (!userToken.isPresent()) {
        	result.rejectValue("","There is already a user registered with the email provided");
            modelAndView.addObject("userPasswordDTO", userPasswordDTO);
     		modelAndView.setViewName("changepassword");
     		addMissingObjects(modelAndView);
            return modelAndView;
        }
        
        userService.saveUser(user);
        /*
        if (!result.hasErrors()) {

            ResetPasswordResult resetPasswordResult =
                    userService.updatePassword(userPasswordDTO);

            switch (resetPasswordResult) {
                case ERROR:
                    result.reject("global.error.password.reset");
                    break;
                case FORGOT_SUCCESSFUL:
                    mav.addObject(FLASH_MESSAGE_KEY_FEEDBACK,
                            getMessage(FEEDBACK_PASSWORD_LOGIN));
                    break;
                case LOGGEDIN_SUCCESSFUL:
                    mav.addObject(FLASH_MESSAGE_KEY_FEEDBACK,
                            getMessage(FEEDBACK_PASSWORD_RESET));
                    break;
            }
        }
         */
        addMissingObjects(modelAndView);
        return modelAndView;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/resetpassword", method = GET)
    public String changePassword(UserPrincipal currentUser, Model model) {

        UserPasswordDTO userPasswordDTO = new UserPasswordDTO(currentUser.getId(), UUID.randomUUID().toString());
        model.addAttribute("userPasswordDTO", userPasswordDTO);
        return USER_CHANGEPASSWORD_VIEW;
    }
        
    @Resource
    private MessageSource messageSource;
    
    public String getMessage(String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, params, current);
    }

}