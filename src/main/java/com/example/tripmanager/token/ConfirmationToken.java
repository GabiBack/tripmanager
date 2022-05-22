package com.example.tripmanager.token;

import com.example.tripmanager.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "tokens")
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "token", nullable = false)
    private String token;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NonNull
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

}
