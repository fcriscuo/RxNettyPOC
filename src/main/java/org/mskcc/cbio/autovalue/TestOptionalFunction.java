package org.mskcc.cbio.autovalue;


import com.google.common.base.Optional;
import com.google.common.base.Predicate;

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
 * Created by Fred Criscuolo on 7/24/15.
 * criscuof@mskcc.org
 */
public class TestOptionalFunction {

   static  Predicate<Optional<?>>  optionalPresent = new Predicate<Optional<?>>() {
        @Override
        public boolean apply(final Optional<?> input) {
            return input.isPresent();
        }
    };

    public static void main(String...args) {
        Optional<String>sOpt1 = Optional.of( "This is a test string");
       if( optionalPresent.apply(sOpt1)) {
           System.out.println(sOpt1.get());
       }
       Optional<String> sOpt2 = Optional.absent();
        if(optionalPresent.apply(sOpt2)){
            System.out.println(sOpt2.get());
        } else {
            System.out.println("Optional absent");
        }

    }

}
