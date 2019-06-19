package makeitwork.mijninzet;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import makeitwork.mijninzet.controller.UserController;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class userController {
    UserController userController=new UserController();
    UserRepository userRepository=new UserRepository() {
        @Override
        public User findByUsername(String username) {
            return null;
        }

        public User findUserById(int id){
            return null;
        }
        @Override
        public User findByEmail(String email) {
            return null;
        }

        @Override
        public List<User> findAll() {
            return null;
        }

        @Override
        public List<User> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<User> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends User> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends User> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<User> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public User getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<User> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends User> S save(S s) {
            return null;
        }

        @Override
        public Optional<User> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(User user) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends User> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends User> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends User> boolean exists(Example<S> example) {
            return false;
        }
    };


    @Test
    public void deSerializeUser(){
        for (int i=1; i<5;i++){
            Optional<User> user=userRepository.findById(i);
            if (user.isPresent())
            assertEquals(user,userController.deSerializeUser(makeObject(user.get())));
        }
    }
    private String makeObject(User user){
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr="";

        try {
            jsonStr = Obj.writeValueAsString(user);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
