package Presentation;

import Metier.CreditMetier;
import Metier.ICreditMetier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

public class CreditControleur implements  ICreditControleur{

    @Autowired
    ICreditMetier creditMetier;

    @Override
    public void NS_afficher_Mensualite(Long id) throws Exception {
        var creditAvecMensualite = creditMetier.NS_calculer_Mensualite(id);
        System.out.println(creditAvecMensualite);
    }
}
