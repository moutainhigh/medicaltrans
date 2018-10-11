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
// Generated from file `mt_tasktype_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.transType;

public final class MtCommonTransTypeServiceIceHolder extends Ice.ObjectHolderBase<MtCommonTransTypeServiceIce>
{
    public
    MtCommonTransTypeServiceIceHolder()
    {
    }

    public
    MtCommonTransTypeServiceIceHolder(MtCommonTransTypeServiceIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof MtCommonTransTypeServiceIce)
        {
            value = (MtCommonTransTypeServiceIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _MtCommonTransTypeServiceIceDisp.ice_staticId();
    }
}
