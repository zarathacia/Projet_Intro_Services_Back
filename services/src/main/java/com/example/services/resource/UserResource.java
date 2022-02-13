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
@Api(tags = { SwaggerConfig.API_TAG4 })
public class UserResource extends ExceptionHandling {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenProvider jWTTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal principal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(principal);
        return ResponseEntity.ok().headers(jwtHeader).body(loginUser);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistException, EmailExistException, UserNotFoundException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/find/" + newUser.getUsername()).toUriString());
        return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNonLocked") String isNonLocked,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws UsernameExistException, EmailExistException, UserNotFoundException, IOException {
        User newUser = userService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(isNonLocked),
                Boolean.parseBoolean(isActive), profileImage);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestParam("currentUsername") String currentUsername,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam(value = "role", required = false) String role,
                                       @RequestParam("isActive") String isActive,
                                       @RequestParam("isNonLocked") String isNonLocked) throws UsernameExistException, EmailExistException, UserNotFoundException, IOException {
        User newUser = userService.updateUser(currentUsername, firstName, lastName, username, email, role, Boolean.parseBoolean(isNonLocked),
                Boolean.parseBoolean(isActive));
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username")String username) {
        User user = userService.findUserByUsername(username);
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

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Response> deleteUser(@PathVariable("username") String username) throws IOException {
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
