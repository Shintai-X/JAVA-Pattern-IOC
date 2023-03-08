package Model;


import lombok.*;
import org.springframework.stereotype.Component;

@Data @AllArgsConstructor @NoArgsConstructor
public class Credit {

    // NS code
    private Long id;
    private Double capitale_Emprunt;
    private Integer nombre_Mois;
    private Double taux_Mensuel;
    private String nom_Demandeur;
    private Double mensualite;

    @Override
    public String toString(){
        var creditStr = "==================================================================== \n";
        creditStr+= "=> Crédit n°                  : " +getId()+ "                        \n";
        creditStr+= "=> Nom du demandeur de crédit : " + getNom_Demandeur() + "           \n";
        creditStr+= "-------------------------------------------------------------------- \n";
        creditStr+= "=> Capitale Emprunté          : " + getCapitale_Emprunt() + "        \n";
        creditStr+= "=> Nombre de mois             : " + getNombre_Mois() + "             \n";
        creditStr+= "=> Taux mensuel               : " + getTaux_Mensuel() + "            \n" ;
        creditStr+= "-------------------------------------------------------------------- \n";
        creditStr+= "=> Mensualité                 : "
                + (getMensualite() == 0.0 ? "NON-CALCULE":getMensualite()+ " DH/mois")+"\n";
        creditStr+= "==================================================================== \n";

        return creditStr;
    }

    public static void main(String[] args) {

    }

}

