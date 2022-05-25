package com.example.tripmanager.service;

import com.example.tripmanager.repository.ConfirmationTokenRepository;
import com.example.tripmanager.repository.UserRepository;
import com.example.tripmanager.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfirmationTokenService.class);

    @Autowired
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public String createConfirmationToken(Long id) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(userRepository.findById(id).get())
                .build();
        this.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Transactional
    public Boolean confirmToken(String token, Long userId){
        Optional<ConfirmationToken> confirmationToken = this.findConfirmationTokenByUserIdAndToken(token, userId);
        Boolean confirmed = null;
            try{
                if(confirmationToken.isPresent()){
                    confirmed = this.checkIfTokenisActive(confirmationToken.get());
                    confirmationToken.get().setConfirmedAt(LocalDateTime.now());
                }
            } catch (NullPointerException e){
                LOGGER.error("there is no matching token in data base", e);
                throw new NullPointerException("there is no matching token in data base");
            }

        return confirmed;
    }


    private void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }


    private Optional<ConfirmationToken> findConfirmationTokenByUserIdAndToken(String token, Long userId){
        List<ConfirmationToken> tokens = confirmationTokenRepository.findAll();
        ConfirmationToken confirmationToken = null;

        for(ConfirmationToken ct : tokens) {
            String tokenToCheck = ct.getToken();
            Long userIdToCheck = ct.getUser().getId();
            if(tokenToCheck.equals(token) && userIdToCheck.equals(userId)) confirmationToken = ct;
        }

        return Optional.ofNullable(confirmationToken);
    }


    private boolean checkIfTokenisActive(ConfirmationToken confirmationToken) {
        LocalDateTime created = confirmationToken.getCreatedAt();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(created,now);
        long difference = Math.abs(duration.toMinutes());

        return difference <= 15;
    }
}
