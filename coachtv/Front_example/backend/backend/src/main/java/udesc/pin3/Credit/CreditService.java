package udesc.pin3.Credit;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.graalvm.collections.Pair;
import udesc.pin3.User.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CreditService {

    @Transactional
    public List<CreditPackDTO> getCreditPlans() {
        PanacheQuery<PanacheEntityBase> result = CreditPack.findAll();
        if (result.list().isEmpty()) {
            createDefaultCreditPacks();
            result = CreditPack.findAll();
        }
        List<CreditPack> list = result.list();
        List<CreditPackDTO> listDTO = new ArrayList<>();
        list.forEach(c -> listDTO.add(new CreditPackDTO(c)));

        return listDTO;
    }

    private void createDefaultCreditPacks() {
        CreditPack creditPack1 = new CreditPack();
        creditPack1.setAmount(300);
        creditPack1.setPrice(5f);
        creditPack1.setTitle("Pacote - 300 Créditos");
        creditPack1.persist();

        CreditPack creditPack2 = new CreditPack();
        creditPack2.setAmount(700);
        creditPack2.setPrice(10f);
        creditPack2.setTitle("Pacote - 700 Créditos");
        creditPack2.persist();

        CreditPack creditPack3 = new CreditPack();
        creditPack3.setAmount(1500);
        creditPack3.setPrice(20f);
        creditPack3.setTitle("Pacote - 1500 Créditos");
        creditPack3.persist();

    }

    @Transactional
    public Pair<Integer, String> purchaseCredits(CreditPackDTO creditPackDTO) {
        Optional<CreditPack> optionalCreditPack =
                CreditPack.find("title", creditPackDTO.getTitle()).firstResultOptional();

        if (optionalCreditPack.isPresent()) {
            CreditPack creditPack = optionalCreditPack.get();
            Optional<User> optionalUser = User.find("id", creditPackDTO.getUserDTO().getId()).firstResultOptional();
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.addCredits(creditPack.getAmount());
                user.persist();
                return Pair.create(200, "Compra de créditos finalizada com sucesso!");
            } else {
                return Pair.create(400, "Usuário não encontrado com a identificação informada!");
            }
        } else {
            return Pair.create(400, "Não foi encontrado um pacote de créditos com o título informado. " +
                    "Tente novamente!");
        }
    }
}
