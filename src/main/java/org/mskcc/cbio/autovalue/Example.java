package org.mskcc.cbio.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Copyright (c) Fred Criscuolo
 * fcriscu1@jhu.edu
 * <p/>
 * <p/>
 * Created by fcriscuo on 7/11/15.
 */
public class Example {

    @AutoValue
    public abstract static class Gene {
        Gene() {} //hidden default constructor prevents subclassing
        public static Gene create (String hugoSymbol, String chromosome, Integer startPosition, Integer endPosition){
            return new  AutoValue_Example_Gene(hugoSymbol, chromosome, startPosition, endPosition);
        }
        public abstract String hugoSymbol();
        public abstract String chromosome();
        public abstract Integer startPosition();
        public abstract Integer endPosition();

    }

        @AutoValue
        public abstract static class Animal {
            Animal(){} // prevents sublassing in another package
            public static Animal create(String name, int numberOfLegs) {
                return new AutoValue_Example_Animal(name, numberOfLegs);
                // (or just AutoValue_Animal if this is not nested)
            }

            public abstract String name();
            public abstract int numberOfLegs();
        }


    public static void main(String...args) {
        Animal dog1 = Animal.create("Rover",4);
        System.out.println("Name " +dog1.name()+" legs= " +dog1.numberOfLegs());
        Animal dog2 = Animal.create("Rover",4);
        System.out.println("Equality = " +dog1.equals(dog2));
        org.mskcc.cbio.autovalue.Gene gene1 = org.mskcc.cbio.autovalue.Gene.create("BCRA1", "17", 10110, 20014);
        System.out.println(gene1.toString());
        System.out.println(org.mskcc.cbio.autovalue.Gene.create("PTEN", "X", 67457, 99997).hugoSymbol());

    }
}
