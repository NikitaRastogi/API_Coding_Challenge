package com.example.controller;

import com.example.Enum.Status;
import com.example.model.User;
import com.example.pojo.LoginUser;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/register")
    public Status registerUser( @RequestBody LoginUser user) {
        User newuser = userRepository.findByUsername(user.getUsername());
        if(newuser != null){
            if(user.getUsername().equals(newuser.getUsername())){
                return Status.USER_ALREADY_EXISTS;
            }
        }
        else {
            newuser = new User(user.getUsername(),user.getPassword());
            userRepository.save(newuser);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }

    @PostMapping("/users/login")
    public Status loginUser( @RequestBody LoginUser user) {
        User users = userRepository.findByUsername(user.getUsername());

        if(users != null){
            if(users.getPassword().equals(user.getPassword()) ){
                users.setLoggedin(true);
                userRepository.save(users);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @PostMapping("/users/logout")
    public Status logUserOut(@RequestBody User user) {
        User users = userRepository.findByUsername(user.getUsername());
        if(users != null){
            if(user.getUsername().equals(users.getUsername())){
                user.setLoggedin(false);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;

    }
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }


//    @PostMapping("/save")
//    public boolean createUser(@RequestBody User user) {
//        return userServices.saveUser(user);
//
//    }
//
//    @GetMapping("/all")
//    public List<User> getUser() {
//        return userServices.getUser();
//    }
//
//    @GetMapping("/getById/{id}")
//    public String getUser(@PathVariable long id){
//        User tempUser = new User();
//        String response = "";
//        Optional<User> optionalUser = userServices.getUserById(id);
//        //tempUser = user.orElse(dummyUser);
//        if(optionalUser.isPresent()){
//            response = optionalUser.get().toString();
//        }else{
//            response = "No user found with id " + id;
//        }
//        return response;
//    }
//    @GetMapping("/getByIdd/{id}")
//    public User getUsers(@PathVariable long id){
//        User tempUser = new User();
//        String response = "";
//        Optional<User> optionalUser = userServices.getUserById(id);
//        //tempUser = user.orElse(dummyUser);
////        if(optionalUser.isPresent()){
////            tempUser = optionalUser.get();
////        }else{
////            System.out.println("tempUser = " + tempUser);
////            response = "No user found with id " + id;
////        }
//        return optionalUser.get();
//    }
//
//    @GetMapping("/deleteUser/{id}")
//    public void deleteUser(@PathVariable long id){
//        userRepository.deleteById(id);
//    }


}