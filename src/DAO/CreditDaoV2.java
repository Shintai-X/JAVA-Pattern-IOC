package DAO;

import Model.Credit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreditDaoV2 implements IDao<Credit,Long> {
    public static Set<Credit> BDCreditsV2(){
        return new HashSet<Credit>(
                Arrays.asList(
                        new Credit(1L,300000.0,120,2.5,"X1",0.0),
                        new Credit(2L,850000.0,240,2.5,"X2",0.0),
                        new Credit(3L,20000.0,30,1.5,"X3",0.0),
                        new Credit(4L,650000.0,60,2.0,"X4",0.0)
                )
        );
    }


    @Override
    public Credit trouverParID(Long id) {
        System.out.println("[DAO - DS V2 Mysql] trouver le credit n° : " + id);
        return BDCreditsV2()
                .stream()
                .filter(credit -> credit.getId() == id)
                .findFirst()
                .get();
    }
}
