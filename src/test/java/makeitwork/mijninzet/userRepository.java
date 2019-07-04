package makeitwork.mijninzet;

import makeitwork.mijninzet.model.Role;
import makeitwork.mijninzet.model.User;
import makeitwork.mijninzet.repository.UserRepository;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;



public class userRepository {

    UserRepository userRepository = new UserRepository() {
        @Override
        public User findByUsername(String username) {
            return null;
        }

        @Override
        public User findByEmail(String email) {
            return null;
        }

        @Override
        public Set<User> findAllByRole(Role role) {
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


    public userRepository() {
    }

    @Test
    public void saveGebruiker(){
        User user = nieuweGebruiker("Merel","","Slingenberg","1234","info@slingenberg.nl");
        var gelukt=userRepository.save(user);
        System.out.printf("%s\n",gelukt);
        System.out.printf("%s\n",user);
        assertNotNull(gelukt);
        assertNull(userRepository.save(user));

        user = nieuweGebruiker("Huub","van","Thienen","1234","info@thienen.nl");
        assertNotNull(userRepository.save(user));
        assertNull(userRepository.save(user));

        user = nieuweGebruiker("Gerke","de","Boer","1234","info@deboer.nl");
        assertNotNull(userRepository.save(user));
        assertNull(userRepository.save(user));
    }


    private User nieuweGebruiker(String voornaam,
                                 String voorvoegsel,
                                 String achternaam,
                                 String password,
                                 String email){
        User user = new User();
        user.setSurname(voornaam);
        user.setNamePrefix(voorvoegsel);
        user.setFamilyName(achternaam);
        user.setUsername(voornaam+achternaam);
        user.setPassword(user.encryptPassword(password));
        user.setEmail(email);
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Docent");
        List<Role> rol=new ArrayList<>();
        rol.add(role);
        user.setRole(rol);
        user.setActive(1);
        return user;
    }
}
