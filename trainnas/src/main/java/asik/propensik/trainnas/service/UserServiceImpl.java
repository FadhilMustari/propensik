package asik.propensik.trainnas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import asik.propensik.trainnas.model.PelatihanTrainee;
import asik.propensik.trainnas.model.UserModel;
import asik.propensik.trainnas.repository.PelatihanTraineeDb;
import asik.propensik.trainnas.repository.UserDb;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Autowired
    private PelatihanTraineeDb pelatihanTraineeDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<PelatihanTrainee> listPelatihan(){
        return pelatihanTraineeDb.findAll();
    }


}
