package com.avl.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avl.model.AVLTreeVO;
import com.avl.service.AVLTreeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private AVLTreeService treeService;
	
	AVLTreeVO tree;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("tree", tree );
		
		return "home";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public AVLTreeVO handleInsertRequest(@RequestParam("newValue") int newValue, Model model) {
	    if (tree == null) {
	        tree = treeService.insertNode(newValue);
	    } else {
	        tree = treeService.insertNode(tree, newValue);
	    }

	    model.addAttribute("tree", tree);

	    // Returning a view name to display the updated model
	    return tree;
	}

	
}
