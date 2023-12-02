package ca.lambton.LoyaltiVue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.lambton.LoyaltiVue.dto.LoginPayload;
import ca.lambton.LoyaltiVue.dto.RegisterPayload;
import ca.lambton.LoyaltiVue.dto.UserDetailsDto;
import ca.lambton.LoyaltiVue.service.UserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginPayload loginRequest) {
		try {
		UserDetailsDto userDetails = userService.verifyUser(loginRequest.getPhoneNumber(), loginRequest.getPassword());
		if(userDetails != null)
			return ResponseEntity.ok(userDetails);
		throw new Exception("Login failed");
			
		} catch(Exception e) {
			return new ResponseEntity<String>("Login Failed.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterPayload register){
		 try {
	            String message = userService.registerUser(register);
	            if (message != null)
	                return ResponseEntity.ok(message);
	            throw new Exception("Registration failed");
        } catch (Exception e) {
	            return new ResponseEntity<>("Registration Failed.", HttpStatus.BAD_REQUEST);
	        }
	}
	
	@GetMapping("/get-user-info")
	public ResponseEntity<?> checkPoints(@PathVariable long phnNumber){
		try {
			UserDetailsDto userDetails = userService.getUserDetails(phnNumber);
			if(userDetails != null)
				return ResponseEntity.ok(userDetails);
			throw new Exception("Error");

		}
		catch(Exception e) {
            return new ResponseEntity<>("Points Calculation Failed.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
