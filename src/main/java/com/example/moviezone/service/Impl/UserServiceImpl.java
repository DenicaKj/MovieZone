package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Role;
import com.example.moviezone.model.User;
import com.example.moviezone.model.Worker;
import com.example.moviezone.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.moviezone.model.exceptions.PasswordsDoNotMatchException;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.repository.CustomerRepository;
import com.example.moviezone.repository.UserRepository;
import com.example.moviezone.repository.WorkerRepository;
import com.example.moviezone.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkerRepository workerRepository;
    private final CustomerRepository customerRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, WorkerRepository workerRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.workerRepository = workerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(String first_name, String last_name, String username, String email, String number, String password, Role role) {
//       if(!password.equals(repeatedPassword))
//           throw new PasswordsDoNotMatchException();
//       if (username==null || username.isEmpty()  || password==null || password.isEmpty())
//            throw new InvalidUsernameOrPasswordException();

       if(role.equals(Role.ROLE_ADMIN))
        {
//            User user= new User(passwordEncoder.encode(password),first_name,last_name,username,email,number);
//            workerRepository.save((Worker) user);
            userRepository.save(new Worker(password,first_name,last_name,email,number,username));
        }
        else
       {
//           Customer customer=new Customer(passwordEncoder.encode(password),first_name,last_name,username,email,number);
//           customerRepository.save(customer);
           userRepository.save(new Customer(password,first_name,last_name,email,number,username));

       }

    }

    @Override
    public User login(String username, String password) {
        return userRepository.findAllByUsernameAndPassword(username,password).stream().findFirst().orElseThrow(UserNotFoundException::new);
    }


}
