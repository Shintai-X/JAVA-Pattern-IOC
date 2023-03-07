package Metier;
import DAO.CreditDao;
import DAO.IDao;
import Model.Credit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditMetier implements  ICreditMetier {
    IDao<Credit,Long> creditDao;

    @Override
    public Credit NS_calculer_Mensualite(Long id) throws Exception{
        var credit = creditDao.trouverParID(id);

        if (credit == null)
        {
            throw new Exception("L'id du credit est incorrecte :: [Credit non trouve]");
        }
        else {
            double  taux         = credit.getTaux_Mensuel();
            taux         = taux/1200;
            double  capitale     = credit.getCapitale_Emprunt();
            int     nbr_mois     = credit.getNombre_Mois();

            double  mensualite   = (capitale * taux) / (1 - (Math.pow((1 + taux), -1 * nbr_mois)));
            mensualite   = Math.round(mensualite*100)/100;

            credit.setMensualite(mensualite);

            return credit;
        }
    }
}
