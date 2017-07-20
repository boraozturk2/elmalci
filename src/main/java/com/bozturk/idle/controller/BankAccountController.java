package com.bozturk.idle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserBankAccount;
import com.bozturk.idle.repository.UserBankAccountRepository;
import com.bozturk.idle.repository.UserRepository;
import com.bozturk.idle.service.UserPrincipal;

@Controller
public class BankAccountController extends MainController {

	@Autowired
	private UserBankAccountRepository bankAccountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/bankaccount", method = RequestMethod.GET)
	public ModelAndView getBankAccount(@RequestParam(required=false) Long bankAccountId) {

		ModelAndView modelAndView = new ModelAndView();
		
		UserBankAccount bankAccount = new UserBankAccount();
		if (bankAccountId!=null){
			bankAccount = bankAccountRepository.findOne(bankAccountId);
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserPrincipal userP = (UserPrincipal)authentication.getPrincipal();
			User user = userRepository.findByEmail(userP.getUsername());
			bankAccount.setName(user.getName());
			bankAccount.setLastName(user.getLastName());
		}
		modelAndView.addObject("bankAccount", bankAccount);
		modelAndView.setViewName("/user/bankaccount");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/bankaccounts", method = RequestMethod.GET)
	public ModelAndView getUserBankAccounts() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPr = (UserPrincipal)authentication.getPrincipal();
		
		User user = userRepository.findByEmail(userPr.getUsername());
		List<UserBankAccount> userBankAccounts= bankAccountRepository.findByUserId(user.getId());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userBankAccounts", userBankAccounts);
		modelAndView.setViewName("/user/bankaccounts");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/bankaccount", method = RequestMethod.POST)
	public ModelAndView upsertCategories(@Valid UserBankAccount bankAccount, BindingResult result, ModelAndView modelAndView) {

		if (result.hasErrors()) {
			modelAndView.getModelMap().addAttribute("errorMessage", result.getFieldErrors().get(0).getField()+":"+result.getFieldErrors().get(0).getDefaultMessage());
			modelAndView.addObject("bankAccount", bankAccount);
			modelAndView.setViewName("/user/bankaccount");
			addMissingObjects(modelAndView);
			return modelAndView;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
		
		
		bankAccount.setUser(userRepository.findByEmail(user.getUsername()));
		bankAccountRepository.save(bankAccount);
		
		return getUserBankAccounts();
	}

	
}
