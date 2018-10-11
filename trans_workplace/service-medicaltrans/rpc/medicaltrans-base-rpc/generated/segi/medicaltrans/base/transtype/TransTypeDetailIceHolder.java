// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `transtype.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.transtype;

public final class TransTypeDetailIceHolder extends Ice.ObjectHolderBase<TransTypeDetailIce>
{
    public
    TransTypeDetailIceHolder()
    {
    }

    public
    TransTypeDetailIceHolder(TransTypeDetailIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof TransTypeDetailIce)
        {
            value = (TransTypeDetailIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return TransTypeDetailIce.ice_staticId();
    }
}
