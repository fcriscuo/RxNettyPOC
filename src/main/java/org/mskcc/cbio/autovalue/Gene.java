package org.mskcc.cbio.autovalue;

import com.google.auto.value.AutoValue;

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
@AutoValue
public abstract class Gene {
    Gene() {} //hidden default constructor prevents subclassing
    public static Gene create (String hugoSymbol, String chromosome, Integer startPosition, Integer endPosition){
        return new  AutoValue_Gene(hugoSymbol, chromosome, startPosition, endPosition);
    }
    public abstract String hugoSymbol();
    public abstract String chromosome();
    public abstract Integer startPosition();
    public abstract Integer endPosition();

}
