package com.mitocode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiration;

    public void setExpiration(int minutes) {
        LocalDateTime today = LocalDateTime.now();
        this.expiration = today.plusMinutes(minutes);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiration);
    }

}
