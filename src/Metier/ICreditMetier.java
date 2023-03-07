package Metier;

import Model.Credit;

public interface ICreditMetier {
    Credit NS_calculer_Mensualite(Long id) throws Exception;
}


