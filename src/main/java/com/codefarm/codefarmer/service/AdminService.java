package com.codefarm.codefarmer.service;

import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private UserRepository userRepository;
    private FarmerRepository farmerRepository;



}
