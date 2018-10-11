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
// Generated from file `mt_location_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.location.common;

public final class MtLocationCommonServiceIceHolder extends Ice.ObjectHolderBase<MtLocationCommonServiceIce>
{
    public
    MtLocationCommonServiceIceHolder()
    {
    }

    public
    MtLocationCommonServiceIceHolder(MtLocationCommonServiceIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof MtLocationCommonServiceIce)
        {
            value = (MtLocationCommonServiceIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _MtLocationCommonServiceIceDisp.ice_staticId();
    }
}
