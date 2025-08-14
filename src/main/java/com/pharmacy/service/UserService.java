package com.pharmacy.service;

import com.pharmacy.model.User;
import com.pharmacy.repository.PharmacyRepository;
import com.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository; // Inject PharmacyRepository for relationship handling

    @Autowired
    public UserService(UserRepository userRepository, PharmacyRepository pharmacyRepository) {
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        // Ensure the associated Pharmacy exists before saving the User
        if (user.getPharmacy() != null && user.getPharmacy().getId() != null) {
            pharmacyRepository.findById(user.getPharmacy().getId()).ifPresent(user::setPharmacy);
        }
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRememberToken(userDetails.getRememberToken());
            user.setStartDate(userDetails.getStartDate());
            user.setRole(userDetails.getRole());
            user.setIdentificationNumber(userDetails.getIdentificationNumber());
            user.setLastName(userDetails.getLastName());
            user.setAddress(userDetails.getAddress());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setSalary(userDetails.getSalary());

            // Update pharmacy if provided and exists
            if (userDetails.getPharmacy() != null && userDetails.getPharmacy().getId() != null) {
                pharmacyRepository.findById(userDetails.getPharmacy().getId())
                        .ifPresent(user::setPharmacy);
            } else if (userDetails.getPharmacy() == null) {
                // If pharmacy is set to null in details, remove association
                user.setPharmacy(null);
            }

            user.setProfilePictureUrl(userDetails.getProfilePictureUrl());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
