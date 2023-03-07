import DAO.CreditDao;
import DAO.IDao;
import Metier.CreditMetier;
import Metier.ICreditMetier;
import Model.Credit;
import Presentation.CreditControleur;
import Presentation.ICreditControleur;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

public class SimulatteurDeCredit_App {
    static Scanner clavier = new Scanner(System.in);
    static ICreditControleur creditControleur;

    private static boolean NS_estUnNombre(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e ){
            return false;
        }
    }

    public static void test1(){
        // instanciation des différents compsants de l'application
        var dao = new CreditDao();
        var metier = new CreditMetier();
        var controleur = new CreditControleur();
        // injection des dépendances
        metier.setCreditDao(dao);
        controleur.setCreditMetier(metier);
        // tester
        String rep = "";
        do {
            System.out.println("=> [Test 1] calcule de Mensualité de cédit <= \n");
            try {
                String input = "";
                while (true){
                    System.out.println("=> Entrez l'id du crédit : ");
                    input = clavier.nextLine();
                    if (NS_estUnNombre(input)) break;
                    System.err.println("Entrée non valide !!!");
                }
                long id = Long.parseLong(input);
                controleur.NS_afficher_Mensualite(id);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Voulez vous quittez (oui/non) ? ");
            rep = clavier.nextLine();
        }while (!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir ^_^");
    }

    public static void test2() throws Exception {

        String daoClass;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");

        if (propertiesFile == null) throw new Exception("fichier config introuvable");
        else {
            try {
                properties.load(propertiesFile);
                daoClass        = properties.getProperty("DAO");
                serviceClass    = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            }
            catch (IOException e){
                throw new Exception("Problème de chargement des propriétés du fichier config");
            }
            finally {
                properties.clear();
            }
        }
        try {
            Class cDao          = Class.forName(daoClass);
            Class cMetier       = Class.forName(serviceClass);
            Class cController   = Class.forName(controllerClass);

            var dao = (IDao<Credit, Long>) cDao.getDeclaredConstructor().newInstance();
            var metier = (ICreditMetier) cMetier.getDeclaredConstructor().newInstance();
            creditControleur    = (ICreditControleur) cController.getDeclaredConstructor().newInstance();

            Method setDao       = cMetier.getMethod("setCreditDao", IDao.class);
            setDao.invoke(metier,dao);

            Method setMetier    = cController.getMethod("setCreditMetier", ICreditMetier.class);
            setMetier.invoke(creditControleur,metier);

            creditControleur.NS_afficher_Mensualite(1L);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            test2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
