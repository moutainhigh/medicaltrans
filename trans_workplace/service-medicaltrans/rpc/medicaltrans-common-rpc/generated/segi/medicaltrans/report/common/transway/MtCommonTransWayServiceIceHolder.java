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
// Generated from file `mt_transway_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transway;

public final class MtCommonTransWayServiceIceHolder extends Ice.ObjectHolderBase<MtCommonTransWayServiceIce>
{
    public
    MtCommonTransWayServiceIceHolder()
    {
    }

    public
    MtCommonTransWayServiceIceHolder(MtCommonTransWayServiceIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof MtCommonTransWayServiceIce)
        {
            value = (MtCommonTransWayServiceIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _MtCommonTransWayServiceIceDisp.ice_staticId();
    }
}