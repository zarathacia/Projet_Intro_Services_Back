package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.exception.ExceptionHandling;
import com.example.services.exception.domain.EmailExistException;
import com.example.services.exception.domain.EmailNotFoundException;
import com.example.services.exception.domain.UserNotFoundException;
import com.example.services.exception.domain.UsernameExistException;
import com.example.services.model.Response;
import com.example.services.model.User;
import com.example.services.model.UserPrincipal;
import com.example.services.service.UserService;
import com.example.services.utility.JWTTokenProvider;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static com.example.services.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/", "/user"})
@AllArgsConstructor
@Api(tags = { SwaggerConfig.API_TAG5 })
public class UserResource extends ExceptionHandling {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenProvider jWTTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserForm userForm) {
        authenticate(userForm.getEmail(), userForm.getPassword());
        User loginUser = userService.findUserByEmail(userForm.getEmail());
        UserPrincipal principal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(principal);
        return ResponseEntity.ok().headers(jwtHeader).body(loginUser);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterForm registerForm) throws UsernameExistException, EmailExistException, UserNotFoundException {
        User newUser = userService.register(registerForm.getFullName(), registerForm.getEmail(), registerForm.getPassword(), registerForm.getConfirmedPassword());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/find/" + newUser.getFullName()).toUriString());
        return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("fullName") String fullName,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNonLocked") String isNonLocked,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws UsernameExistException, EmailExistException, UserNotFoundException, IOException {
        User newUser = userService.addNewUser(fullName,email, role, Boolean.parseBoolean(isNonLocked),
                Boolean.parseBoolean(isActive), profileImage);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestParam("currentFullname") String currentFullname,
                                       @RequestParam("newFullname") String newFullname,
                                       @RequestParam("email") String email,
                                       @RequestParam(value = "role", required = false) String role,
                                       @RequestParam("isActive") String isActive,
                                       @RequestParam("isNonLocked") String isNonLocked) throws UsernameExistException, EmailExistException, UserNotFoundException, IOException {
        User newUser = userService.updateUser(currentFullname,newFullname,email, role, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive));
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/find/{fullname}")
    public ResponseEntity<User> getUser(@PathVariable("fullname")String username) {
        User user = userService.findUserByFullName(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

	@GetMapping("/resetpassword/{email}")
	public ResponseEntity<Response> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException {
		userService.resetPassword(email);
		return response(OK, "Password changed successfully");
	}

    @DeleteMapping("/delete/{fullName}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Response> deleteUser(@PathVariable("fullName") String username) throws IOException {
        userService.deleteUser(username);
        return response(OK, "User deleted successfully");
    }

    private HttpHeaders getJwtHeader(UserPrincipal user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER, jWTTokenProvider.generateJwtToken(user));
        return httpHeaders;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private ResponseEntity<Response> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new Response(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }

}

@Data
class UserForm{
    private String email;
    private String password;
}

@Data
class  RegisterForm{
    private String fullName;
    private String email;
    private String password;
    private String confirmedPassword;

}
