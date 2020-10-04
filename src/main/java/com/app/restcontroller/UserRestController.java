package com.app.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.UserService;


@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		Integer id=userService.saveUser(user);
		ResponseEntity<String> resp=new ResponseEntity<String>("User Created successfully",HttpStatus.CREATED);
		return resp;
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<?> getAllUsers(){
		
		List<User> usersList=userService.getAllUsers();
		ResponseEntity<?> resp=null;
		
		if(usersList==null || usersList.isEmpty()) {
			 resp=new ResponseEntity<String>("NO DATA FOUND",HttpStatus.OK);
		}else {
			 resp=new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
		}
		
		return resp;
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		Optional<User> user=userService.getUserById(id);
		 ResponseEntity<?> resp=null;
		if(user.isPresent()) {
			resp=new ResponseEntity<User>(user.get(),HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("USER NOT FOUND",HttpStatus.BAD_REQUEST);
		}
		
		return resp;
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> removeUser(@PathVariable Integer id){
		
		
		userService.deleteUser(id);
		ResponseEntity< String> resp=new ResponseEntity<String>("USER DELETED SUCCESSFULLY",HttpStatus.OK);
		
		return resp;
		
	}
	@PutMapping("updateuser")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		Integer id=user.getId();
		boolean exist=userService.isExists(id);
		ResponseEntity<String> resp=null;
		if(exist) {
			userService.saveUser(user);
			resp=new ResponseEntity<String>("USER UPDATED SUCESSFULLY",HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("USER NOT EXISTS",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	
}
