package org.mskcc.cbio.autovalue.test;
import org.mskcc.cbio.autovalue.*;

/**
 * Copyright (c) 2015 Memorial Sloan-Kettering Cancer Center.
 * <p/>
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 * documentation provided hereunder is on an "as is" basis, and
 * Memorial Sloan-Kettering Cancer Center
 * has no obligations to provide maintenance, support,
 * updates, enhancements or modifications.  In no event shall
 * Memorial Sloan-Kettering Cancer Center
 * be liable to any party for direct, indirect, special,
 * incidental or consequential damages, including lost profits, arising
 * out of the use of this software and its documentation, even if
 * Memorial Sloan-Kettering Cancer Center
 * has been advised of the possibility of such damage.
 * <p/>
 * Created by Fred Criscuolo on 7/14/15.
 * criscuof@mskcc.org
 */
public class TestAnimal {
    public static void main (String...args){

        Example.Animal an1 = Example.Animal.create("Spot",4);
        Example.Animal an2 = Example.Animal.create("Spot",4);
        Example.Animal an3 = Example.Animal.create("NotSpot",2);
        System.out.println(an1.toString());
        System.out.println("1 == 2 should be true " +an1.equals(an2));
        System.out.println("2 == 3 should be false " +an2.equals(an3));
        System.out.println("Name " +an1.name() +" hashcode " +an1.hashCode());

        Gene gene1 = Gene.create("BCRA1","17",10110, 20014);
        System.out.println(gene1.toString());
        System.out.println(Gene.create("PTEN","X",67457,99997).hugoSymbol());

    }
}
