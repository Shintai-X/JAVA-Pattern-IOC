package DAO;

import Model.Credit;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component("d1")
public class CreditDao implements IDao<Credit,Long> {


    public static Set<Credit> BDCredits(){
        return new HashSet<Credit>(
                Arrays.asList(
                        new Credit(1L,300000.0,120,2.5,"P1",0.0),
                        new Credit(2L,850000.0,240,2.5,"P2",0.0),
                        new Credit(3L,20000.0,30,1.5,"P3",0.0),
                        new Credit(4L,650000.0,60,2.0,"P4",0.0)
                )
        );
    }


    @Override
    public Credit trouverParID(Long id) {
        System.out.println("[DAO - DS volatile] trouver le credit n° : " + id);
        return BDCredits()
                .stream()
                .filter(credit -> credit.getId() == id)
                .findFirst()
                .get();
    }
}
