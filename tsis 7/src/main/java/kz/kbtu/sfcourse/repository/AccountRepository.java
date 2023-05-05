package kz.kbtu.sfcourse.repository;
import kz.kbtu.sfcourse.domain.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
