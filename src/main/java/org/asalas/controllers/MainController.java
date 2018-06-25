package org.asalas.controllers;

import org.asalas.commands.ProductForm;
import org.asalas.commands.LoginForm;
import org.asalas.converters.ProductToProductForm;
import org.asalas.domain.Product;
import org.asalas.dto.Users;
import org.asalas.services.ProductService;
import org.asalas.services.UsersService;
import org.asalas.services.CustomerUserDetailsService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class MainController {
    private ProductService productService;

    private ProductToProductForm productToProductForm;

   
    
    
    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    @RequestMapping("/")
    public String showIndex() {
    	return "redirect:/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
         return "login";
    }

    @RequestMapping("/users")
    public String showUsers(ModelMap model) {
    	UsersService c =  new UsersService();
    	Users user = new Users(); 
    	model.addAttribute("page", "users");
    	model.addAttribute("varMsg", "");
    	model.addAttribute("varList", c.listSecUsersMongoDB());
    	model.addAttribute("varForm", user );
    	
    	return "mainpage";
    }
    
    @RequestMapping(value = "/useradd", method = RequestMethod.POST)
    public String addUser (@ModelAttribute Users userForm, ModelMap model) {
    	UsersService c =  new UsersService();
    	String msg = c.addUser(userForm);
    	Users user = new Users(); 
    	model.addAttribute("page", "users");
    	model.addAttribute("varList", c.listSecUsersMongoDB());
    	model.addAttribute("varForm", user );
    	model.addAttribute("varMsg", msg);
    	return "mainpage";
    }
    
    @RequestMapping("/dashboard")
    public String showDashboard(ModelMap model) {
    	model.addAttribute("page", "dashboard");
    	return "mainpage";
    }
    @RequestMapping("/charts")
    public String showCharts(ModelMap model) {
    	model.addAttribute("page", "charts");
    	return "mainpage";
    }
    @RequestMapping("/tables")
    public String showTables(ModelMap model) {
    	model.addAttribute("page", "tables");
    	return "mainpage";
    }
  /*  @RequestMapping("/index.html")
    public String showIndexHtml() {
    	return "dashboard.html";
    }
    @GetMapping("/login")
    public String showLoginHtml(Model model) {
    	model.addAttribute("loginObj", new LoginForm());
    	return "login";
    }
    @PostMapping("/login")
    public String doLoginEval(@ModelAttribute LoginForm loginForm )  {
    	String user = loginForm.getUsername();
    	String passwd = loginForm.getPassword();
    	System.out.println("user: " + user + "pwd: " +  passwd);
    	if("admin".equals(user) && "adm123".equals(passwd)) {
    		return "dashboard.html";
    	} 
    	return "redirect:/login";
    }
    */
   /* public String redirToList(){
        return "redirect:/product/list";
    }*/

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id){
        productService.delete(id);
        return "redirect:/product/list";
    }
}
