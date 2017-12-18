package com.example.germnrojo.pictoproject;

/**
 * Created by pc on 17-07-2017.
 *
 * @author Diego Saavedra Tapia
 */

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseModel extends com.raizlabs.android.dbflow.structure.BaseModel {

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);

    }
}








