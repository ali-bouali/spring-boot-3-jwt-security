package com.alibou.security.token;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String tokenCode;

  @Enumerated(EnumType.STRING)
  private TokenType tokenType = TokenType.BEARER;

  private boolean revoked;

  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  public User user;

  @Override
  public final boolean equals(Object o) {
    if (Objects.isNull(o)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ?
            hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ?
            hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Token token = (Token) o;
    return Objects.nonNull(getId()) && Objects.equals(getId(), token.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy hibernateProxy ?
            hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
