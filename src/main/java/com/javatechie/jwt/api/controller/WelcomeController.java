package com.javatechie.jwt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.jwt.api.advice.TrackExecutionTime;
import com.javatechie.jwt.api.entity.AuthRequest;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.repository.UserMGDBRepository;
import com.javatechie.jwt.api.service.UsersService;
import com.javatechie.jwt.api.util.JwtUtil;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMGDBRepository repository;
    @Autowired
    private UsersService usersService;
    @Value("${spring.message}")
    private String message;
    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!" +message;
    }
 	String msg =null;
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {                                       //UsernamePasswordAuthenticationToken to the default AuthenticationProvider,
        	                                         // which will use the userDetailsService to get the user based on username and compare that user's password with the one in the authentication token.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
    
    @PostMapping("/addUsers")
    public ResponseEntity<?> addUsers(@RequestBody User user) throws Exception {
    	 User userRes	 = usersService.addUser(user);
		/*
		 * User res = null;
		 * 
		 * try { res = repository.save(user);
		 * 
		 * } catch (Exception ex) { throw new
		 * Exception("Exception occurred while saving user"); }
		 * 
		 * return res;
		 */
    	 return new ResponseEntity<User>(userRes,HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteUserById/{id}")
	public String deleteUser(@PathVariable int id) throws Exception {
		try {
			repository.deleteById(id);
			msg = "User deleted Successfully.";

		} catch (Exception ex) {
			msg = "Exception occurred while Deteleted user.";
			throw new Exception("Exception occurred while Deteleted user");

		}

		return msg;
	}
    @TrackExecutionTime
    @GetMapping("/findByAgeGreaterThan/{id}")
    public List<User> findByAgeGreaterThan(@PathVariable int  id) throws Exception {
    	List<User> user = null;
    	try {
    		 user =usersService.findByAgeGreaterThan(id);
    		
    	}catch(Exception ex) {
    		msg = "Exception occurred while searching findByAgeGreaterThan ."+ex;
			throw new Exception("Exception occurred while searching findByAgeGreaterThan ");
    	}
		return user;
    	
    }
    @TrackExecutionTime
    @GetMapping("/findByFirstnameLike/{userName}")
    public List<User> findByAgeGreaterThan(@PathVariable String  userName) throws Exception {
    	List<User> user = null;
    	try {
    		 user =usersService.findByFirstnameLike(userName);
    		
    	}catch(Exception ex) {
    		msg = "Exception occurred while searching findByFirstnameLike ."+ex;
			throw new Exception("Exception occurred while searching findByFirstnameLike ");
    	}
		return user;
    	
    }
    
}
